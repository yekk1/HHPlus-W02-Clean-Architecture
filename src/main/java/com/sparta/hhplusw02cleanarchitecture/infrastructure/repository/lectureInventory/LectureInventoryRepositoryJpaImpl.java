package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory;

import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureInventoryEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 특강 여석 테이블 CUD JPA 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class LectureInventoryRepositoryJpaImpl implements LectureInventoryRepository {
  private final LectureInventoryJpaRepository lectureInventoryRepository;

  @Override
  public LectureInventoryEntity updateAmount(LectureInventoryEntity lectureInventoryEntity) {
    return lectureInventoryRepository.save(lectureInventoryEntity);
  }

  public LectureInventoryEntity findById(Long id) {
    Optional<LectureInventoryEntity> inventory = lectureInventoryRepository.findById(id);
    return inventory.orElseThrow(() -> new RuntimeException("Product not found"));
  }
}
