package com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.sparta.hhplusw02cleanarchitecture.common.InputException;
import com.sparta.hhplusw02cleanarchitecture.common.InputValidator;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.LectureQueryRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetAppliedLecturesServiceTest {

  @Mock
  private LectureQueryRepository lectureQueryRepository;
  @Mock
  private InputValidator inputValidator;

  @InjectMocks
  private GetAppliedLecturesService getAppliedLecturesService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("사용자는 특정 userId 로 신청 완료된 특강 목록을 조회할 수 있다.")
  public void testGetLectures() {
    // Given
    Long userId = 1L;
    LocalDate date = LocalDate.of(2024, 10, 3);

    List<LectureInfo> lectureInfos = List.of(
        new LectureInfo(1L, 1L, "2주차 특강", "홍길동", date, 30, 10),
        new LectureInfo(2L, 2L, "3주차 특강", "김철수", date.plusWeeks(1), 25, 15),
        new LectureInfo(3L, 3L, "4주차 특강", "이영희", date.plusWeeks(2), 20, 20)
    );
    when(lectureQueryRepository.findLectureInfosByUserId(userId)).thenReturn(lectureInfos);

    GetAppliedLecturesService.Input input = new GetAppliedLecturesService.Input(userId);

    // When
    GetAppliedLecturesService.Output output = getAppliedLecturesService.getAppliedLectures(input);

    // Then
    assertThat(output.getList()).hasSize(3);
  }

  @Test
  @DisplayName("입력된 사용자 id가 유효하지 않으면 예외가 발생한다.")
  public void intputInvalidUserId_Then_InputException() {
    // given
    Long invalidUserId = null; // 유효하지 않은 날짜
    GetAppliedLecturesService.Input input = new GetAppliedLecturesService.Input(invalidUserId);
    doThrow(new InputException("유효하지 않은 사용자 ID입니다."))
        .when(inputValidator).getAppliedLectureServiceInputValidator(input);

    //  when & then
    InputException exception = assertThrows(InputException.class, () -> {
      getAppliedLecturesService.getAppliedLectures(input);
    }); //예외발생 확인

    assertEquals("유효하지 않은 사용자 ID입니다.", exception.getMessage()); // 메시지 확인
  }
}