//package com.roman.app.rest;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//@SpringBootTest(
//		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//		classes = RestApiApplication.class)
//@AutoConfigureMockMvc
//@TestPropertySource(
//		locations = "classpath:application-integrationtest.properties")
//class RestApiApplicationTests {
//
//	@Test
//	void getPage_thenReturnWelcome()
//		throws Exception {
//
//
//			mvc.perform(get("/")
//							.contentType(MediaType.APPLICATION_JSON))
//					.andExpect(status().isOk())
//					.andExpect(content()
//							.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//					.andExpect(jsonPath("$[0].name", is("bob")));
//		}
//}
