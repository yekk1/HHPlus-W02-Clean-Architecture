package com.sparta.hhplusw02cleanarchitecture.domain.lecture;

import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * 특강 선택 서비스
 * - 날짜별로 현재 신청 가능한 특강 목록을 조회하는 서비스 를 작성합니다.
 * - 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
 */
@Service
public class GetLectureItemsService {
  @Data
  public static class Output{
    private List<LectureInfo> list;
  }
  public Output getLectures (){
    return new Output();
  }
}
