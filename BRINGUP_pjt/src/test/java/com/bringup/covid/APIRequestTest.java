package com.bringup.covid;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/common/*.xml")
public class APIRequestTest {
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void init() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void area() throws Exception {
		mvc.perform(get("/covid/local").param("end", "2020-04-11")).andExpect(status().isOk());
		mvc.perform(get("/covid/local").param("end", "2020-04-10")).andExpect(status().isBadRequest());
		mvc.perform(get("/covid/local").param("end", "2020-04-09")).andExpect(status().isBadRequest());
	}

}
