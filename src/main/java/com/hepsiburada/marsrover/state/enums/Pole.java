package com.hepsiburada.marsrover.state.enums;

import com.hepsiburada.marsrover.domain.Coordinates;
import com.hepsiburada.marsrover.domain.Direction;
import com.hepsiburada.marsrover.domain.Position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Pole {
	
	

	N("NORTH") {

		@Override
		public Position right(Coordinates coordinates) {

			return Position.builder().direction(Direction.builder().pole(E).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position left(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(W).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position move(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(N).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()+FORWARD).build())
					.build();
		}

	},
	S("SOUTH") {
		@Override
		public Position right(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(W).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position left(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(E).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position move(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(S).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()+BACK).build())
					.build();
		}
	},
	E("EAST") {
		@Override
		public Position right(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(S).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position left(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(N).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position move(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(E).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()+FORWARD).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}
	},
	W("WEST") {
		@Override
		public Position right(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(N).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position left(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(S).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}

		@Override
		public Position move(Coordinates coordinates) {
			return Position.builder().direction(Direction.builder().pole(W).build()).coordinates(Coordinates.builder()
					.xCoordinate(coordinates.getXCoordinate()+BACK).yCoordinate(coordinates.getYCoordinate()).build())
					.build();
		}
	};

	private String displayName;
	
	private static final Integer FORWARD = 1;
	
	private static final Integer BACK = -1;

	public abstract Position right(Coordinates coordinates);

	public abstract Position left(Coordinates coordinates);

	public abstract Position move(Coordinates coordinates);

}
