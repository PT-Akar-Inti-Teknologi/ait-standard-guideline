package org.ait.project.guideline.example.config.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ait.project.guideline.example.shared.constant.enums.ResponseEnum;
import org.ait.project.guideline.example.shared.dto.ResponseTemplate;
import org.ait.project.guideline.example.shared.utils.ResponseHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

  private final ResponseHelper responseHelper;

  @ExceptionHandler(ModuleException.class)
  public <T> ResponseEntity<ResponseTemplate<T>> handleException(ModuleException exception) {
    return responseHelper.createResponse(exception.getResponseEnum(), null);
  }

  @ExceptionHandler(Exception.class)
  public <T> ResponseEntity<ResponseTemplate<T>> handleException(Exception ex,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response) {
    Arrays.stream(ex.getStackTrace()).limit(5).forEach(logger::error);
    logger.error(ex.getMessage());
    return responseHelper.createResponse(ResponseEnum.INTERNAL_SERVER_ERROR, null);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    Map<String, Object> returnError = new HashMap<>();

    ex.getFieldErrors().forEach(
        fieldError -> returnError.put(fieldError.getField(), fieldError.getDefaultMessage()));

    return ResponseEntity
        .ok(responseHelper.createResponseTemplate(ResponseEnum.INVALID_PARAM, returnError));
  }
}
