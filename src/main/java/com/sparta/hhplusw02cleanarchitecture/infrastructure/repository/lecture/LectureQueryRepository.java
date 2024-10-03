package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.QLectureInfo;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.QLectureEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.QLectureHistoryEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.QLectureInventoryEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.QLectureItemEntity;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 특강 기본 쿼리 레포지토리
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LectureQueryRepository {
  private final JPAQueryFactory queryFactory;

  /**
   * 날짜별 강의 목록을 조회하는 쿼리
   * @param date: 조회할 날짜
   * @return 강의 목록들
   */
  public List<LectureInfo> findLectureInfosByDate(LocalDate date) {
    QLectureEntity lecture = QLectureEntity.lectureEntity;
    QLectureItemEntity lectureItem = QLectureItemEntity.lectureItemEntity;
    QLectureInventoryEntity lectureInventory = QLectureInventoryEntity.lectureInventoryEntity;

    return queryFactory.select(new QLectureInfo(
            lecture.id,
            lectureItem.id,
            lecture.title,
            lecture.instructor,
            lectureItem.date,
            lectureItem.capacity,
            lectureInventory.amount
        ))
        .from(lecture)
        .join(lectureItem).on(lecture.id.eq(lectureItem.id))
        .join(lectureInventory).on(lectureItem.id.eq(lectureInventory.id))
        .where(lectureItem.date.eq(date))
        .fetch();
  }
}
