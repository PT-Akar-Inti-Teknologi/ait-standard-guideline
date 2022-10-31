package org.ait.project.guideline.example.modules.post.controller;

import org.ait.project.guideline.example.modules.post.model.repository.JsonPlaceHolderPostRepository;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.JsonPlaceHolderClient;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JsonPlaceHolderPostControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private JsonPlaceHolderClient jsonPlaceHolderClient;

  @Autowired
  private JsonPlaceHolderPostRepository jsonPlaceHolderPostRepository;


  @Test
  void getAllJsonPlaceHolderPost() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/post")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.content().string(Matchers.containsString("response_schema")))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("response_code")))
        .andExpect(
            MockMvcResultMatchers.content().string(Matchers.containsString("response_message")));
  }

  @Test
  void getAllJsonPlaceHolderPostPage() {
  }

  @Test
  void getJsonPlaceHolderPostById() {
  }

  @Test
  void addPost() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/post")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
            "    \"title\": \"test test\",\n" +
            "    \"body\": \"testing@gmail.com\",\n" +
            "    \"userId\": \"777\"\n" +
            "}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        // code below expected to check default response
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_code").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_message").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
        // code below expected to check content response
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.title").exists())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.response_output.detail.title").value("test test"));
  }

}