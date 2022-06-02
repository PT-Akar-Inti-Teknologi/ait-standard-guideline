package org.ait.project.guideline.example.modules.post.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ait.project.guideline.example.modules.post.dto.request.PostReq;
import org.ait.project.guideline.example.modules.post.dto.response.JsonPlaceHolderPostResponse;
import org.ait.project.guideline.example.modules.post.service.internal.JsonPlaceHolderPostService;
import org.ait.project.guideline.example.shared.dto.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class JsonPlaceHolderPostController implements JsonPlaceHolderPostService{

  private final JsonPlaceHolderPostService postService;

  @Override
  @GetMapping
  public ResponseEntity<ResponseTemplate<List<JsonPlaceHolderPostResponse>>> getAllJsonPlaceHolderPost() {
    return postService.getAllJsonPlaceHolderPost();
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<ResponseTemplate<JsonPlaceHolderPostResponse>> getJsonPlaceHolderPostById(
      @PathVariable Integer id) {
    return postService.getJsonPlaceHolderPostById(id);
  }

  @Override
  @PostMapping
  public ResponseEntity<ResponseTemplate<JsonPlaceHolderPostResponse>> addPost(
      @Valid
      @RequestBody
          PostReq postReq) {
    return postService.addPost(postReq);
  }
}
