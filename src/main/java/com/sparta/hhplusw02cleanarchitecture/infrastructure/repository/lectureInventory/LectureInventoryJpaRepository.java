package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory;

import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 특강 여석 테이블 CUD JPA 레포지토리 인터페이스
 */
public interface LectureInventoryJpaRepository extends JpaRepository<LectureInventoryEntity, Long> {

}
