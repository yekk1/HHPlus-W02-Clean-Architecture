package com.sparta.hhplusw02cleanarchitecture.interfaces.api;

import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.ApplyLectureService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.GetAppliedLecturesService;
import com.sparta.hhplusw02cleanarchitecture.domain.lecture.usecase.GetLectureItemsService;
import com.sparta.hhplusw02cleanarchitecture.interfaces.ApiResponse;
import com.sparta.hhplusw02cleanarchitecture.interfaces.request.ApplyLectureRequest;
import com.sparta.hhplusw02cleanarchitecture.interfaces.response.GetLectureListResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 특강 신청 서비스 컨트롤러
 *
 * 💡 **KEY POINT**
 * - 정확하게 30 명의 사용자에게만 특강을 제공할 방법을 고민해 봅니다.
 * - 같은 사용자에게 여러 번의 특강 슬롯이 제공되지 않도록 제한할 방법을 고민해 봅니다.
 */

// TODO - API 명세에 맞춰서 MockAPI로 먼저 개발환경에 배포 <-서버 개발의 1순위
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

  private final ApplyLectureService applyLectureService;
  private final GetLectureItemsService getLectureItemsService;
  private final GetAppliedLecturesService getAppliedLecturesService;

  /**
   * 특강 신청 API
   * - 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
   * - 동일한 신청자는 동일한 강의에 대해서 한 번의 수강 신청만 성공할 수 있습니다.
   * - 특강은 선착순 30명만 신청 가능합니다.
   * - 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
   */
  @PostMapping("/{userId}/apply")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<Long> applyLecture (
      @PathVariable Long userId,
      @RequestBody ApplyLectureRequest request
  ){
    log.debug("LectureController#applyLecture called.");
    log.debug("userId={}", userId);
    log.debug("ApplyLectureRequest={}", request);

    Long savedHistoryId = applyLectureService.applyLecture(request.toInputDTO()).getHistoryId();

    log.debug("savedHistoryId={}", savedHistoryId);
    return ApiResponse.created(savedHistoryId);
  }

  /**
   * 특강 선택 API
   * - 날짜별로 현재 신청 가능한 특강 목록을 조회하는 API 를 작성합니다.
   * - 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
   */
  @GetMapping("/lectures")
  public ApiResponse<List<GetLectureListResponse>> getLectures (@RequestParam LocalDate date){
    log.debug("LectureController#getLectures called.");
    log.debug("date={}", date);

    GetLectureItemsService.Output output = getLectureItemsService.getLectures(
        new GetLectureItemsService.Input(date)
    );

    List<GetLectureListResponse> responseList = output.getList().stream()
        .map(lectureInfo -> new GetLectureListResponse(lectureInfo))
        .collect(Collectors.toList());
    log.debug("responseList={}", responseList);

    return ApiResponse.ok(responseList);
  }


  /**
   * 특강 신청 완료 목록 조회 API
   * - 특정 userId 로 신청 완료된 특강 목록을 조회하는 API 를 작성합니다.
   * - 각 항목은 특강 ID 및 이름, 강연자 정보를 담고 있어야 합니다.
   * @param userId 조회할 유저 ID
   */
  @GetMapping("/{userId}")
  public ApiResponse<List<GetLectureListResponse>> getCompletedLectures (@PathVariable Long userId){
    log.debug("LectureController#getCompletedLectures called.");
    log.debug("userId={}", userId);

    GetAppliedLecturesService.Output output = getAppliedLecturesService.getAppliedLectures(
        new GetAppliedLecturesService.Input(userId)
    );

    List<GetLectureListResponse> responseList = output.getList().stream()
        .map(lectureInfo -> new GetLectureListResponse(lectureInfo))
        .collect(Collectors.toList());

    log.debug("responseList={}", responseList);

    return ApiResponse.ok(responseList);
  }
}
