package org.ait.project.guideline.example.shared.utils;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.ait.project.guideline.example.shared.constant.enums.ResponseEnum;
import org.ait.project.guideline.example.shared.dto.PaginationConfig;
import org.ait.project.guideline.example.shared.dto.ResponseCollection;
import org.ait.project.guideline.example.shared.dto.ResponseDetail;
import org.ait.project.guideline.example.shared.dto.ResponseList;
import org.ait.project.guideline.example.shared.dto.ResponseTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseHelper {

  private final ResponseMessageHelper responseMessageHelper;

  public <T> ResponseEntity<ResponseTemplate<ResponseDetail<T>>> createResponseDetail(
      ResponseEnum responseEnum, T body) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            createResponseDetailTemplate(responseEnum, body)
        );
  }

  public <T> ResponseTemplate<ResponseDetail<T>> createResponseDetailTemplate(
      ResponseEnum responseEnum, T body) {
    return ResponseTemplate.<ResponseDetail<T>>builder()
        .responseSchema(responseMessageHelper.getResponseSchema(responseEnum))
        .responseOutput(new ResponseDetail<>(body))
        .build();
  }

  public <T> ResponseEntity<ResponseTemplate<T>> createResponseError(
      ResponseEnum responseEnum, T body) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            createResponseErrorTemplate(responseEnum, body)
        );
  }

  public <T> ResponseTemplate<T> createResponseErrorTemplate(
      ResponseEnum responseEnum, T body) {
    return ResponseTemplate.<T>builder()
        .responseSchema(responseMessageHelper.getResponseSchema(responseEnum))
        .responseOutput(body)
        .build();
  }

  public <T> ResponseEntity<ResponseTemplate<ResponseList<T>>> createResponseCollection(
      ResponseEnum responseEnum, Page page,
      List<T> contents) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            createResponseTemplateCollection(responseEnum, page,contents)
        );
  }

  public <T> ResponseTemplate<ResponseList<T>> createResponseTemplateCollection(
      ResponseEnum responseEnum, Page page,
      List<T> contents) {
    return ResponseTemplate.<ResponseList<T>>builder()
        .responseSchema(responseMessageHelper.getResponseSchema(responseEnum))
        .responseOutput(new ResponseList<>(createResponseTemplateCollection(page, contents)))
        .build();
  }

  private <T> ResponseCollection<T> createResponseTemplateCollection(Page page, List<T> contents) {
    return new ResponseCollection(
        Optional.ofNullable(page).map(
            pageableData -> new PaginationConfig(page.getNumber(),
                page.getSize(),
                page.getTotalElements())
        ).orElse(null),
        contents
    );
  }
}
