package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory;


import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureInventoryEntity;
import org.springframework.stereotype.Repository;

/**
 * 특강 여석 테이블 CUD 레포지토리 인터페이스
 */
@Repository
public interface LectureInventoryRepository {
  LectureInventoryEntity updateAmount(LectureInventoryEntity lectureInventoryEntity);
}
