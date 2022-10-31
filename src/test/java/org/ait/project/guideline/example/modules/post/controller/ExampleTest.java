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
public class ExampleTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void template_post() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/post")
        .contentType(MediaType.APPLICATION_JSON)
        .content("isijsondisini"))
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
