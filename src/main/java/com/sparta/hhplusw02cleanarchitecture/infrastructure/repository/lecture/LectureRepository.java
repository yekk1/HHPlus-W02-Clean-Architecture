package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import org.springframework.stereotype.Repository;

/**
 * 특강 기본 테이블 CUD 레포지토리 인터페이스
 */
@Repository
public interface LectureRepository {
  LectureInfo get();

}
