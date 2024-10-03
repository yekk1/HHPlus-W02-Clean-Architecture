package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureHistory;


import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureHistoryEntity;
import org.springframework.stereotype.Repository;

/**
 * 특강 등록 내역 테이블 CUD 레포지토리 인터페이스
 */
@Repository
public interface LectureHistoryRepository{
  LectureHistoryEntity saveHistory(LectureHistoryEntity lectureHistory);
}
