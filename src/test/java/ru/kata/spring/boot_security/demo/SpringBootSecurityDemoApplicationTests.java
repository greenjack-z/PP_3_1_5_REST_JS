package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.kata.spring.boot_security.demo.controller.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootSecurityDemoApplicationTests {

	@Autowired
	private Controller controller;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

	@Test
	void rootAcceptAll() throws Exception {
		this.mvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	void indexAcceptAll() throws Exception {
		this.mvc.perform(get("/index"))
				.andExpect(status().isOk());
	}

	@Test
	void anyForbidden() throws Exception {
		this.mvc.perform(get("/any"))
				.andExpect(status().isForbidden());
	}

	@WithMockUser
	@Test
	void anyAcceptAuthorized() throws Exception {
		this.mvc.perform(get("/any"))
				.andExpect(status().isUnauthorized());
	}

}
