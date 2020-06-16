package com.nooty.nootyhashtag;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HashtagController.class)
class NootyHashtagApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HashtagRepo hashtagRepo;

	@MockBean
	private HashtagNootRepo hashtagNootRepo;


	@Test
	void contextLoads() {
	}

}
