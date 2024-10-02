package com.sparta.hhplusw02cleanarchitecture.domain.lecture;

import lombok.Data;
import org.springframework.stereotype.Service;
/**
 * 특강 신청 서비스
 * - 특정 userId 로 선착순으로 제공되는 특강을 신청하는 서비스 를 작성합니다.
 * - 동일한 신청자는 동일한 강의에 대해서 한 번의 수강 신청만 성공할 수 있습니다.
 * - 특강은 선착순 30명만 신청 가능합니다.
 * - 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
 */
@Service
public class ApplyLectureService {

  @Data
  public static class Input {
    private Long userId;
    private Long lectureId;
    private Long itemId;
  }

  @Data
  public static class Output {
    private LectureInfo info;
  }
  public Output apply (Input input){
    return new Output();
  }

}
