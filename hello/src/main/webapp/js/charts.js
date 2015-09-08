$(function() {
	var processed_json = [];
	var id = document.getElementById('id').value;
	var chartPath = '/charts?id='+id;
	$.getJSON(chartPath, function(data) {
		processed_json = [];
		for (i = 0; i < data.length; i++) {
			processed_json.push(data[i]);

		}
		console.log(processed_json);
		// draw chart
		$('#container').highcharts({
			chart : {
				type : "line"
			},
			title : {
				text : "Duration per hour"
			},
			xAxis : {
				type : 'hours',
				allowDecimals : false,
				title : {
					text : ""
				}
			},
			yAxis : {
				title : {
					text : "Minutes spent"
				}
			},
			series : [ {
				name : 'Hour',
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
