<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Bike-DrugProg</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<script src="${pageContext.request.contextPath}/resources/static/bower_components/jquery/dist/jquery.min.js"></script>
	<link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/bootstrap.css">
	<link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/usebootstrap.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">  
  
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
            #help	{
            	text-align: center;
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
                <li><a href="/litsearch">Literature-Search</a></li>
                <li><a href="/overview">Overview</a></li>
                <li><a href="/tutorial">Tutorial</a></li>
                <li><a href="/help">Help</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container" >
	<div class="col-lg-12">
        <h3 class="page-header">Help</h3>
    </div>
	<div id="help" class="col-lg-12" >
		<img src="${pageContext.request.contextPath}/resources/img/browser_comp.png" style='width:70%; height:70%; object-fit: contain; vertical-align: middle;'/>
	</div>    
</div>  

<footer class="main-footer">
<div class="pull-right hidden-xs">
    <b>Version</b> 1.0
</div>
<strong>Copyright © 2019 <a href="http://bike.snu.ac.kr">BiKE LAB</a>.</strong> All rights reserved.
</footer>
    <script src="${pageContext.request.contextPath}/resources/static/bower_components/jquery/dist/jquery.min.js"></script>    
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="http://bike-bee.snu.ac.kr/js/finder.js?newversion"></script>
</body>
</html>