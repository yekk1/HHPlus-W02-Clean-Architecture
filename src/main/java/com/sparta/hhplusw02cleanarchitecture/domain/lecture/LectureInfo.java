package com.sparta.hhplusw02cleanarchitecture.domain.lecture;

import java.time.LocalDate;
public record LectureInfo(
    Long lectureId,
    Long itemId,
    String title,
    String instructor,
    LocalDate date,
    Long capacity,
    Long amount) {

}
