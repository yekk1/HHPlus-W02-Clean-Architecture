package com.sparta.hhplusw02cleanarchitecture.infrastructure.entity;

import com.sparta.hhplusw02cleanarchitecture.domain.common.TimeBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 특강 기본 정보 엔티티
 */
@Entity
@Table(name = "LECTURE")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LectureEntity extends TimeBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "lecture_id")
  private Long id;

  private String title;

  private String instructor;
}
