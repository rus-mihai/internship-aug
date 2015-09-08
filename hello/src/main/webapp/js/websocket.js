/**
 * 
 */

var stompClient = null;
var stompClient1 = null;
var i = 0;

function setConnected(connected) {

	// document.getElementById('conversationDiv').style.visibility = connected ?
	// 'visible' : 'hidden';
	document.getElementById('response').innerHTML = '';
	document.getElementById('response1').innerHTML = '';
}

function connect1() {

	var socket1 = new SockJS('/admin/messages');
	stompClient1 = Stomp.over(socket1);
	stompClient1.connect({}, function(frame) {

		console.log('Connected: ' + frame);
		stompClient1.subscribe('/admin/topic/message', function(m) {
			// show(JSON.parse(r.body).startstring);
			var tr = createRow();
			var date = createDate(JSON.parse(m.body).date);
			var name = createName(JSON.parse(m.body).name);
			var message = createUserMessage(JSON.parse(m.body).usermessage);
			tr.appendChild(date);
			tr.appendChild(name);
			tr.appendChild(message);
		});
	});
}

function connect() {
	var socket = new SockJS('/admin/realtime');
	stompClient = Stomp.over(socket);
	stompClient
			.connect(
					{},
					function(frame) {

						console.log('Connected: ' + frame);
						stompClient
								.subscribe(
										'/admin/topic/view',
										function(r) {
											// show(JSON.parse(r.body).startstring);
											var tr = createRow();
											var line = createLine(JSON
													.parse(r.body).line);
											var startTime = createStartTime(JSON
													.parse(r.body).startTime);
											var endTime = createEndTime(JSON
													.parse(r.body).endTime);
											var startStation = createStartStation(JSON
													.parse(r.body).startStation);
											var endStation = createEndStation(JSON
													.parse(r.body).endStation);
											tr.appendChild(line);
											tr.appendChild(startTime);
											tr.appendChild(endTime);
											tr.appendChild(startStation);
											tr.appendChild(endStation);

										});
					});

}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function disconnect1() {
	if (stompClient1 != null) {
		stompClient1.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function show(message) {
	var response = document.getElementById('response');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.appendChild(document.createTextNode(message));
	response.appendChild(p);
}

function createRow() {
	var response = document.getElementById('tbody');
	var tr = document.createElement('tr');
	response.appendChild(tr);
	return tr;
}

function createLine(message) {
	var line = document.getElementById('line');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}

function createDate(message) {
	var line = document.getElementById('date');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}
function createName(message) {
	var line = document.getElementById('driver');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}

function createUserMessage(message) {
	var line = document.getElementById('message');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}

function createStartTime(message) {
	var line = document.getElementById('startTime');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}

function createEndTime(message) {
	var line = document.getElementById('endTime');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}

function createStartStation(message) {
	var line = document.getElementById('startStation');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}

function createEndStation(message) {
	var line = document.getElementById('endStation');
	var td = document.createElement('td');
	td.style.wordWrap = 'break-word';
	td.appendChild(document.createTextNode(message));
	line.appendChild(td);
	return td;
}