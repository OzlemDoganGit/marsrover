package com.hepsiburada.marsrover.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@ApiModel(description = "Represents Parameters")
@Getter
@Setter
@FieldDefaults(makeFinal = false, level = AccessLevel.PROTECTED)
@JsonTypeName("Parameters")
@NoArgsConstructor
public class Request {
	
	 private static final long serialVersionUID = -405825017125717316L;
	 
	 String parameters;
	 
	 Long id;

}
