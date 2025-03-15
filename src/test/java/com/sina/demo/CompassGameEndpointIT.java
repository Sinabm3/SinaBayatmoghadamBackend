package com.sina.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.demo.endpoint.dto.CompassGameDto;
import com.sina.demo.endpoint.dto.QuestionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CompassGameEndpointIT {
	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;
	@Autowired
	CompassGameEndpointIT(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	void getDefaultGameReturns200Test() throws Exception {
		mockMvc.perform(get("/compassGame/1")).andExpect(status().isOk()).
				andExpect(content().
						json("{\"id\":1,\"name\":\"Default Compass Game\"}"));
	}
	@Test
	void createAndDeleteTest() throws Exception{
		String id = mockMvc.perform((post("/compassGame").contentType("application/json").content(
				"{\"name\":\"Test Game\",\"horizontalAxisPositiveName\":\"Right\",\"horizontalAxisNegativeName\":\"Left\"," +
						"\"verticalAxisPositiveName\":\"Up\",\"verticalAxisNegativeName\":\"Down\",\"password\":\"123456\"," +
						"\"questionDtos\":[{\"text\":\"Test Question\",\"isHorizontal\":true,\"axisPower\":1}]}")))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
		mockMvc.perform(delete("/compassGame/"+id+"?password=123456")).andExpect(status().isOk());
	}

	@Test
	void editAndDeleteTest() throws Exception {
		String id = mockMvc.perform((post("/compassGame").contentType("application/json").content(
				"{\"name\":\"Test Game\",\"horizontalAxisPositiveName\":\"Right\",\"horizontalAxisNegativeName\":\"Left\"," +
						"\"verticalAxisPositiveName\":\"Up\",\"verticalAxisNegativeName\":\"Down\",\"password\":\"123456\"," +
						"\"questionDtos\":[{\"text\":\"Test Question\",\"isHorizontal\":true,\"axisPower\":1}]}")))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		QuestionDto questionDto = new QuestionDto(null, "bruh", true, 1);
		HashSet<QuestionDto> questionDtos = new HashSet<>();
		questionDtos.add(questionDto);
		mockMvc.perform(put("/compassGame/"
						+id+"?password=123456").contentType("application/json").
						content(objectMapper.writeValueAsString(new CompassGameDto(null,"Test Game",
								"Right","Left",
								"Up","Down","123456",
								questionDtos))))
				.andExpect(status().isOk());
		mockMvc.perform(delete("/compassGame/"+id+"?password=123456")).andExpect(status().isOk());
	}

}
