package com.hepsiburada.marsrover.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.MissingRequestValueException;

import com.hepsiburada.marsrover.domain.Coordinates;
import com.hepsiburada.marsrover.domain.Direction;
import com.hepsiburada.marsrover.domain.Instruction;
import com.hepsiburada.marsrover.domain.MarsRover;
import com.hepsiburada.marsrover.domain.Plateau;
import com.hepsiburada.marsrover.domain.Position;
import com.hepsiburada.marsrover.exception.OutOfBoundsException;
import com.hepsiburada.marsrover.repository.MarsRoverRepository;
import com.hepsiburada.marsrover.service.impl.MarsRoverServiceImpl;
import com.hepsiburada.marsrover.state.enums.Pole;
import com.hepsiburada.marsrover.unittestbase.MarsRoverTestBase;

@RunWith(SpringJUnit4ClassRunner.class)
public class MarsRoverServiceImplTest {

	@Mock
	MarsRoverRepository marsRoverRepository;

	@InjectMocks
	public MarsRoverServiceImpl marsRoverService;

	@Test
	public void testSetDimensions() throws MissingRequestValueException {
		String dimensions = "5-5";
		List<Integer> dimensionList = new ArrayList<>();
		dimensionList.add(5);
		dimensionList.add(5);
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Mockito.when(marsRoverRepository.save(Mockito.any())).thenReturn(marsRover);
		MarsRover actual = marsRoverService.setDimensions(dimensions);
		assertNotNull(actual);
	}

	@Test(expected = OutOfBoundsException.class)
	public void calculateRoverInstructions_OutOfBoundsException() throws MissingRequestValueException {
		String parameters = "MMMMM";
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Mockito.when(marsRoverRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(marsRover));
		marsRoverService.calculateRoverInstructions(parameters, 1L);

	}
	
	@Test
	public void calculateRoverInstructions() throws MissingRequestValueException {
		String parameters = "LMLR";
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Optional<MarsRover> optMarsRover = Optional.ofNullable(marsRover);
		Mockito.when(marsRoverRepository.findById(Mockito.anyLong())).thenReturn(optMarsRover);		
		Mockito.when(marsRoverRepository.save(Mockito.any())).thenReturn(marsRover);
		marsRover = marsRoverService.calculateRoverInstructions(parameters, 1L);		
		assertNotNull(marsRover);
		
	}
	
	@Test(expected = DataAccessResourceFailureException.class)
	public void calculateRoverInstructions_DataAccess() throws MissingRequestValueException {
		String parameters = "LML";
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Mockito.when(marsRoverRepository.save(Mockito.any())).thenReturn(marsRover);
		marsRoverService.calculateRoverInstructions(parameters, 1L);
	}
	
	@Test(expected = MissingRequestValueException.class)
	public void calculateRoverInstructions_MissingRequestValueException() throws MissingRequestValueException {
		String parameters = "LMA";
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Optional<MarsRover> optMarsRover = Optional.ofNullable(marsRover);
		Mockito.when(marsRoverRepository.findById(Mockito.anyLong())).thenReturn(optMarsRover);		
		Mockito.when(marsRoverRepository.save(Mockito.any())).thenReturn(marsRover);
		marsRoverService.calculateRoverInstructions(parameters, 1L);	
	}
	
	@Test
	public void testRoverPositionWithoutID() throws MissingRequestValueException {
		String parameters = "1-2-N";
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Optional<MarsRover> optMarsRover = Optional.ofNullable(marsRover);
		Mockito.when(marsRoverRepository.findById(Mockito.anyLong())).thenReturn(optMarsRover);
		Mockito.when(marsRoverRepository.save(Mockito.any())).thenReturn(marsRover);
		marsRover = marsRoverService.setRoverPosition(parameters);
		assertNotNull(marsRover);
	}
	
	@Test
	public void testRoverPositionWitID() throws MissingRequestValueException {
		String parameters = "1-2-N";
		Long id = 1L;
		MarsRoverTestBase marsRoverTestBase = new MarsRoverTestBase();
		MarsRover marsRover = marsRoverTestBase.generateMockMarsRover();
		Optional<MarsRover> optMarsRover = Optional.ofNullable(marsRover);
		Mockito.when(marsRoverRepository.findById(Mockito.anyLong())).thenReturn(optMarsRover);
		Mockito.when(marsRoverRepository.save(Mockito.any())).thenReturn(marsRover);
		marsRover = marsRoverService.setRoverPosition(parameters,id);
		assertNotNull(marsRover);
	}
}
