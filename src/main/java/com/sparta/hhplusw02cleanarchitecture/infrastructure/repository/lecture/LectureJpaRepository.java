package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture;

import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 특강 기본 테이블 CUD JPA 레포지토리 인터페이스
 */
public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

}
