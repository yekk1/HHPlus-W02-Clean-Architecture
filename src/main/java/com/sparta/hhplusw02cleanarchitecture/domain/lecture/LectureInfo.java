package com.sparta.hhplusw02cleanarchitecture.domain.lecture;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.Builder;

public record LectureInfo(
    Long lectureId,
    Long itemId,
    String title,
    String instructor,
    LocalDate date,
    Integer capacity,
    Integer amount) {

  @QueryProjection
  @Builder
  public LectureInfo(Long lectureId, Long itemId, String title, String instructor, LocalDate date,
      Integer capacity, Integer amount) {
    this.lectureId = lectureId;
    this.itemId = itemId;
    this.title = title;
    this.instructor = instructor;
    this.date = date;
    this.capacity = capacity;
    this.amount = amount;
  }


}
