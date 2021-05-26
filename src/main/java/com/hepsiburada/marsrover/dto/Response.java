package com.hepsiburada.marsrover.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@ApiModel(description = "Represents Result Position")
@Getter
@Setter
@FieldDefaults(makeFinal = false, level = AccessLevel.PROTECTED)
@JsonTypeName("Result Position")
@NoArgsConstructor
public class Response {

	 String resultPosition;
	 
	 long id;
}
