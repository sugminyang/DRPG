
$.fn.dataTable.ext.search.push(
	    function( settings, data, dataIndex ) {
	    	var drugType = $("#drug_type_dr option:selected").text();

	    	var min = 0;
	        var max = 4;
	        var phase = parseFloat( data[3] ) || 0; // use data for the age column
	 
	        if(drugType == 'Candidate'){
	        	min = 0;
	        	max = 3;
	        }
	        else if(drugType == 'Approved'){	//approved
	        	min = 4;
	        	max = 4;
	        }
	        else	{
	        	min = 0;
	        	max = 4;
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

    
    $("#drug_type_dr").change(function() {
    	var dataTable = $('#MydataTable').DataTable();
//    	console.log(dataTable);
    	dataTable.draw();
    	
    })
    
    function search_drugrepositioning()	{
    	search_type = $('#search_type_dr option:selected').val()
    	search_query = $('#search_query_dr').val()
    	query = ""
    		
    	if(search_type == 'disease_name') {
    		query = "disease_@" + search_query
    	} else if(search_type == 'geneSymbol') {
    		query = "gene_@" + search_query
    	} else if(search_type == 'chemical_name') {
    		query = "drug_@" + search_query
    	} else {
    		query = ""
    	}
    
        url = "searchdrugrepositor"
        url += "?query=" + query
        url += "&output=json"

    	$.ajax({
            'type': "GET",
            'url': url,
            'dataType': "json",
            'error': search_fail,
            'success': search_success
        })
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
})