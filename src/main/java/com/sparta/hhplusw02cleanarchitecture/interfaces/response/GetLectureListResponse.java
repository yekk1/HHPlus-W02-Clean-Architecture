package com.sparta.hhplusw02cleanarchitecture.interfaces.response;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특강 정보 목록 응답 DTO
 * :  특강 선택 API / 특강 신청 완료 목록 조회 API 에 함께 사용
 */
@Data
@NoArgsConstructor
public class GetLectureListResponse {
  private String title;
  private String instructor;
  private LocalDate date;
  private Long capacity;
  private Long amount;
}

