package com.sparta.hhplusw02cleanarchitecture.common;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.ApplyLectureService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.GetAppliedLecturesService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.GetLectureItemsService;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputValidatorTest {
  private InputValidator inputValidator;

  @BeforeEach
  public void setUp() {
    inputValidator = new InputValidator();
  }

  @Test
  @DisplayName("applyLectureServiceInputValidator: input이 유효하지 않으면 예외가 발생한다.")
  public void whenApplyLectureServiceInputValidator_intputInvalidData_Then_InputException() {
    // given
    ApplyLectureService.Input input = null;

    // when & then
    assertThatThrownBy(() -> inputValidator.applyLectureServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("입력 값이 null일 수 없습니다.");
  }

  @Test
  @DisplayName("applyLectureServiceInputValidator: 입력된 사용자 ID가 유효하지 않으면 예외가 발생한다.")
  public void whenApplyLectureServiceInputValidator_intputInvalidUserId_Then_InputException() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(-1L)
        .lectureId(1L)
        .itemId(1L)
        .inventoryId(1L)
        .amount(1)
        .build();

    // when & then
    assertThatThrownBy(() -> inputValidator.applyLectureServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("유효하지 않은 사용자 ID입니다.");
  }

  @Test
  @DisplayName("applyLectureServiceInputValidator: 입력된 특강 ID가 유효하지 않으면 예외가 발생한다.")
  public void twhenApplyLectureServiceInputValidator_intputInvalidLectureId_Then_InputException() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(-1L)
        .itemId(1L)
        .inventoryId(1L)
        .amount(1)
        .build();

    // when & then
    assertThatThrownBy(() -> inputValidator.applyLectureServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("유효하지 않은 특강 ID입니다.");
  }

  @Test
  @DisplayName("applyLectureServiceInputValidator: 입력된 특강 목록 ID가 유효하지 않으면 예외가 발생한다.")
  public void whenApplyLectureServiceInputValidator_intputInvalidItemId_Then_InputException() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(1L)
        .itemId(-1L)
        .inventoryId(1L)
        .amount(1)
        .build();

    // when & then
    assertThatThrownBy(() -> inputValidator.applyLectureServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("유효하지 않은 특강 목록 ID입니다.");
  }

  @Test
  @DisplayName("applyLectureServiceInputValidator: 입력된 특강 여석 ID가 유효하지 않으면 예외가 발생한다.")
  public void whenApplyLectureServiceInputValidator_intputInvalidInventoryId_Then_InputException() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(1L)
        .itemId(1L)
        .inventoryId(-1L)
        .amount(1)
        .build();

    // when & then
    assertThatThrownBy(() -> inputValidator.applyLectureServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("유효하지 않은 특강 여석 ID입니다.");
  }

  @Test
  @DisplayName("applyLectureServiceInputValidator: 입력값 유효 검증 통과")
  public void testApplyLectureServiceInputValidator_noInvalidData() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(1L)
        .itemId(1L)
        .inventoryId(1L)
        .amount(1)
        .build();

    // when & then
    assertThatCode(() -> inputValidator.applyLectureServiceInputValidator(input))
        .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("getLectureItemsServiceInputValidator: input이 유효하지 않으면 예외가 발생한다.")
  public void whenGetLectureItemsServiceInputValidator_intputInvalidData_Then_InputException() {
    // given
    GetLectureItemsService.Input input = null;

    // when & then
    assertThatThrownBy(() -> inputValidator.getLectureItemsServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("입력 값이 null일 수 없습니다.");
  }

  @Test
  @DisplayName("getLectureItemsServiceInputValidator: 입력된 날짜가 유효하지 않으면 예외가 발생한다.")
  public void whenGetLectureItemsServiceInputValidator_intputInvalidDate_Then_InputException() {
    // given
    GetLectureItemsService.Input input = new GetLectureItemsService.Input(null);

    // when & then
    assertThatThrownBy(() -> inputValidator.getLectureItemsServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("유효하지 않은 날짜입니다.");
  }

  @Test
  @DisplayName("getLectureItemsServiceInputValidator: 입력값 유효 검증 통과")
  public void whenGetAppliedLectureServiceInputValidator_noInvalidData() {
    // given
    GetLectureItemsService.Input input = new GetLectureItemsService.Input(LocalDate.now());

    // when & then
    assertThatCode(() -> inputValidator.getLectureItemsServiceInputValidator(input))
        .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("getAppliedLectureServiceInputValidator: input이 유효하지 않으면 예외가 발생한다.")
  public void whenGetAppliedLectureServiceInputValidator_intputInvalidData_Then_InputException() {
    // given
    GetAppliedLecturesService.Input input = null;

    // when & then
    assertThatThrownBy(() -> inputValidator.getAppliedLectureServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("입력 값이 null일 수 없습니다.");
  }

  @Test
  @DisplayName("getAppliedLectureServiceInputValidator: 입력된 사용자 ID가 유효하지 않으면 예외가 발생한다.")
  public void whenGetAppliedLectureServiceInputValidator_intputInvalidUserId_Then_InputException() {
    // given
    GetAppliedLecturesService.Input input = new GetAppliedLecturesService.Input(-1L);

    // when & then
    assertThatThrownBy(() -> inputValidator.getAppliedLectureServiceInputValidator(input))
        .isInstanceOf(InputException.class)
        .hasMessage("유효하지 않은 사용자 ID입니다.");
  }

  @Test
  @DisplayName("getAppliedLectureServiceInputValidator: 입력값 유효 검증 통과")
  public void testGetAppliedLectureServiceInputValidator_noInvalidData() {
    // given
    GetAppliedLecturesService.Input input = new GetAppliedLecturesService.Input(1L);

    // when & then
    assertThatCode(() -> inputValidator.getAppliedLectureServiceInputValidator(input))
        .doesNotThrowAnyException();
  }
}