package com.sparta.hhplusw02cleanarchitecture.domain.entity;

import com.sparta.hhplusw02cleanarchitecture.domain.common.TimeBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 특강 신청 내역 엔티티
 */
@Entity
@Table(name = "LECTURE_HISTORY")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LectureHistoryEntity extends TimeBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "history_id")
  private Long id;

  @Column(nullable = false)
  private Long user_id;

  @Column(nullable = false)
  private Long lecture_id;

  @Column(nullable = false)
  private Long item_id;

  @Column(nullable = false)
  private LocalDateTime applied_at;

}
