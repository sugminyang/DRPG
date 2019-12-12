<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>BikeDrugProg</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/bootstrap.css">
  <link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/usebootstrap.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
  
<link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <style>
            .ui-autocomplete {
              max-height: 100px;
              overflow-y: auto;
              /* prevent horizontal scrollbar */
              overflow-x: hidden;
            }
            /* IE 6 doesn't support max-height
             * we use height instead, but this forces the menu to always be this tall
             */
            * html .ui-autocomplete {
              height: 100px;
            }
    </style>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="${pageContext.request.contextPath}/" class="navbar-brand">BiKE <b>DrugProg</b></a>
            
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav navbar-right">                                
                <li><a href="${pageContext.request.contextPath}/" target="_blank">About</a></li>
                <li><a href="${pageContext.request.contextPath}/" target="_blank">Database</a></li>
                <li><a href="${pageContext.request.contextPath}/" target="_blank">Tutorial</a></li>
                <li><a href="${pageContext.request.contextPath}/" target="_blank">Technology</a></li>
                <li><a href="${pageContext.request.contextPath}/" target="_blank">Publication</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container" >
	<div class="col-lg-12">
        <h4 class="page-header">Result</h4>
    </div>
    <div class="col-lg-12">
    	<p>search drug name: <b>${drugname}</b></p>
    </div>
	<div class="table table-bordered table-hover dataTable" id="result"></div>

	<div class="col-lg-12">
        <h4 class="page-header">1. PMID</h4>
        <div id="pmidList">
		</div>
    </div>
</div>      
<footer class="main-footer">
<div class="pull-right hidden-xs">
    <b>Version</b> 1.0
</div>
<strong>Copyright © 2019 <a href="http://bike.snu.ac.kr">BiKE LAB</a>.</strong> All rights
reserved.
</footer>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/static/search_drugreposition.js"></script>
	
<script>
        $(document).ready( function () {
        	if(${data} != null)	{
        		$('#result').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		//var vars = ['disease','gene','interaction_types','drug_name','drug_summary','interaction_claim_source']	//old
        		var vars = ['diseaseName','targetGene','phaseNum','interactionType','status','evidenceScore','sources']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		var bindings = ${data}
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
        			/* $("#chemInfo")[0].innerHTML = 'DGI info: ' + '<a target="_blank" rel="noopener noreferrer"  href="http://www.dgidb.org/drugs/' + b['drugName'] + '#_summary">' + 'http://www.dgidb.org/drugs/' + b['drugName'] + '#_summary' +'</a>' 
        					+ "<br>"+ 'Chemical Name: ' +'<button type="button" class="chemicalBtn"> ' + b['drugName'] + '</button>'
        					+"<br>" + 'CHEMBL ID: ' + '<button> <a target="_blank" rel="noopener noreferrer" href="https://www.ebi.ac.uk/chembl/compound_report_card/' + b['chemblID']+ '">' + b['chemblID'] + '</a></button>'
//        			console.log(b['drugName']  + ", " +  b['chemblID']) */
        					
        			$(vars).each(function(k2, v) {
        				if(v == 'drugName')	{
//        					tr.append('<td>'+'<button> <a href="http://www.dgidb.org/drugs/' + b['drugName'] + '#_summary">' + b['drugName'] + '</a></button>' + '</td>')
//        					tr.append('<td>'+'<a href="http://www.dgidb.org/drugs/' + b['drugName'] + '#_summary">' + 'http://www.dgidb.org/drugs/' + b['drugName'] + '#_summary' +'</a>' + '<button type="button" class="chemicalBtn"> ' + b['drugName'] + '</button>' + '</td>')
        				}
        				else if(v == 'chemblID'){
//        					tr.append('<td>'+'<button> <a href="https://www.ebi.ac.uk/chembl/compound_report_card/' + b['chemblID']+ '">' + b['chemblID'] + '</a></button>' + '</td>')
        				}
        				else if(v == 'targetGene')	{
        					if(b['targetGene'].length != 0)	{
        						tr.append('<td>'+'<button> <a target="_blank" rel="noopener noreferrer" href="https://www.genecards.org/cgi-bin/carddisp.pl?gene=' + b['targetGene'] + '">' + b['targetGene'] + '</a></button>' + '</td>')
        					}
        					else	{
        						tr.append('<td>'+ '</td>')
        					}
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
                "bFilter": true,
                "bSort": true,
                "order": [[ 5, "desc" ]],
                scrollX:        true,
                scrollCollapse: true,
                "retrieve": true
        		})
        		
        		$("#MydataTable_filter").remove();
        	}
        	else {
//        		console.log(${data})
        		$('#result').empty()
        		error = JSON.stringify(${data})
        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
        		$('#result').append(table)
        		table.DataTable({
        			bPaginate : false,		    
        	        'paging': true
//                'paging': true,
//                'lengthChange': false,
//                'searching': false,
//                'ordering': true,
//                'info': true,
//                'autoWidth': true
        		})
        	}
        	
        	
        	 
        	$('#MydataTable tbody').on( 'click', 'tr', function () {
        		var table = $('#MydataTable').DataTable();
//        	    alert( 'Row index: '+table.row( this ).index() );
//        	    console.log(table.row( this ).data()[0]);
//        	    console.log(table.row( this ).data()[1].indexOf("\">"));
				var diseasename = table.row( this ).data()[0];
        	    var gene = table.row( this ).data()[1];
        	    var st = table.row( this ).data()[1].indexOf("\">") + 2;
        	    var ed = table.row( this ).data()[1].indexOf("</a>");
//        	    console.log(gene.substring(st,ed))
        	    gene = gene.substring(st,ed)
        	    
        	    
        	    url = "rowinfo?genesymbol=" + gene + "&diseasename=" + diseasename
	    		url += "&output=json"
	    
        	    $.ajax({
	            'type': "GET",
	            'url': url,
	            'dataType': "json",
	            'error': rowinfo_fail,
	            'success': rowinfo_success
	        	})
        	} );
            
			function rowinfo_success(data)	{
				console.log(data)
				$("#pmidList").text(data)
				
				$('#pmidList').empty()
				var table = $('<table id="pmidTable" class="table table-bordered table-hover"></table>')
				var tr = $("<tr></tr>")
				var vars = ['pmidList']
				$(vars).each(function(k, v) {
					tr.append('<th>' + v + '</th>')
				})
				var thead = $("<thead></thead>")
				thead.append(tr)
				$(table).append(thead)
				var cnt = 0;
				var tbody = $("<tbody></tbody>")
				var bindings = data
				tr = $("<tr></tr>")
				$(bindings).each(function(k, b) {
					$(vars).each(function(k2, v) {
						tr.append('<td>'+'<button> <a href="' + document.location.origin + '/paperviz?pmid='+ b+'">' + b + '</a></button>' + '</td>')
						cnt = cnt+1;
					})
					
					if(cnt % 10 == 0)	{
						tbody.append(tr)
						tr = $("<tr></tr>")
					}
				})
				tbody.append(tr)
				$(table).append(tbody)
		
				$('#pmidList').append(table)
			}
        	    
			function rowinfo_fail(error) {
        		alert("no information")
			}
        });
</script>        
</body>
</html>