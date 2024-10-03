package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureHistory;

import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.LectureHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 특강 신청 내역 테이블 CUD JPA 레포지토리 인터페이스
 */
public interface LectureHistoryJpaRepository extends JpaRepository<LectureHistoryEntity, Long> {

}
