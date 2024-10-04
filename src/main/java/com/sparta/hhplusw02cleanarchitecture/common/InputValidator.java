package com.sparta.hhplusw02cleanarchitecture.common;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.ApplyLectureService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.GetAppliedLecturesService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.GetLectureItemsService;
import java.util.Objects;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *  input 데이터를 검증해주는 컴포넌트
 */
@Component
@NoArgsConstructor
public class InputValidator {
  public void applyLectureServiceInputValidator(ApplyLectureService.Input input) {
    if (Objects.isNull(input)) throw new InputException("입력 값이 null일 수 없습니다.");
    if (Objects.isNull(input.getUserId()) || input.getUserId() <= 0) throw new InputException("유효하지 않은 사용자 ID입니다.");
    if (Objects.isNull(input.getLectureId()) || input.getLectureId() <= 0) throw new InputException("유효하지 않은 특강 ID입니다.");
    if (Objects.isNull(input.getItemId()) || input.getItemId() <= 0) throw new InputException("유효하지 않은 특강 목록 ID입니다.");
    if (Objects.isNull(input.getInventoryId()) || input.getInventoryId() <= 0) throw new InputException("유효하지 않은 특강 여석 ID입니다.");
  }

  public void getLectureItemsServiceInputValidator(GetLectureItemsService.Input input) {
    if (Objects.isNull(input)) throw new InputException("입력 값이 null일 수 없습니다.");
    if (Objects.isNull(input.getDate())) throw new InputException("유효하지 않은 날짜입니다.");
  }

  public void getAppliedLectureServiceInputValidator(GetAppliedLecturesService.Input input) {
    if (input == null) throw new InputException("입력 값이 null일 수 없습니다.");
    if (input.getUserId() == null || input.getUserId() <= 0) throw new InputException("유효하지 않은 사용자 ID입니다.");
  }

}