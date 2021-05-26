
var plateau = document.querySelector('#plateau');
var roverPos = document.querySelector('#roverPos');
var roverInst = document.querySelector('#roverInst');
var roverResult = document.querySelector('#roverResult');


var plateauDim = document.getElementById('plateauDim');
var roverposition = document.getElementById('roverposition');
var roverInstruction = document.getElementById('roverInstruction');
var roverNextPos = document.getElementById('roverNextPos');



document.querySelector('#roverPos').style.visibility = 'hidden';
document.querySelector('#roverInst').style.visibility = 'hidden';
document.querySelector('#roverResult').style.visibility = 'hidden';

var marsRoverID = null;


function setDimensions(event) {

	var plateauDimension = plateauDim.value.trim();
	var requestObj = new Object();
	requestObj.parameters = plateauDimension;
	if (plateauDimension) {
		$.ajax({
			url: '/setDimensionsOfPlateau',
			type: 'POST',
			data: JSON.stringify(requestObj),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			cache: false,
			async: true,
			success: function(request) {
				console.log("succesfully set dimensions")
				marsRoverID = request.id;
				document.querySelector('#plateau').style.visibility = 'hidden';
				document.querySelector('#roverPos').style.visibility = 'visible';
			},
			error: function() {
				console.log("error set dimensions")
				alert("Type in the correct format");
			}
		});
	}
	event.preventDefault();

}

function setRoverPos(event) {

	var roverPositionVal = roverposition.value.trim();
	var requestObj = new Object();
	requestObj.parameters = roverPositionVal;
	requestObj.id = marsRoverID;
	if (roverPositionVal) {

		$.ajax({
			url: '/setRoverPosition',
			type: 'POST',
			data: JSON.stringify(requestObj),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			cache: false,
			async: true,
			success: function(request) {
				console.log("succesfully set position");
				marsRoverID = request.id;
				document.querySelector('#roverPos').style.visibility = 'hidden';
				document.querySelector('#roverInst').style.visibility = 'visible';
			},
			error: function() {
				console.log("error set position")
				alert("Type in the correct format");
			}
		});
	}
	event.preventDefault();
}



function setRoverInst(event) {

	var roverInstructionVal = roverInstruction.value.trim();
	var requestObj = new Object();
	requestObj.parameters = roverInstructionVal;
	requestObj.id = marsRoverID;
	if (roverInstructionVal) {
		$.ajax({
			url: '/setRoverInstruction',
			type: 'POST',
			data: JSON.stringify(requestObj),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			cache: false,
			async: true,
			success: function(request) {
				console.log("succesfully set instructions");
				marsRoverID = request.id;
				roverNextPos.value = request.resultPosition;
				document.querySelector('#roverInst').style.visibility = 'hidden';
				document.querySelector('#roverResult').style.visibility = 'visible';
			},
			error: function() {
				console.log("error set instructions")
				alert("Type in the correct format");
			}
		});
	}
	event.preventDefault();
}

function setOK(event) {

	marsRoverID = null;
	document.getElementById('plateauDim').value = '';
	document.getElementById('roverposition').value = '';
	document.getElementById('roverInstruction').value = '';
	document.getElementById('roverNextPos').value = '';
	document.querySelector('#roverResult').style.visibility = 'hidden';
	document.querySelector('#roverPos').style.visibility = 'visible';
	event.preventDefault();
}



plateau.addEventListener('submit', setDimensions, true)
roverPos.addEventListener('submit', setRoverPos, true)
roverInst.addEventListener('submit', setRoverInst, true)
roverResult.addEventListener('submit', setOK, true)
