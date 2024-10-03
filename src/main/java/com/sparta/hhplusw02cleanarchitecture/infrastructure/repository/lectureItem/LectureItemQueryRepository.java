package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureItem;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 특강 목록 쿼리 레포지토리
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LectureItemQueryRepository {
  private final JPAQueryFactory queryFactory;
}
