package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 특강 여석 테이블 CUD JPA 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class LectureInventoryRepositoryJpaImpl implements LectureInventoryRepository {
  private final LectureInventoryJpaRepository lectureInventoryRepository;
}
