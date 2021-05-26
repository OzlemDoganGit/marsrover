package com.hepsiburada.marsrover.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hepsiburada.marsrover.domain.MarsRover;
import com.hepsiburada.marsrover.dto.Request;
import com.hepsiburada.marsrover.dto.Response;
import com.hepsiburada.marsrover.service.MarsRoverService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "marsrover-api")
public class MarsRoverController {

	private final MarsRoverService marsRoverService;

	@Autowired
	public MarsRoverController(@Qualifier("marsRoverSerivce") MarsRoverService marsRoverService) {
		this.marsRoverService = marsRoverService;
	}

	@PostMapping(value = "/setDimensionsOfPlateau", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "", notes = "Set Plateau dimensions", nickname = "setDimensionsOfPlateau")
	public ResponseEntity<MarsRover> setDimensionsOfPlateau(@RequestBody Request requestDTO)
			throws MissingRequestValueException {
		MarsRover marsRover = marsRoverService.setDimensions(requestDTO.getParameters());
		return new ResponseEntity<>(marsRover, HttpStatus.OK);
	}

	@PostMapping(value = "/setRoverPosition", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "", notes = "Set Rover Position", nickname = "setRoverPosition")
	public ResponseEntity<MarsRover> setRoverPosition(@RequestBody Request requestDTO)
			throws MissingRequestValueException {
		Optional<Long> optRequestId = Optional.ofNullable(requestDTO.getId());
		if (optRequestId.isPresent()) {
			MarsRover marsRover = marsRoverService.setRoverPosition(requestDTO.getParameters(), requestDTO.getId());
			return new ResponseEntity<>(marsRover, HttpStatus.OK);
		} else {
			MarsRover marsRover = marsRoverService.setRoverPosition(requestDTO.getParameters());
			return new ResponseEntity<>(marsRover, HttpStatus.OK);
		}
		
	}

	@PostMapping(value = "/setRoverInstruction", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "", notes = "Set Rover Instructions", nickname = "setRoverInstruction")
	public ResponseEntity<Response> setRoverInstructions(@RequestBody Request requestDTO)
			throws MissingRequestValueException {
		MarsRover marsRover = marsRoverService.calculateRoverInstructions(requestDTO.getParameters(),
				requestDTO.getId());
		Response response = new Response();
		response.setId(marsRover.getId());
		response.setResultPosition(String.valueOf(marsRover.getResultPosition().getCoordinates().getXCoordinate())
				.concat("-").concat(String.valueOf(marsRover.getResultPosition().getCoordinates().getYCoordinate()))
				.concat("-").concat(marsRover.getResultPosition().getDirection().getPole().name()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
