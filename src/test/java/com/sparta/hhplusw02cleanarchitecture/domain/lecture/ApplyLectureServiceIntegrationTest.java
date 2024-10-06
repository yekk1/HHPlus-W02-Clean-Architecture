package com.sparta.hhplusw02cleanarchitecture.domain.lecture;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureInventoryEntity;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.ApplyLectureService;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureHistory.LectureHistoryRepositoryJpaImpl;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory.LectureInventoryRepositoryJpaImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.concurrent.CountDownLatch;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplyLectureServiceIntegrationTest {

  @Autowired
  private ApplyLectureService applyLectureService;

  @Autowired
  private LectureInventoryRepositoryJpaImpl lectureInventoryRepository;

  @Autowired
  private LectureHistoryRepositoryJpaImpl lectureHistoryRepository;

  @Test
  @DisplayName("특강은 선착순 30명만 신청 가능하다.")
  public void allowOnlyFirst30Applicants() throws InterruptedException {
    // Given
    Long inventoryId = 1L; // 테스트할 특강 여석 ID
    int initialAmount = 30; // 전체 여석 수
    int numberOfApplicants = 40; // 신청 횟수

    ExecutorService executorService = Executors.newFixedThreadPool(numberOfApplicants);
    CountDownLatch latch = new CountDownLatch(numberOfApplicants);

    // When
    for (int i = 0; i < numberOfApplicants; i++) {
      final int userId = i;
      executorService.execute(() -> {
        try {
          ApplyLectureService.Input input = ApplyLectureService.Input.builder()
              .userId(Long.valueOf(userId))
              .lectureId(1L)
              .itemId(1L)
              .inventoryId(inventoryId)
              .build();
          applyLectureService.applyLecture(input);
        } catch (IllegalStateException e) {
          System.err.println("특강 신청이 마감되었습니다.");
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(10, TimeUnit.SECONDS);
    executorService.shutdown();

    // Then
    LectureInventoryEntity updatedInventory = lectureInventoryRepository.findById(inventoryId);
    assertThat(updatedInventory.getAmount()).isEqualTo(0); // 여석이 0이어야 함
    assertThat(lectureHistoryRepository.count()).isEqualTo(initialAmount); // 성공한 신청 수는 초기 여석 수와 같아야 함
  }
  @Test
  @DisplayName("동일한 유저 정보로 같은 특강을 1번만 신청할 수 있다.")
  public void alreadyAppliedLectureWithSameUserId_Then_Fail() throws InterruptedException {
    //given
    Long userId = 1L;       // 테스트할 유저 ID
    Long lectureId = 1L;    // 테스트할 강의 ID
    Long itemId = 1L;       // 테스트할 강의 목록 ID
    Long inventoryId = 1L;  // 테스트할 여석 ID
    Integer initialAmount = 30;

    int numberOfApplications = 5; //신청횟수
    CountDownLatch latch = new CountDownLatch(numberOfApplications);

    for (int i = 0; i < numberOfApplications; i++) {
      ApplyLectureService.Input input = ApplyLectureService.Input.builder()
          .userId(userId)
          .lectureId(lectureId)
          .itemId(itemId)
          .inventoryId(inventoryId)
          .build();
      try {
        applyLectureService.applyLecture(input);
      } catch (IllegalStateException e) {
        System.err.println("특강 신청이 마감되었습니다.");
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        latch.countDown();
      }
    }

    latch.await(10, TimeUnit.SECONDS);

    LectureInventoryEntity updatedInventory = lectureInventoryRepository.findById(inventoryId);
    assertThat(updatedInventory.getAmount()).isEqualTo(initialAmount - 1); // 여석이 0이어야 함 // 여석이 한 자리만 줄었어야 함.

    assertThat(lectureHistoryRepository.count()).isEqualTo(1); //성공한 신청 수는 한번 뿐이어야 함
  }
  @Test
  @DisplayName("여러 스레드를 사용할지라도 동일한 유저 정보로 같은 특강을 1번만 신청할 수 있다.")
  public void alreadyAppliedLectureWithSameUserIdWithOtherThread_Then_Fail() throws InterruptedException {
    //given
    Long userId = 1L;       // 테스트할 유저 ID
    Long lectureId = 1L;    // 테스트할 강의 ID
    Long itemId = 1L;       // 테스트할 강의 목록 ID
    Long inventoryId = 1L;  // 테스트할 여석 ID
    Integer initialAmount = 30;

    int numberOfApplications = 5; //신청횟수
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfApplications);
    CountDownLatch latch = new CountDownLatch(numberOfApplications);
//    Thread.sleep(3000);
    for (int i = 0; i < numberOfApplications; i++) {
      executorService.execute(() -> {
        ApplyLectureService.Input input = ApplyLectureService.Input.builder()
            .userId(userId)
            .lectureId(lectureId)
            .itemId(itemId)
            .inventoryId(inventoryId)
            .build();
        try {
          applyLectureService.applyLecture(input);
        } catch (IllegalStateException e) {
          System.err.println(e);
        } catch (Exception e) {
          System.err.println(e);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await(10, TimeUnit.SECONDS);
    executorService.shutdown();

    LectureInventoryEntity updatedInventory = lectureInventoryRepository.findById(inventoryId);
    assertThat(updatedInventory.getAmount()).isEqualTo(initialAmount - 1); //여석이 한 자리만 줄었어야 함.

    assertThat(lectureHistoryRepository.count()).isEqualTo(1); //성공한 신청 수는 한번 뿐이어야 함
  }
}
