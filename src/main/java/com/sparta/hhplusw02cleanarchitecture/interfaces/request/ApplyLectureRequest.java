package com.sparta.hhplusw02cleanarchitecture.interfaces.request;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.ApplyLectureService;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특강 신청 요청 DTO
 */
@Data
@NoArgsConstructor
public class ApplyLectureRequest {
  private Long userId;
  private Long lectureId;
  private Long itemId;
  private Long inventoryId;


  @Builder
  public ApplyLectureRequest(Long userId, Long lectureId, Long itemId, Long inventoryId) {
    this.userId = userId;
    this.lectureId = lectureId;
    this.itemId = itemId;
    this.inventoryId = inventoryId;
  }


  public ApplyLectureService.Input toInputDTO() {
    return ApplyLectureService.Input.builder()
        .userId(this.userId)
        .lectureId(this.lectureId)
        .itemId(this.itemId)
        .inventoryId(this.inventoryId)
        .build();
  }

}
