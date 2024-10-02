package com.sparta.hhplusw02cleanarchitecture.application;

import com.sparta.hhplusw02cleanarchitecture.common.InputValidator;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.ApplyLectureService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.GetAppliedLecturesService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.GetLectureItemsService;
import org.springframework.stereotype.Component;

/**
 * 특강 신청 서비스 파사드
 */
@Component

public class LectureFacade {
  private final ApplyLectureService applyLectureService;
  private final GetAppliedLecturesService getAppliedLecturesService;
  private final GetLectureItemsService getLectureItemsService;
  private final InputValidator inputValidator;

  public LectureFacade(ApplyLectureService applyLectureService,
      GetAppliedLecturesService getAppliedLecturesService,
      GetLectureItemsService getLectureItemsService, InputValidator inputValidator) {
    this.applyLectureService = applyLectureService;
    this.getAppliedLecturesService = getAppliedLecturesService;
    this.getLectureItemsService = getLectureItemsService;
    this.inputValidator = inputValidator;
  }

  // 파사드의 책임은 다른사람이 쓰게 맞춰보내주는게 아님. 다른 곳에서 쓸 수 있어서 DTO로 보내주면 안됨
  //return LectureInfo;
  //자기만의 언어로 뱉어내야함
}
