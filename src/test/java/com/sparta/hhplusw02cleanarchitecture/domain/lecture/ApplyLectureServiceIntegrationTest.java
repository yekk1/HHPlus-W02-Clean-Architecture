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
}
