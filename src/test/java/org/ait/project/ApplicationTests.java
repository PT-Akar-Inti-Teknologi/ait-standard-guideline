package org.ait.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void template_post() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content("isi json disni"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// code below expected to check default response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_message").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
				// code below expected to check content response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.access_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.refresh_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.token_type").exists())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.response_output.detail.title").value("test test"));


	}

	@Test
	void template_get() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content("isi json disni"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// code below expected to check default response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_message").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
				// code below expected to check content response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.access_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.refresh_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.token_type").exists())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.response_output.detail.title").value("test test"));


	}

	@Test
	void template_put() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content("isi json disni"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// code below expected to check default response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_message").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
				// code below expected to check content response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.access_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.refresh_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.token_type").exists())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.response_output.detail.title").value("test test"));


	}

	@Test
	void template_delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content("isi json disni"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// code below expected to check default response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.response_message").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
				// code below expected to check content response
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.access_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.refresh_token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.token_type").exists())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.response_output.detail.title").value("test test"));


	}

}
