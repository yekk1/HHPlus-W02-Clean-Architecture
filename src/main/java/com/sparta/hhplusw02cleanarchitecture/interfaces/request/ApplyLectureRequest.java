package com.sparta.hhplusw02cleanarchitecture.interfaces.request;

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
}
