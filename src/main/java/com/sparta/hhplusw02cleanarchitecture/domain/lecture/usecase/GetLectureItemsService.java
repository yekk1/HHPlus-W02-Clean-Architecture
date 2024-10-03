package com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.LectureQueryRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 특강 선택 서비스
 * - 날짜별로 현재 신청 가능한 특강 목록을 조회하는 서비스 를 작성합니다.
 * - 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
 */
@Service
@RequiredArgsConstructor
public class GetLectureItemsService {
  private final LectureQueryRepository lectureQueryRepository;
  @Data
  public static class Input{
    private LocalDate date;
  }
  @Data
  @AllArgsConstructor
  public static class Output{
    private List<LectureInfo> list;
  }

  public Output getLectures (Input input){
    //TODO:
    // 1. validation check

    List<LectureInfo> lectureInfos = lectureQueryRepository.findLectureInfosByDate(input.getDate());
    return new Output(lectureInfos);
  }
}
