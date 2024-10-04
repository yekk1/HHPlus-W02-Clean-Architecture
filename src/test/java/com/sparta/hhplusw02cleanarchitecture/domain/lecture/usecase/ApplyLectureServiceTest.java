package com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.sparta.hhplusw02cleanarchitecture.common.InputException;
import com.sparta.hhplusw02cleanarchitecture.common.InputValidator;
import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureHistoryEntity;
import com.sparta.hhplusw02cleanarchitecture.domain.entity.LectureInventoryEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.LectureQueryRepository;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureHistory.LectureHistoryRepositoryJpaImpl;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lectureInventory.LectureInventoryRepositoryJpaImpl;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

class ApplyLectureServiceTest {
  @Mock
  private LectureInventoryRepositoryJpaImpl lectureInventoryRepository;

  @Mock
  private LectureHistoryRepositoryJpaImpl lectureHistoryRepository;
  @Mock
  private InputValidator inputValidator;
  @Mock
  private LectureQueryRepository lectureQueryRepository;

  @InjectMocks
  private ApplyLectureService applyLectureService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("사용자는 특정 userId로 잔여좌석이 남은 특강을 신청할 수 있다.")
  @Transactional
  public void testApply() {
    // Given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(2L)
        .itemId(3L)
        .inventoryId(3L)
        .amount(10)
        .build();

    LectureInventoryEntity lectureInventoryEntity = LectureInventoryEntity.builder()
        .id(3L)
        .lecture_id(2L)
        .item_id(3L)
        .amount(9)
        .build();

    LectureHistoryEntity lectureHistoryEntity = LectureHistoryEntity.builder()
        .id(20L)
        .user_id(1L)
        .lecture_id(2L)
        .item_id(3L)
        .applied_at(LocalDateTime.now())
        .build();

    when(lectureQueryRepository.findLectureInfosByInventoryId(input.getInventoryId())).thenReturn(10);
    when(lectureInventoryRepository.updateAmount(any(LectureInventoryEntity.class))).thenReturn(lectureInventoryEntity);
    when(lectureHistoryRepository.saveHistory(any(LectureHistoryEntity.class))).thenReturn(lectureHistoryEntity);

    // When
    ApplyLectureService.Output output = applyLectureService.applyLecture(input);

    // Then
    assertThat(output.getInventoryId()).isEqualTo(3L);
    assertThat(output.getAmount()).isEqualTo(9);

    assertThat(output.getHistoryId()).isEqualTo(20L);
  }

  @Test
  @DisplayName("사용자는 잔여좌석이 남지않은 특강을 신청할 수 없다.")
  @Transactional
  public void whenNoSeatsAvailable_userCannotApply() {
    // Given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(2L)
        .itemId(3L)
        .inventoryId(3L)
        .build();

    when(lectureQueryRepository.findLectureInfosByInventoryId(input.getInventoryId())).thenReturn(0);

    // when & then
    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      applyLectureService.applyLecture(input);
    });
    assertThat("No available seats").isEqualTo(exception.getMessage());
  }

  @Test
  @DisplayName("사용자는 이미 신청한 특강을 신청할 수 없다.")
  @Transactional
  public void whenAreadyAppliedSeat_userCannotApplyAgain() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(2L)
        .itemId(3L)
        .inventoryId(3L)
        .build();

    when(lectureQueryRepository.findLectureInfosByInventoryId(input.getInventoryId())).thenReturn(30);
    when(lectureQueryRepository.getByUserIdAndLectureId(input.getUserId(), input.getItemId())).thenReturn(true);

    // when & then
    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      applyLectureService.applyLecture(input);
    });
    assertThat("이미 신청한 특강입니다.").isEqualTo(exception.getMessage());
  }

  @Test
  @DisplayName("input이 유효하지 않으면 예외가 발생한다.")
  public void intputInvalidData_Then_InputException() {
    // given
    ApplyLectureService.Input input = null;

    doThrow(new InputException("입력 값이 null일 수 없습니다."))
        .when(inputValidator).applyLectureServiceInputValidator(input);
    // when & then
    Exception exception = assertThrows(InputException.class, () -> {
      inputValidator.applyLectureServiceInputValidator(input);
    });
    assertEquals("입력 값이 null일 수 없습니다.", exception.getMessage());
  }

  @Test
  @DisplayName("입력된 사용자 id가 유효하지 않으면 예외가 발생한다.")
  public void intputInvalidUserId_Then_InputException() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(-1L)
        .lectureId(1L)
        .itemId(1L)
        .inventoryId(1L)
        .amount(1)
        .build();

    doThrow(new InputException("유효하지 않은 사용자 ID입니다."))
        .when(inputValidator).applyLectureServiceInputValidator(input);

    //when & then
    Exception exception = assertThrows(InputException.class, () -> {
      inputValidator.applyLectureServiceInputValidator(input);
    });
    assertEquals("유효하지 않은 사용자 ID입니다.", exception.getMessage());
  }

  @Test
  @DisplayName("입력된 특강 id가 유효하지 않으면 예외가 발생한다.")
  public void intputInvalidLectureId_Then_InputException() {
    // when
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(-1L)
        .itemId(1L)
        .inventoryId(1L)
        .amount(1)
        .build();

    doThrow(new InputException("유효하지 않은 특강 ID입니다."))
        .when(inputValidator).applyLectureServiceInputValidator(input);

    // when & then
    Exception exception = assertThrows(InputException.class, () -> {
      inputValidator.applyLectureServiceInputValidator(input);
    });
    assertEquals("유효하지 않은 특강 ID입니다.", exception.getMessage());
  }

  @Test
  @DisplayName("입력된 특강 목록 id가 유효하지 않으면 예외가 발생한다.")
  public void intputInvalidItemId_Then_InputException() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(1L)
        .itemId(-1L)
        .inventoryId(1L)
        .amount(1)
        .build();

    doThrow(new InputException("유효하지 않은 특강 목록 ID입니다."))
        .when(inputValidator).applyLectureServiceInputValidator(input);

    // when & then
    Exception exception = assertThrows(InputException.class, () -> {
      inputValidator.applyLectureServiceInputValidator(input);
    });
    assertEquals("유효하지 않은 특강 목록 ID입니다.", exception.getMessage());
  }

  @Test
  @DisplayName("입력된 특강 여석 id가 유효하지 않으면 예외가 발생한다.")
  public void intputInvalidIventoryId_Then_InputException() {
    // given
    ApplyLectureService.Input input = ApplyLectureService.Input.builder()
        .userId(1L)
        .lectureId(1L)
        .itemId(1L)
        .inventoryId(-1L)
        .amount(1)
        .build();

    doThrow(new InputException("유효하지 않은 특강 여석 ID입니다."))
        .when(inputValidator).applyLectureServiceInputValidator(input);

    // when & then
    Exception exception = assertThrows(InputException.class, () -> {
      inputValidator.applyLectureServiceInputValidator(input);
    });
    assertEquals("유효하지 않은 특강 여석 ID입니다.", exception.getMessage());
  }
}