package com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

}