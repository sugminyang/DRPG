<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<title>Bike-DrugProg</title>
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  	<link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/bootstrap.css">
  	<link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/usebootstrap.css">
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"/>

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
            <a href="/" class="navbar-brand">BiKE <b>DrugProg</b></a>
            
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav navbar-right">                                
                <li><a href="/" target="_blank">About</a></li>
                <li><a href="/" target="_blank">Database</a></li>
                <li><a href="/" target="_blank">Tutorial</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container" >
	<h4 class="page-header">paper visualization</h4>
	
    <!-- Main content -->
    <section class="content">
	    <table class="table table-bordered table-hover">
	    	<tr>
	    		<td>
	    			PMID:<a href='https://www.ncbi.nlm.nih.gov/pubmed/${pmid}' target='PubMedsearch'>${pmid}</a>
	    		</td>
	    		<td>
	    			<strong>${title}</strong>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			Publication:
	    		</td>
	    		<td>
	    			<strong>${publication}</strong>
	    		</td>
	    	</tr>
		</table>
      
		<div>
			TITLE:<BR>
			${coloredTitle}<BR>
	      	ABSTRACT:<BR>
	      	${coloredAbs}<BR>
		</div>
    </section>
  
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
    <script src="/resources/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="/resources/static/search_drugreposition.js"></script>
  </body>
</html>
