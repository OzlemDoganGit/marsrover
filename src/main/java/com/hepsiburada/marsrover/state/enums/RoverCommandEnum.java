package com.hepsiburada.marsrover.state.enums;

import com.hepsiburada.marsrover.domain.Coordinates;
import com.hepsiburada.marsrover.domain.Direction;
import com.hepsiburada.marsrover.domain.Position;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoverCommandEnum {

	L("LEFT") {

		@Override
		public Position execute(Coordinates coordinates, Direction direction) {
			return direction.getPole().left(coordinates);

		}

	},

	R("RIGHT") {

		@Override
		public Position execute(Coordinates coordinates, Direction direction) {
			return direction.getPole().right(coordinates);

		}

	},

	M("MOVE") {

		@Override
		public Position execute(Coordinates coordinates, Direction direction) {
			return direction.getPole().move(coordinates);

		}

	};

	String displayName;

	public abstract Position execute(Coordinates coordinates, Direction direction);

}
