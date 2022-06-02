package org.ait.project.guideline.example.shared.utils;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.ait.project.guideline.example.shared.constant.enums.ResponseEnum;
import org.ait.project.guideline.example.shared.dto.PaginationConfig;
import org.ait.project.guideline.example.shared.dto.ResponseCollection;
import org.ait.project.guideline.example.shared.dto.ResponseTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseHelper {

  private final ResponseMessageHelper responseMessageHelper;

  public <T> ResponseEntity<ResponseTemplate<T>> createResponse(ResponseEnum responseEnum, T body) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            createResponseTemplate(responseEnum, body)
        );
  }

  public <T> ResponseTemplate<T> createResponseTemplate(ResponseEnum responseEnum, T body) {
    return ResponseTemplate.<T>builder()
        .responseSchema(responseMessageHelper.getResponseSchema(responseEnum))
        .responseOutput(body)
        .build();
  }

  public <T> ResponseTemplate<ResponseCollection<T>> createResponseCollection(
      ResponseEnum responseEnum, Page page,
      List<T> contents) {
    return ResponseTemplate.<ResponseCollection<T>>builder()
        .responseSchema(responseMessageHelper.getResponseSchema(responseEnum))
        .responseOutput(createResponseCollection(page, contents))
        .build();
  }

  private <T> ResponseCollection<T> createResponseCollection(Page page, List<T> contents) {
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
