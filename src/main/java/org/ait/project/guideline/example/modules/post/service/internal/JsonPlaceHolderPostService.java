package org.ait.project.guideline.example.modules.post.service.internal;

import org.ait.project.guideline.example.modules.post.dto.request.PostReq;
import org.ait.project.guideline.example.modules.post.dto.response.JsonPlaceHolderPostResponse;
import org.ait.project.guideline.example.shared.dto.ResponseCollection;
import org.ait.project.guideline.example.shared.dto.ResponseTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface JsonPlaceHolderPostService {

  ResponseEntity<ResponseTemplate<ResponseCollection<JsonPlaceHolderPostResponse>>> getAllJsonPlaceHolderPost();

  ResponseEntity<ResponseTemplate<ResponseCollection<JsonPlaceHolderPostResponse>>> getAllJsonPlaceHolderPostPage(
      Pageable pageable);

  ResponseEntity<ResponseTemplate<JsonPlaceHolderPostResponse>> getJsonPlaceHolderPostById(
      Integer id);

  ResponseEntity<ResponseTemplate<JsonPlaceHolderPostResponse>> addPost(PostReq postReq);
}
