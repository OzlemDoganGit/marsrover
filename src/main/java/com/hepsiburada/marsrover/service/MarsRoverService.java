package com.hepsiburada.marsrover.service;

import org.springframework.web.bind.MissingRequestValueException;

import com.hepsiburada.marsrover.domain.MarsRover;

public interface MarsRoverService {
	
	public MarsRover setDimensions(String dimensions) throws MissingRequestValueException;

	public MarsRover calculateRoverInstructions(String parameters, long integer) throws MissingRequestValueException;

	public MarsRover setRoverPosition(String parameters, long integer) throws MissingRequestValueException;

	public MarsRover setRoverPosition(String parameters) throws MissingRequestValueException;

}
