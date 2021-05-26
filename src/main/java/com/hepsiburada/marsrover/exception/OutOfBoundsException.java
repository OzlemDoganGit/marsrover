package com.hepsiburada.marsrover.exception;

import lombok.Getter;

@Getter
public class OutOfBoundsException extends RuntimeException {


	public OutOfBoundsException() {
		super("Position is out of bounds!");
		
	}

}