package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 특강 목록 테이블 CUD JPA 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class LectureItemRepositoryJpaImpl implements LectureItemRepository{
  private LectureItemJpaRepository lectureItemRepository;
}
