
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
    
    $('#search_type').on('change', function (e) {
    	search_type = $('#search_type option:selected').val()
    	
    	if(search_type == 'disease_name') {
    		$("#search_query").val("glioma");
    	} else if(search_type == 'geneSymbol') {
    		$("#search_query").val("EGFR");
    	} else if(search_type == 'pmid') {
    		$("#search_query").val("7849156");
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
		var vars = ['pmid','disease','gene','mutation','NER viz']
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
    
    $( "#search_query" ).autocomplete({ 
    	source : function( request, response ) { 
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

    			url = "autosearch"
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