package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.QLectureInfo;
import com.sparta.hhplusw02cleanarchitecture.domain.entity.QLectureEntity;
import com.sparta.hhplusw02cleanarchitecture.domain.entity.QLectureHistoryEntity;
import com.sparta.hhplusw02cleanarchitecture.domain.entity.QLectureInventoryEntity;
import com.sparta.hhplusw02cleanarchitecture.domain.entity.QLectureItemEntity;
import jakarta.persistence.LockModeType;
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
        .join(lectureItem).on(lecture.id.eq(lectureItem.lecture_id))
        .join(lectureInventory).on(lectureItem.id.eq(lectureInventory.item_id))
        .where(lectureItem.date.eq(date))
        .fetch();
  }

  /**
   * 특정 userId 로 신청 완료된 특강 목록을 조회하는 쿼리
   * @param userId: 조회할 userId
   * @return 신청 완료된 특강 목록
   */
  public List<LectureInfo> findLectureInfosByUserId(Long userId) {
    QLectureEntity lecture = QLectureEntity.lectureEntity;
    QLectureItemEntity lectureItem = QLectureItemEntity.lectureItemEntity;
    QLectureInventoryEntity lectureInventory = QLectureInventoryEntity.lectureInventoryEntity;
    QLectureHistoryEntity lectureHistory = QLectureHistoryEntity.lectureHistoryEntity;

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
        .join(lectureItem).on(lecture.id.eq(lectureItem.lecture_id))
        .join(lectureInventory).on(lectureItem.id.eq(lectureInventory.item_id))
        .join(lectureHistory).on(lectureItem.id.eq(lectureHistory.item_id))
        .where(lectureHistory.user_id.eq(userId))
        .fetch();
  }

  /**
   * 특강 여석을 조회하는 쿼리
   * @param inventoryId 조회할 특강여석 id
   * @return 특강 여석 갯수
   */
  public Integer findLectureInfosByInventoryId(Long inventoryId) {
    QLectureInventoryEntity lectureInventory = QLectureInventoryEntity.lectureInventoryEntity;

    return queryFactory.select(lectureInventory.amount)
        .from(lectureInventory)
        .where(lectureInventory.id.eq(inventoryId))
        .setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
        .fetchOne();
  }

  /**
   * 이미 신청한 특강인지를 조회하는 쿼리
   * @param userId 조회할 유저 id
   * @param itemId 조회할 특강 목록 id
   * @return 이미 신청한 특강인지 여부
   */
  public boolean getByUserIdAndLectureId(Long userId, Long itemId) {
    QLectureHistoryEntity lectureHistory = QLectureHistoryEntity.lectureHistoryEntity;

    BooleanExpression predicate = lectureHistory.user_id.eq(userId)
        .and(lectureHistory.lecture_id.eq(itemId));

    return queryFactory.selectOne()
        .from(lectureHistory)
        .where(predicate)
        .setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
        .fetchFirst() != null;
  }
}
