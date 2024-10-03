package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureItem;

import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.LectureItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 특강 목록 테이블 CUD JPA 레포지토리 인터페이스
 */
public interface LectureItemJpaRepository extends JpaRepository<LectureItemEntity, Long> {

}
