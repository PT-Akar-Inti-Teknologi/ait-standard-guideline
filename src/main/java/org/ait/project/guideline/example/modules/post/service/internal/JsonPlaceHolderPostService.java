package org.ait.project.guideline.example.modules.post.service.internal;

import java.util.List;
import org.ait.project.guideline.example.modules.post.dto.request.PostReq;
import org.ait.project.guideline.example.modules.post.dto.response.JsonPlaceHolderPostResponse;
import org.ait.project.guideline.example.shared.dto.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface JsonPlaceHolderPostService {

  ResponseEntity<ResponseTemplate<List<JsonPlaceHolderPostResponse>>> getAllJsonPlaceHolderPost();

  ResponseEntity<ResponseTemplate<JsonPlaceHolderPostResponse>> getJsonPlaceHolderPostById(
      Integer id);

  ResponseEntity<ResponseTemplate<JsonPlaceHolderPostResponse>> addPost(PostReq postReq);
}
