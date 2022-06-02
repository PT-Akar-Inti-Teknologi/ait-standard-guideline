package org.ait.project.guideline.example.modules.post.service.delegate;

import java.util.List;
import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;

public interface JsonPlaceHolderPostDelegate {
  List<JsonPlaceHolderPost> getAllPost();

  JsonPlaceHolderPost getPostById(Integer id);

  List<JsonPlaceHolderPost> saveAll(List<JsonPlaceHolderPost> jsonPlaceHolderPostList);

  JsonPlaceHolderPost save(JsonPlaceHolderPost jsonPlaceHolderPost);
}
