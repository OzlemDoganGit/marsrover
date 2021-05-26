package com.hepsiburada.marsrover.dto;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;

public class RequestTest {

	@InjectMocks
	Request request;

	@Test
	public void testGet() {
		Request request = new Request();
		request.setId(1L);
		request.setParameters("2-3-N");

		assertNotNull(request);
		assertEquals(1L, request.getId());
		assertEquals("2-3-N", request.getParameters());
	}
}
