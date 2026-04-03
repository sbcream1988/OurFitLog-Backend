package com.ofl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.gathering.entity.Gathering;
import com.ofl.domain.gathering.repository.GatheringRepository;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.entity.Role;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.domain.participation.repository.ParticipationRepository;
import com.ofl.domain.participation.service.service.ParticipationService;
import com.ofl.domain.post.repository.PostRepository;
import com.ofl.global.entity.ProviderType;

import jakarta.persistence.EntityManager;

@SpringBootTest(properties = { "KAKAO_REST_API_KEY=test_key", "kakao.api.key=test_key",
		"JWT_SECRET_KEY=v7S8dn28df92m30dn29d8fn203nd829dfn203nd829dfn203nd829dfn203nd829dfn" })
public class ParticipationServiceTest {

	@Autowired
	private ParticipationService participationService;
	@Autowired
	private GatheringRepository gatheringRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	private ParticipationRepository participationRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private EntityManager entityManager;

	private Long savedGatheringId;
	private final List<Long> memberIds = new ArrayList<>();

	@Autowired
	private org.springframework.transaction.support.TransactionTemplate transactionTemplate;

	@BeforeEach
	void setup() {
	    transactionTemplate.execute(status -> {
	        try {
	            // 1. DB 초기화 (이 안에서는 무조건 트랜잭션이 보장됩니다)
	            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
	            entityManager.createNativeQuery("TRUNCATE TABLE participation").executeUpdate();
	            entityManager.createNativeQuery("TRUNCATE TABLE gathering").executeUpdate();
	            entityManager.createNativeQuery("TRUNCATE TABLE members").executeUpdate();
	            entityManager.createNativeQuery("TRUNCATE TABLE post_like").executeUpdate();
	            entityManager.createNativeQuery("TRUNCATE TABLE post").executeUpdate();
	            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

	            // 2. 데이터 저장
	            Member host = memberRepository.save(new Member("host@test.com", "호스트", Role.USER, ProviderType.GOOGLE, "1234"));
	            Gathering gathering = new Gathering("테스트 모임", "설명", LocalDateTime.now().plusDays(1), 10, host);
	            savedGatheringId = gatheringRepository.save(gathering).getId();

	            memberIds.clear();
	            for (int i = 0; i < 100; i++) {
	                Member m = memberRepository.save(new Member("user" + i + "@test.com", "유저" + i, Role.USER, ProviderType.SYSTEM, "00" + i));
	                memberIds.add(m.getId());
	            }

	            // 3. 동시성 테스트를 위한 플러시
	            entityManager.flush();
	            entityManager.clear();
	            
	            System.out.println("=== SETUP 성공: " + memberIds.size() + "명 생성됨 ===");
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	        return null;
	    });
	}


	@Transactional
    public void clearDatabase() {
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        
        // 테이블명 확인 필수! (보통 소문자나 엔티티 설정에 따름)
        entityManager.createNativeQuery("TRUNCATE TABLE participation").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE gathering").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE members").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE post_like").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE post").executeUpdate();
        
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
	@AfterEach
	void tearDown() {
		clearDatabase();
	}
	@Test
	@DisplayName("동시에 100명 신청해도 정원만큼만 가능하게")
	void concurrencyTest() throws InterruptedException {
		int maxCapacity = 10;
		int threadCount = 100;

		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			Long memberId = memberIds.get(i);
			Member participant = memberRepository.findById(memberId).orElseThrow();
			executorService.execute(() -> {
				try {
					participationService.attend(savedGatheringId, participant);
				} catch (Exception e) {
					System.out.println("신청 실패 사유: " + e.getMessage());
				} finally {
					latch.countDown();
				}
			});

		}
		latch.await();

		long count = participationRepository.count();
		System.out.println("최종 참여 확정 인원: " + count);

		assertThat(count).isEqualTo(10);
	}

}
