$(function() {
	var processed_json = new Array();
	$.getJSON('/charts', function(data) {
		// Populate series
		for (i = 0; i < data.length; i++) {
			processed_json.push(data[i]);

		}
		console.log(processed_json);
		// draw chart
		$('#container').highcharts({
			chart : {
				type : "column"
			},
			title : {
				text : "Student data"
			},
			xAxis : {
				type : 'category',
				allowDecimals : false,
				title : {
					text : ""
				}
			},
			yAxis : {
				title : {
					text : "Scores"
				}
			},
			series : [ {
				name : 'Subjects',
				data : series(processed_json)
			} ]
		});
	});
});

function series(array){
	var series = [];
	for(i = 0; i<array.length; i++){
		var min;
		var transform = moment(array[i], "HH:mm:ss");
		min = transform.minute();
		series.push(min);
	}
	return series;
}
