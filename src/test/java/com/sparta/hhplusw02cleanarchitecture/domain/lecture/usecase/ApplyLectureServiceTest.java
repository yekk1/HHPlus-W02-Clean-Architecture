package com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.LectureHistoryEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.entity.LectureInventoryEntity;
import com.sparta.hhplusw02cleanarchitecture.infrastructure.repository.lecture.LectureQueryRepository;
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
}