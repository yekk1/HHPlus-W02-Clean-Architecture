package com.sparta.hhplusw02cleanarchitecture.interfaces.response;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특강 정보 목록 응답 DTO
 * :  특강 선택 API / 특강 신청 완료 목록 조회 API 에 함께 사용
 */
@Data
@NoArgsConstructor
public class GetLectureListResponse {
  private Long lectureId;
  private Long itemId;
  private String title;
  private String instructor;
  private LocalDate date;
  private Integer capacity;
  private Integer amount;

  @Builder
  public GetLectureListResponse(Long lectureId, Long itemId, String title, String instructor,
      LocalDate date, Integer capacity, Integer amount) {
    this.lectureId = lectureId;
    this.itemId = itemId;
    this.title = title;
    this.instructor = instructor;
    this.date = date;
    this.capacity = capacity;
    this.amount = amount;
  }

  public GetLectureListResponse(LectureInfo lectureInfo) {
    this.lectureId = lectureInfo.lectureId();
    this.itemId = lectureInfo.itemId();
    this.title = lectureInfo.title();
    this.instructor = lectureInfo.instructor();
    this.date = lectureInfo.date();
    this.capacity = lectureInfo.capacity();
    this.amount = lectureInfo.amount();
  }
}

