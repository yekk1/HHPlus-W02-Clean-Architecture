package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 특강 여석 쿼리 레포지토리
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LectureInventoryQueryRepository {
  private final JPAQueryFactory queryFactory;
}
