package com.hepsiburada.marsrover.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingRequestValueException;

import com.hepsiburada.marsrover.domain.Coordinates;
import com.hepsiburada.marsrover.domain.Direction;
import com.hepsiburada.marsrover.domain.Instruction;
import com.hepsiburada.marsrover.domain.MarsRover;
import com.hepsiburada.marsrover.domain.Plateau;
import com.hepsiburada.marsrover.domain.Position;
import com.hepsiburada.marsrover.exception.OutOfBoundsException;
import com.hepsiburada.marsrover.repository.MarsRoverRepository;
import com.hepsiburada.marsrover.service.MarsRoverService;
import com.hepsiburada.marsrover.state.enums.Pole;
import com.hepsiburada.marsrover.state.enums.RoverCommandEnum;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
@Qualifier("marsRoverSerivce")
public class MarsRoverServiceImpl implements MarsRoverService {

	@Autowired
	MarsRoverRepository marsRoverRepository;

	@Override
	public MarsRover setDimensions(String dimensions) throws MissingRequestValueException {
		List<Integer> dimensionList = validateAndConvertDimension(dimensions);
		Plateau plateau = Plateau.builder().coordinates(
				Coordinates.builder().xCoordinate(dimensionList.get(0)).yCoordinate(dimensionList.get(1)).build())
				.creationTime(LocalDateTime.now()).build();
		MarsRover marsRover = MarsRover.builder().plateau(plateau).build();
		return marsRoverRepository.save(marsRover);

	}

	@Override
	public MarsRover calculateRoverInstructions(String parameters, long id) throws MissingRequestValueException {
		List<Instruction> instructionList = validateAndConvertInstructions(parameters);
		Optional<MarsRover> marsRover = marsRoverRepository.findById(id);
		if (marsRover.isPresent()) {
			Position currentPosition = marsRover.get().getInitialPosition();
			for (Instruction instruction : instructionList) {
				marsRover.get().addInstructions(instruction);
				Position updatedPostion = calculateNextPosition(marsRover.get().getPlateau().getCoordinates(),
						currentPosition, instruction);
				currentPosition = updatedPostion;
			}
			marsRover.get().setResultPosition(currentPosition);
			return marsRoverRepository.save(marsRover.get());
		} else

		{
			throw new DataAccessResourceFailureException("No related record was found!");
		}
	}

	private Position calculateNextPosition(Coordinates plateauCoordinates, Position position, Instruction inst) {
		if (isXCoordinateOutsideBounds(plateauCoordinates.getXCoordinate(), position.getCoordinates().getXCoordinate())
				|| isYCoordinateOutsideBounds(plateauCoordinates.getYCoordinate(),
						position.getCoordinates().getYCoordinate())) {
			throw new OutOfBoundsException();
		}
		return RoverCommandEnum.valueOf(inst.getCommand()).execute(position.getCoordinates(), position.getDirection());

	}

	private boolean isXCoordinateOutsideBounds(int plateauXLength, final int xCoordinate) {
		return xCoordinate > plateauXLength;
	}

	private boolean isYCoordinateOutsideBounds(int plateauYLength, final int yCoordinate) {
		return yCoordinate > plateauYLength;
	}

	@Override
	public MarsRover setRoverPosition(String parameters, long id) throws MissingRequestValueException {

		Position position = validateAndConvertPosition(parameters);
		Optional<MarsRover> marsRover = marsRoverRepository.findById(id);

		if (marsRover.isPresent()) {
			Optional<Plateau> plateau = Optional.ofNullable(marsRover.get().getPlateau());
			if(!plateau.isPresent())
			{
				marsRoverRepository.findById(marsRoverRepository.count()).ifPresent(mR ->
				marsRover.get().setPlateau(mR.getPlateau()));
			}
			marsRover.get().setInitialPosition(position);
			return marsRoverRepository.save(marsRover.get());
		} else {
			throw new DataAccessResourceFailureException("No related marsRover record was found!");
		}

	}

	private List<Integer> validateAndConvertDimension(String dimensions) throws MissingRequestValueException {
		String[] dimensionList = dimensions.split("-");
		List<Integer> convertedDimensionList = new ArrayList<>();
		if (dimensionList.length >= 3 || dimensionList.length <= 1) {
			throw new MissingRequestValueException("Type dimension in the correct format!");
		}
		for (String dimension : dimensionList) {
			convertedDimensionList.add(Integer.valueOf(dimension));
		}
		return convertedDimensionList;
	}

	private Position validateAndConvertPosition(String parameter) throws MissingRequestValueException {
		String[] positionList = parameter.split("-");
		Position position = Position.builder().build();
		if ((positionList.length > 3 || positionList.length <= 2)) {
			throw new MissingRequestValueException("Type position in the correct format!");
		}

		if (parameter.endsWith(Pole.N.name()) || parameter.endsWith(Pole.E.name()) || parameter.endsWith(Pole.W.name())
				|| parameter.endsWith(Pole.S.name())) {
			position = Position.builder()
					.coordinates(Coordinates.builder().xCoordinate(Integer.valueOf(positionList[0]))
							.yCoordinate(Integer.valueOf(positionList[1])).build())
					.direction(Direction.builder().pole(Pole.valueOf(positionList[2])).build()).build();
		}
		return position;
	}

	private List<Instruction> validateAndConvertInstructions(String parameters) throws MissingRequestValueException {
		if (!parameters.matches("[LMR]+")) {
			throw new MissingRequestValueException("Type instructions in the correct format!");
		}
		List<Instruction> instructionList = new ArrayList<>();
		String[] params = parameters.split("");
		for (String parameter : params) {
			instructionList.add(Instruction.builder().command(parameter).build());
		}
		return instructionList;
	}

	@Override
	public MarsRover setRoverPosition(String parameters) throws MissingRequestValueException {
		Position position = validateAndConvertPosition(parameters);
		MarsRover marsRover = MarsRover.builder().initialPosition(position).build();
		marsRoverRepository.findById(marsRoverRepository.count()).ifPresent(mR ->
		marsRover.setPlateau(mR.getPlateau()));
		marsRover.setInitialPosition(position);
		return marsRoverRepository.save(marsRover);
	}

}
