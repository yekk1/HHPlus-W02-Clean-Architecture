package com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.LectureInfo;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture.LectureQueryRepository;
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

    GetLectureItemsService.Input input = new GetLectureItemsService.Input();
    input.setDate(date);

    // When
    GetLectureItemsService.Output output = getLectureItemsService.getLectures(input);

    // Then
    assertThat(output.getList()).hasSize(2);
  }
}