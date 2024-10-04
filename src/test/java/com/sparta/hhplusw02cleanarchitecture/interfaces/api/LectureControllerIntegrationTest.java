package com.sparta.hhplusw02cleanarchitecture.interfaces.api;

import static org.assertj.core.api.Assertions.assertThat;
import com.sparta.hhplusw02cleanarchitecture.interfaces.ApiResponse;
import com.sparta.hhplusw02cleanarchitecture.interfaces.request.ApplyLectureRequest;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LectureControllerIntegrationTest {

  /**
   * 테스트 실행 전 실행되는 쿼리 INSERT INTO LECTURE (LECTURE_ID, TITLE, INSTRUCTOR) VALUES (1, '과제발제', '허재')
   * INSERT INTO LECTURE (LECTURE_ID, TITLE, INSTRUCTOR) VALUES (2, '멘토링', '로이') INSERT INTO
   * LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (1, 1, '2024-09-28', 30) INSERT INTO
   * LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (2, 1, '2024-10-02', 30) INSERT INTO
   * LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (3, 2, '2024-10-02', 30) INSERT INTO
   * LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (4, 2, '2024-10-03', 30) INSERT INTO
   * LECTURE_ITEM (ITEM_ID, LECTURE_ID, DATE, CAPACITY) VALUES (5, 2, '2024-10-04', 30) INSERT INTO
   * LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (1, 1, 1, 30) INSERT INTO
   * LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (2, 2, 1, 30) INSERT INTO
   * LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (3, 3, 2, 30) INSERT INTO
   * LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (4, 4, 2, 30) INSERT INTO
   * LECTURE_INVENTORY (INVENTORY_ID, ITEM_ID, LECTURE_ID, AMOUNT) VALUES (5, 5, 2, 30)
   */
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("특강 하나를 신청한다.")
  public void testApplyLecture() throws Exception {
    //given
    Long userId = 1L;
    ApplyLectureRequest request = ApplyLectureRequest.builder()
        .userId(userId)
        .lectureId(1L)
        .itemId(1L)
        .inventoryId(1L)
        .build();

    // when
    ResponseEntity<ApiResponse> response = restTemplate.postForEntity(
        "/lecture/" + userId + "/apply",
        request,
        ApiResponse.class
    );

    // then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED); // 응답이 CREATED인지 확인
    assertThat(response.getBody().getData()).isNotNull();               // 응답데이터가 null이 아닌지 확인
//    assertThat(response.getBody().getData()).isEqualTo(2);     // 응답데이터가 제대로 들어왔는지 확인
  }

  @Test
  @DisplayName("툭정 날짜의 특강 목록을 조회한다.")
  public void testGetLectures() throws Exception {
    //given
    LocalDate date = LocalDate.of(2024, 10, 2);

    //when
    ResponseEntity<ApiResponse> response = restTemplate.getForEntity(
        "/lecture/lectures?date=" + date.toString(),
        ApiResponse.class
    );

    //then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); //응답이 OK인지 확인
    assertThat(response.getBody()).isNotNull();                    //응답 데이터가 null이 아닌지 확인
    assertThat((List<?>) response.getBody().getData())
        .hasSize(2);                                      //응답 데이터 사이즈가 2인지 확인
  }

  @Test
  @DisplayName("특정 userId로 등록한 특강 목록을 조회한다.")
  public void testGetCompletedLectures() throws Exception {
    //given: 특강 신청
    Long userId = 3L;
    ApplyLectureRequest request = ApplyLectureRequest.builder()
        .userId(userId)
        .lectureId(1L)
        .itemId(1L)
        .inventoryId(1L)
        .build();

    restTemplate.postForEntity(
        "/lecture/" + userId + "/apply",
        request,
        ApiResponse.class
    );

    //when
    ResponseEntity<ApiResponse> response = restTemplate.getForEntity(
        "/lecture/" + userId.toString(),
        ApiResponse.class
    );

    //then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); //응답이 OK인지 확인
    assertThat(response.getBody()).isNotNull();                    //응답 데이터가 null이 아닌지 확인
    assertThat((List<?>) response.getBody().getData())
        .hasSize(1);                                      //응답 데이터 사이즈가 1인지 확인
  }
}