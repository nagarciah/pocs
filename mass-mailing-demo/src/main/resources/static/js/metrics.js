
$(document).ready(function(){
	
    $.ajax({
        url: "/api/v0.1/metrics/time-series/" + customerId
    }).then(function(data) {
    	
    	var tsMetrics = {
    		count_targeted: [],
    		count_accepted: [],
    		count_unique_confirmed_opened: [],
    		count_rendered: [],
    		count_clicked: [],
	    	count_admin_bounce: [],
	    	count_bounce: [],
	    	count_delayed: [],
	    	count_delivered: [],
	    	count_injected: [],
	    	count_rejected: [],
	    	count_sent: [],
	    	count_spam_complaint: [],
	    	count_unique_clicked: [],
	    	total_msg_volume: []			
    	};
    	// TODO refactorizar para simplicidad y flexibilidad, determinando dinámicamente las métricas
    	var j=0, maxY=10, totals={
    		count_targeted: 0,
    		count_accepted: 0,
    		count_unique_confirmed_opened: 0,
    		count_clicked: 0,
    		count_spam_complaint: 0
    	};
    	data.results.forEach(function(item, index){
    		tsMetrics.count_targeted.push([/*item.ts*/j, item.count_targeted]);
    		tsMetrics.count_accepted.push([/*item.ts*/j, item.count_accepted]);
    		tsMetrics.count_unique_confirmed_opened.push([/*item.ts*/j, item.count_unique_confirmed_opened]);
    		tsMetrics.count_clicked.push([/*item.ts*/j, item.count_clicked]);
    		tsMetrics.count_spam_complaint.push([/*item.ts*/j++, item.count_spam_complaint]);
    		
    		if(item.count_targeted > maxY){
    			maxY = parseInt(item.count_targeted) + 5;
    		}
    		if(item.count_accepted > maxY){
    			maxY = parseInt(item.count_accepted) + 5;
    		}
    		if(item.count_unique_confirmed_opened > maxY){
    			maxY = parseInt(item.count_unique_confirmed_opened) + 5;
    		}
    		if(item.count_clicked > maxY){
    			maxY = parseInt(item.count_clicked) + 5;
    		}
    		if(item.count_spam_complaint > maxY){
    			maxY = parseInt(item.count_spam_complaint) + 5;
    		}
    		
    		totals.count_targeted += parseInt(item.count_targeted);
    		totals.count_accepted += parseInt(item.count_accepted);
    		totals.count_unique_confirmed_opened += parseInt(item.count_unique_confirmed_opened);
    		totals.count_clicked += parseInt(item.count_clicked);
    		totals.count_spam_complaint += parseInt(item.count_spam_complaint);
    	});

    	$("#metricBox1").text(totals.count_targeted);
    	$("#metricBox2").text(totals.count_accepted);
    	$("#metricBox3").text(totals.count_spam_complaint);
    	$("#metricBox4").text(totals.count_unique_confirmed_opened);
    	$("#metricBox5").text(totals.count_clicked);
    	
	
		// === Make chart === //
	    var plot = $.plot($(".chart"),
	           [ 
	        	   { data: tsMetrics.count_targeted, label: "Recibidos por la plataforma de entrega", color: "#37AADC"}, 
	        	   { data: tsMetrics.count_accepted, label: "Aceptado por ISP", color: "#9BCD5A"},
	        	   { data: tsMetrics.count_spam_complaint, label: "Reclamos de Spam (ISP)", color: "#CCCCCC"},
	        	   { data: tsMetrics.count_unique_confirmed_opened, label: "Leídos (Únicos)", color: "#BA1E20"},
	        	   { data: tsMetrics.count_clicked, label: "Clicks (Total)", color: "#B70C9E"}
        	   ], 
        	   {
	               series: {
	                   lines: { show: true },
	                   points: { show: true }
	               },
	               grid: { hoverable: true, clickable: true },
	               yaxis: { min: 0, max: maxY }
			   });
	    
		// === Point hover in chart === //
	    var previousPoint = null;
	    $(".chart").bind("plothover", function (event, pos, item) {
			
	        if (item) {
	            if (previousPoint != item.dataIndex) {
	                previousPoint = item.dataIndex;
	                
	                $('#tooltip').fadeOut(200,function(){
						$(this).remove();
					});
	                var x = item.datapoint[0].toFixed(2),
						y = item.datapoint[1].toFixed(2);
	                    
	                maruti.flot_tooltip(item.pageX, item.pageY,item.series.label + " of " + x + " = " + y);
	            }
	            
	        } else {
				$('#tooltip').fadeOut(200,function(){
						$(this).remove();
					});
	            previousPoint = null;           
	        }   
	    });
    },
    
    function(errorObj){
    	handleError(errorObj)
    }
    );
    
	
});
	

maruti = {
		// === Tooltip for flot charts === //
		flot_tooltip: function(x, y, contents) {
			
			$('<div id="tooltip">' + contents + '</div>').css( {
				top: y + 5,
				left: x + 5
			}).appendTo("body").fadeIn(200);
		}
}

// TODO Mejorar la presentación del error, por algo claro para el usuario. Usar indicador de carga en proceso ("Loading...")
handleError = function(errorObj){
	alert(JSON.stringify(errorObj));
}
