package com.hepsiburada.marsrover.unittestbase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hepsiburada.marsrover.domain.Coordinates;
import com.hepsiburada.marsrover.domain.Direction;
import com.hepsiburada.marsrover.domain.Instruction;
import com.hepsiburada.marsrover.domain.MarsRover;
import com.hepsiburada.marsrover.domain.Plateau;
import com.hepsiburada.marsrover.domain.Position;
import com.hepsiburada.marsrover.state.enums.Pole;

public class MarsRoverTestBase {


	
	public MarsRover generateMockMarsRover()
	{
		List<Instruction> instructions = new ArrayList<>();
		instructions.add(Instruction.builder().command("L").id(1L).build());
		instructions.add(Instruction.builder().command("M").id(1L).build());
		instructions.add(Instruction.builder().command("L").id(1L).build());
		instructions.add(Instruction.builder().command("M").id(1L).build());
		instructions.add(Instruction.builder().command("L").id(1L).build());
		instructions.add(Instruction.builder().command("M").id(1L).build());
		instructions.add(Instruction.builder().command("L").id(1L).build());
		instructions.add(Instruction.builder().command("M").id(1L).build());
		instructions.add(Instruction.builder().command("R").id(1L).build());

		Plateau plateau = Plateau.builder().coordinates(Coordinates.builder().xCoordinate(5).yCoordinate(5).id(1L).build())
				.creationTime(LocalDateTime.now()).id(1L).build();
		MarsRover marsRover = MarsRover.builder().id(1L)
				.initialPosition(Position.builder()
						.coordinates(Coordinates.builder().xCoordinate(1).yCoordinate(2).id(1L).build())
						.direction(Direction.builder().pole(Pole.N).id(1L).build()).build())
				.plateau(plateau)
				.resultPosition(Position.builder()
						.coordinates(Coordinates.builder().xCoordinate(1).yCoordinate(3).id(1L).build())
						.direction(Direction.builder().pole(Pole.N).id(1L).build()).id(1L).build())
				.instructions(instructions).build();
		return marsRover;
	}

}
