package com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 특강 기본 테이블 CUD JPA 레포지토리
 */

@Repository
@RequiredArgsConstructor
public class LectureRepositoryJpaImpl implements LectureRepository {

  private final LectureJpaRepository lectureRepository;
  @Override
  public LectureInfo get() {
    return new LectureInfo(1L,1L,"임시", "홍길동",LocalDate.of(2024,1,1),30,30);
  }

}
