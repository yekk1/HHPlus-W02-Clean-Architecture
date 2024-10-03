package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureHistory;

import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 특강 기본 테이블 CUD JPA 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class LectureHistoryRepositoryJpaImpl implements LectureHistoryRepository{
  private final LectureHistoryJpaRepository lectureHistoryRepository;

  @Override
  public LectureHistoryEntity saveHistory(LectureHistoryEntity lectureHistory) {
    return lectureHistoryRepository.save(lectureHistory);
  }
}
