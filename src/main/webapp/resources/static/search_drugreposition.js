
$.fn.dataTable.ext.search.push(
	    function( settings, data, dataIndex ) {
	    	var drugType = $("#drug_type_dr option:selected").text();
	    	var min;
	        var max;
	        var phase = parseFloat( data[3] ) || 0; // use data for the age column
	        
	        if(drugType == 'All')	{
	        	min = 2;
	        	max = 4;
	        }
	        else if(drugType == 'FDA-approved control'){
	        	min = 4;
	        	max = 4;
	        }
	        else if(drugType == 'FDA-approved candidate'){	//approved
	        	min = 2;
	        	max = 4;
	        }
	        else if(drugType == 'Unapproved candidate'){
	        	min = 2;
	        	max = 3;
	        }
	        
	        if ( ( isNaN( min ) && isNaN( max ) ) ||
	             ( isNaN( min ) && phase <= max ) ||
	             ( min <= phase   && isNaN( max ) ) ||
	             ( min <= phase   && phase <= max ) )
	        {
	            return true;
	        }
	        return false;
	    });


$(function() {
    $('#search_repositioning').click(function() {
        search_drugrepositioning()
    })
    
    $(document).keypress(function(e) {
        if (e.which == 13) {
            e.preventDefault();
            search_drugrepositioning()
        }
    })

    $('#drug_type_dr').on('change', function (e) {
    	search_drugrepositioning()
    })
    
    $('#search_type_dr').on('change', function (e) {
    	search_type = $('#search_type_dr option:selected').val()
    	
    	if(search_type == 'disease_name') {
    		$("#search_query_dr").val("breast neoplasms");
    	} else if(search_type == 'geneSymbol') {
    		$("#search_query_dr").val("EGFR");
    	} else if(search_type == 'chemical_name') {
    		$("#search_query_dr").val("LETROZOLE");
    	} 
    })
    
    $("#drug_type_dr").change(function() {
    	var dataTable = $('#MydataTable').DataTable();
    	dataTable.draw();
    	
    })
    
    $('#btnSE').click(function() {
    	drug_sideEffect()
    })
    
    function drug_sideEffect()	{
    	drugName = $('#btnSE').text()
    	url = "sideeffect?drugname=" + drugName
	    url += "&output=json"
	        	
    	$.ajax({
	            'type': "GET",
	            'url': url,
	            'dataType': "json",
	            'error': se_fail,
	            'success': se_success
	        })
    }
    
    function se_success(data)	{
    	$('#se_result').empty()
		var table = $('<table id="sider_result" class="table table-bordered table-hover"></table>')
		var tr = $("<tr></tr>")
		var vars = ['sideEffect','frequency','description']
		$(vars).each(function(k, v) {
			tr.append('<th>' + v + '</th>')
		})
		var thead = $("<thead></thead>")
		thead.append(tr)
		$(table).append(thead)
		
		var tbody = $("<tbody></tbody>")
		var bindings = data
		$(bindings).each(function(k, b) {
			tr = $("<tr></tr>")
			$(vars).each(function(k2, v) {
				if(v == 'description')	{
					tr.append('<td>'+'<button> <a href="http://sideeffects.embl.de/se/' + b['description'] + '">' + b['description'] + '</a></button>' + '</td>')
				}
				else	{
					tr.append('<td>' + b[v] + '</td>')
				}
			})
			tbody.append(tr)
		})
		$(table).append(tbody)

		$('#se_result').append(table)
		table.DataTable({
        'paging': true,
        'lengthChange': false,
        'searching': false,
        'ordering': true,
        'info': true,
        "applyFilter":true,
        "bJQueryUI": true,
        "bFilter": true,
        "bSort": true,
        "order": [[ 1, "desc" ]],
        "retrieve": true
		})
    }
    
    function se_fail(error) {
        $('#se_result').empty()
		error = JSON.stringify(error)
		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
		$('#se_result').append(table)
		table.DataTable({
        'paging': true,
        'lengthChange': false,
        'searching': false,
        'ordering': true,
        'info': true,
        'autoWidth': true
		})
		
    }
    
    
    function search_drugrepositioning()	{
    	search_type = $('#search_type_dr option:selected').val()
    	search_query = $('#search_query_dr').val()
    	var drugType = $("#drug_type_dr option:selected").text();
    	query = ""
    		
    	if(search_query.trim().length == 0 )	{
//    		console.log('length of search_query : ' + search_query.length)
    	}
    	else	{
	    	if(search_type == 'disease_name') {
	    		url = "drugprogdisease"
	    		query = "disease_@" + search_query
	    	} else if(search_type == 'geneSymbol') {
	    		url = "drugproggene"
	    		query = "gene_@" + search_query
	    	} else if(search_type == 'chemical_name') {
	    		url = "drugprogchemical"
	    		query = "drug_@" + search_query
	    	} else {
	    		url = "drugprogdisease"
	    		query = ""
	    	}
	    	var query_dec = decodeURIComponent(query);
	    	var drugType_dec = decodeURIComponent(drugType);
	    	
	        url += "?query=" + query_dec + "&drug_type=" + drugType_dec
	        url += "&output=json"
	
//	        console.log(url)
	        
	    	$.ajax({
	            'type': "GET",
	            'url': url,
	            'dataType': "json",
	            'error': search_fail,
	            'success': search_success
	        })
    	}
    }
    

    function search_fail(error) {
        $('#result').empty()
		error = JSON.stringify(error)
		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
		$('#result').append(table)
		table.DataTable({
        'paging': true,
        'lengthChange': false,
        'searching': false,
        'ordering': true,
        'info': true,
        'autoWidth': true
		})
		
    }

    function search_success(data) {
        $('#result').empty()
		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
		var tr = $("<tr></tr>")
		//var vars = ['disease','gene','interaction_types','drug_name','drug_summary','interaction_claim_source']	//old
		var vars = ['diseaseName','targetGene','drugName','phaseNum','interactionType','chemblID','sources']
		$(vars).each(function(k, v) {
			tr.append('<th>' + v + '</th>')
		})
		var thead = $("<thead></thead>")
		thead.append(tr)
		$(table).append(thead)
		
		var tbody = $("<tbody></tbody>")
		var bindings = data
		$(bindings).each(function(k, b) {
			tr = $("<tr></tr>")
			$(vars).each(function(k2, v) {
				if(v == 'drugName')	{
					tr.append('<td>'+'<button> <a href="http://www.dgidb.org/drugs/' + b['drugName'] + '#_summary">' + b['drugName'] + '</a></button>' + '</td>')
				}
				else if(v == 'chemblID'){
					tr.append('<td>'+'<button> <a href="https://www.ebi.ac.uk/chembl/compound_report_card/' + b['chemblID']+ '">' + b['chemblID'] + '</a></button>' + '</td>')
				}
				else if(v == 'targetGene')	{
					tr.append('<td>'+'<button> <a href="https://www.genecards.org/cgi-bin/carddisp.pl?gene=' + b['targetGene'] + '">' + b['targetGene'] + '</a></button>' + '</td>')
				}
				else	{
					tr.append('<td>' + b[v] + '</td>')
				}
			})
			tbody.append(tr)
		})
		$(table).append(tbody)

		$('#result').append(table)
		table.DataTable({
        'paging': true,
        'lengthChange': false,
        'searching': true,
        'ordering': true,
        'info': true,
        "applyFilter":true,
        "bJQueryUI": true,
        "bFilter": true,
        "bSort": true,
        "order": [[ 3, "desc" ]],
        "retrieve": true
		})
		
		$("#MydataTable_filter").remove();
    }
    
    $( "#search_query_dr" ).autocomplete({ 
    	source : function( request, response ) { 
    		search_type = $('#search_type_dr option:selected').val()
    		search_query = $('#search_query_dr').val()
    		query = ""
    			if(search_type == 'disease_name') {
    				query = "disease_@" + search_query
    			} else if(search_type == 'geneSymbol') {
    				query = "gene_@" + search_query
    			} else if(search_type == 'chemical_name') {
    				query = "chemical_@" + search_query
    			} else {
    				query = ""
    			}

    			url = "autosearch_dr"
    			url += "?query=" + query
    			url += "&output=json"
    				
    		$.ajax({ 
    			//호출할 URL 
    			'url': url, 
    			//우선 jsontype json으로 
    			'dataType': "json", 
    			// parameter 값이다. 여러개를 줄수도 있다. 
    			'data': { 
    				//request.term >> 이거 자체가 text박스내에 입력된 값이다. 
    				searchValue: request.term 
    			}, 
    			success: function( data ) {
    	            response( data );
    	          }
    		}); 
    	}, 
    	//최소 몇자 이상되면 통신을 시작하겠다라는 옵션 
    	minLength: 3, 
    	//자동완성 목록에서 특정 값 선택시 처리하는 동작 구현 
    	//구현없으면 단순 text태그내에 값이 들어간다. 
    	select: function( event, ui ) {}
    });
})