$(function() {
	$('#search_button').click(function() {
		search()
	})
    
    $(document).keypress(function(e) {
        if (e.which == 13) {
            e.preventDefault();
            search()
        }
    })
    
    function search() {
    	search_type = $('#search_type option:selected').val()
    	search_query = $('#search_query').val()
    	query = ""
    	if(search_type == 'disease_name') {
    		query = "disease_@" + search_query
    	} else if(search_type == 'geneSymbol') {
    		query = "gene_@" + search_query
    	} else if(search_type == 'pmid') {
    		query = "pmid_@" + search_query
    	} else {
    		query = ""
    	}
    
        url = "dbsearch"
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
		var table = $('<table class="table table-bordered table-hover"></table>')
		var tr = $("<tr></tr>")
		var vars = ['pmid','disease','gene','snp','NER viz']
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
				if(v == 'NER viz')	{
					tr.append('<td>'+'<button> <a href="' + document.location.origin + '/paperviz?pmid='+ b['pmid']+'">' + b['pmid'] + '</a></button>' + '</td>')
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
        'searching': false,
        'ordering': true,
        'info': true,
        'autoWidth': true
		})
    }
})