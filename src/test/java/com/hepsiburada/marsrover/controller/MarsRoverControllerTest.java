package com.hepsiburada.marsrover.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingRequestValueException;

import com.hepsiburada.marsrover.domain.Coordinates;
import com.hepsiburada.marsrover.domain.Direction;
import com.hepsiburada.marsrover.domain.Instruction;
import com.hepsiburada.marsrover.domain.MarsRover;
import com.hepsiburada.marsrover.domain.Plateau;
import com.hepsiburada.marsrover.domain.Position;
import com.hepsiburada.marsrover.dto.Request;
import com.hepsiburada.marsrover.dto.Response;
import com.hepsiburada.marsrover.service.MarsRoverService;
import com.hepsiburada.marsrover.state.enums.Pole;
import com.hepsiburada.marsrover.unittestbase.MarsRoverTestBase;

@RunWith(SpringJUnit4ClassRunner.class)
public class MarsRoverControllerTest {

	@Mock
	MarsRoverService marsRoverService;

	@InjectMocks
	MarsRoverController marsRoverController;

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(marsRoverController).build();
	}

	@Test
	public void testSetDimensions() throws MissingRequestValueException {
		Request request = new Request();
		request.setParameters("5-5");
		request.setId(1L);
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		marsRoverService.setDimensions(request.getParameters());
		Mockito.when(marsRoverService.setDimensions(Mockito.anyString())).thenReturn(marsRover);
		ResponseEntity<MarsRover> actual = marsRoverController.setDimensionsOfPlateau(request);
		assertNotNull(actual);
	}

	@Test
	public void testSetRoverInitialPosition() throws MissingRequestValueException {
		Request request = new Request();
		request.setParameters("1-2-N");
		request.setId(1L);
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Mockito.when(marsRoverService.setRoverPosition(Mockito.anyString(), Mockito.anyLong())).thenReturn(marsRover);
		ResponseEntity<MarsRover> actual = marsRoverController.setRoverPosition(request);
		assertNotNull(actual);
	}

	@Test
	public void testSetRoverInitialPositionWithoutId() throws MissingRequestValueException {
		Request request = new Request();
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Mockito.when(marsRoverService.setRoverPosition(Mockito.anyString())).thenReturn(marsRover);
		ResponseEntity<MarsRover> actual = marsRoverController.setRoverPosition(request);
		assertNotNull(actual);
	}


	@Test
	public void testSetInstructions() throws MissingRequestValueException {
		Request request = new Request();
		request.setParameters("LMLMLMLMMR");
		request.setId(1L);
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Mockito.when(marsRoverService.calculateRoverInstructions(Mockito.anyString(), Mockito.anyLong()))
				.thenReturn(marsRover);
		ResponseEntity<Response> actual = marsRoverController.setRoverInstructions(request);
		assertNotNull(actual);
	}



}
