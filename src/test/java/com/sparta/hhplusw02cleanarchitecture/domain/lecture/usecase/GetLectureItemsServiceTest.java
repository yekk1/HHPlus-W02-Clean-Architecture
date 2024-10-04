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

//@SpringBootTest
class GetLectureItemsServiceTest {
  @Mock
  private LectureQueryRepository lectureQueryRepository;
  @Mock
  private InputValidator inputValidator;
  @InjectMocks
  private GetLectureItemsService getLectureItemsService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("사용자는 특정 날짜의 강의목록을 조회할 수 있다.")
  public void testGetLectures() {
    // Given
    LocalDate date = LocalDate.of(2024, 10, 3);
    List<LectureInfo> lectureInfos = List.of(
        new LectureInfo(1L, 1L, "2주차 특강", "허재", date, 30, 10),
        new LectureInfo(2L, 2L, "2주차 멘토링 특강", "로이", date.plusWeeks(1), 30, 15)
        );
    when(lectureQueryRepository.findLectureInfosByDate(date)).thenReturn(lectureInfos);

    GetLectureItemsService.Input input = new GetLectureItemsService.Input(date);

    // When
    GetLectureItemsService.Output output = getLectureItemsService.getLectures(input);

    // Then
    assertThat(output.getList()).hasSize(2);
  }

  @Test
  @DisplayName("입력된 날짜가 유효하지 않으면 예외가 발생한다.")
  public void intputInvalidDate_Then_InputException() {
    // given
    LocalDate invalidDate = null; // 유효하지 않은 날짜
    GetLectureItemsService.Input input = new GetLectureItemsService.Input(invalidDate);
    doThrow(new InputException("유효하지 않은 날짜입니다."))
        .when(inputValidator).getLectureItemsServiceInputValidator(input);

    //  when & then
    InputException exception = assertThrows(InputException.class, () -> {
      getLectureItemsService.getLectures(input);
    }); //예외발생 확인

    assertEquals("유효하지 않은 날짜입니다.", exception.getMessage()); // 메시지 확인
  }

}