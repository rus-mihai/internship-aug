/**
 * 
 */

var stompClient = null;

        function setConnected(connected) {
           
            //document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }

        function connect() {
            var socket = new SockJS('/admin/realtime');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                
                console.log('Connected: ' + frame);
                stompClient.subscribe('/admin/topic/view', function(r){
                    //show(JSON.parse(r.body).startstring);
                    var tr = createRow();
                    var line = createLine(JSON.parse(r.body).line);
                    var startTime = createStartTime(JSON.parse(r.body).startTime);
                    var endTime = createEndTime(JSON.parse(r.body).endTime);
                    var startStation = createStartStation(JSON.parse(r.body).startStation);
                    var endStation = createEndStation(JSON.parse(r.body).endStation);
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


        function show(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
        }
        
        function createRow(){
        	var response = document.getElementById('tbody');
        	var tr = document.createElement('tr');
        	response.appendChild(tr);
        	return tr;
        }
        
        function createLine(message){
        	var line = document.getElementById('line');
        	var td = document.createElement('td');
        	td.style.wordWrap = 'break-word';
        	td.appendChild(document.createTextNode(message));
        	line.appendChild(td);
        	return td;
        }
        
        function createStartTime(message){
        	var line = document.getElementById('startTime');
        	var td = document.createElement('td');
        	td.style.wordWrap = 'break-word';
        	td.appendChild(document.createTextNode(message));
        	line.appendChild(td);
        	return td;
        }
        
        function createEndTime(message){
        	var line = document.getElementById('endTime');
        	var td = document.createElement('td');
        	td.style.wordWrap = 'break-word';
        	td.appendChild(document.createTextNode(message));
        	line.appendChild(td);
        	return td;
        }
        
        function createStartStation(message){
        	var line = document.getElementById('startStation');
        	var td = document.createElement('td');
        	td.style.wordWrap = 'break-word';
        	td.appendChild(document.createTextNode(message));
        	line.appendChild(td);
        	return td;
        }
        
        function createEndStation(message){
        	var line = document.getElementById('endStation');
        	var td = document.createElement('td');
        	td.style.wordWrap = 'break-word';
        	td.appendChild(document.createTextNode(message));
        	line.appendChild(td);
        	return td;
        }