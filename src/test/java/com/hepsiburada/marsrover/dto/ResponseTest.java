package com.hepsiburada.marsrover.dto;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;

public class ResponseTest {

	@InjectMocks 
	Response response;
	
	@Test
	public void testGet()
	{
		Response response = new Response();
		response.setId(1L);
		response.setResultPosition("2-3-N");
		assertNotNull(response);	
		assertEquals(1L, response.getId());
		assertEquals("2-3-N", response.getResultPosition());
	}
}
