package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
}
