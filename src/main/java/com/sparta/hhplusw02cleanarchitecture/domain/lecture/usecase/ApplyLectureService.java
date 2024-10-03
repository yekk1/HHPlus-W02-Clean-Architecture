package com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase;

import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.LectureHistoryEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.LectureInventoryEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture.LectureQueryRepository;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureHistory.LectureHistoryRepositoryJpaImpl;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory.LectureInventoryRepositoryJpaImpl;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
/**
 * 특강 신청 서비스
 * - 특정 userId 로 선착순으로 제공되는 특강을 신청하는 서비스 를 작성합니다.
 * - 동일한 신청자는 동일한 강의에 대해서 한 번의 수강 신청만 성공할 수 있습니다.
 * - 특강은 선착순 30명만 신청 가능합니다.
 * - 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
 */
@Service
@RequiredArgsConstructor
public class ApplyLectureService {

  private final LectureInventoryRepositoryJpaImpl lectureInventoryRepository;
  private final LectureHistoryRepositoryJpaImpl lectureHistoryRepository;
  private final LectureQueryRepository lectureQueryRepository;

  @Data
  @Builder
  public static class Input {

    private Long userId;
    private Long lectureId;
    private Long itemId;
    private Long inventoryId;
    private Integer amount;

    private void enroll() {
      if (this.amount <= 0) {
        throw new IllegalStateException("No available seats");
      }
      this.amount -= 1;
    }

    public LectureInventoryEntity toInventoryEntity() {
      return LectureInventoryEntity.builder()
          .id(this.inventoryId)
          .lecture_id(this.lectureId)
          .item_id(this.itemId)
          .amount(this.amount)
          .build();
    }

    public LectureHistoryEntity toHistoryEntity() {
      return LectureHistoryEntity.builder()
          .user_id(this.userId)
          .lecture_id(this.lectureId)
          .item_id(this.itemId)
          .applied_at(LocalDateTime.now())
          .build();
    }
  }


  @Data
  @Builder
  public static class Output {

    //    private LectureInfo info;
    private Long inventoryId;
    private Long historyId;
    private Integer amount;
    private LocalDateTime appliedAt;
  }

  @Transactional
  public Output applyLecture(Input input) {

    // 1. 잔여 좌석 확인 및 업데이트
    input.setAmount(
        lectureQueryRepository.findLectureInfosByInventoryId(input.getInventoryId())
    );
    input.enroll();

    // 2. 잔여 좌석 테이블에 업데이트
    LectureInventoryEntity lectureInventory = lectureInventoryRepository.updateAmount(
        input.toInventoryEntity()
    );

    // 3. 등록 내역 저장
    LectureHistoryEntity lectureHistory = lectureHistoryRepository.saveHistory(
        input.toHistoryEntity()
    );

    // 3. 결과 반환
    return Output.builder()
        .inventoryId(lectureInventory.getId())
        .amount(lectureInventory.getAmount())
        .historyId(lectureHistory.getId())
        .appliedAt(lectureHistory.getApplied_at())
        .build();
  }
}
