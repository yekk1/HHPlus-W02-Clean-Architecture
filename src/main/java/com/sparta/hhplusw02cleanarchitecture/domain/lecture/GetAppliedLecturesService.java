package com.sparta.hhplusw02cleanarchitecture.domain.lecture;

import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 *  특강 신청 완료 목록 조회 서비스
 *  - 특정 userId 로 신청 완료된 특강 목록을 조회하는 서비스를 작성합니다.
 *  - 각 항목은 특강 ID 및 이름, 강연자 정보를 담고 있어야 합니다.
 */
@Service
public class GetAppliedLecturesService {

  @Data
  public static class Input {
    private Long userId;
  }
  @Data
  public static class Output{
    private List<LectureInfo> list;
  }
  public Output get (Input input){
    return new Output();
    }

}
