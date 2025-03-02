package com.sina.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CompassGameEndpointIT {
	private final MockMvc mockMvc;
	@Autowired
	CompassGameEndpointIT(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
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

}
