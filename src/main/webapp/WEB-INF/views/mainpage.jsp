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
	<script src="${pageContext.request.contextPath}/resources/static/search_drugreposition.js"></script>
	
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
            .jumbotron {
            	padding-top: 5px;
            	background-color: rgba( 255, 255, 255, 0 );
            }
            #maincover {
            	background-image: url("${pageContext.request.contextPath}/resources/img/3-background_drug2.jpg");
            	background-size:     cover;                      /* <------ */
    			background-repeat:   no-repeat;
    			background-position: center;
            }
            #logo	{
            	max-width: 100%;
    			max-height: 100%;
            }
            #lab{
			  width:500px;
			  height:25px;  
			  display: table; 
			  margin: auto;
			}
			#lab_txt {
			  background: #163851;
			  width:500px; 
			  color: white;
			  text-align:center; 
			  vertical-align: middle;
			  display: table-cell;   
			}
			#description	{
				position: absolute;
				bottom:0px;
				left: 230px;
			}
			#desc_txt	{
				font-size:15px;  
				text-align:center; 
			 	margin: auto auto;
			}
			.jumbotron	{
				position:relative;
				height: 100%;   
	
			}
			            
    </style>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">BiKE <b>DrugProg</b></a>
            
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav navbar-right">                                
                <li><a href="${pageContext.request.contextPath}/" target="_blank">About</a></li>
                <li><a href="${pageContext.request.contextPath}/" target="_blank">Database</a></li>
                <li><a href="${pageContext.request.contextPath}/" target="_blank">Tutorial</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
	    <div class="row" id="maincover" style="width:1170px; height:330px;">
	    	<div class="jumbotron">
			    <div id="logo">
			    	<img src="${pageContext.request.contextPath}/resources/img/logo.png" style='height: 50px; width: 100px; object-fit: contain; vertical-align: middle;'/>
			    </div>
			    <div id="lab" style='vertical-align: middle; display: table;'>
			    	<p id="lab_txt" style="background: #163851; width:500px; color: white; text-align:center; vertical-align: middle; display: table-cell;">
			    		by Biomedical Knowledge Engineering Laboratory
			    	</p>
			    </div>
			    <div id="description">
					<p id="desc_txt" >DrugProg is developed <span style="color: #163851"><b>to aid repurposing pre-existing drugs </b></span>to several diseases by providing open-resource
					<br><span style="color: #163851"><b>which connect positive prognostic status</b></span> and molecular status, and developing tools to identify targets.</p>
				</div>
		    </div>
		</div>
		<div class="row" ><!--  style="border: 1px dashed;background-color: #CCD0D3;" -->
		<!-- <div id="row" style="border: 1px solid #bcbcbc; text-align: center;" class="well well-lg"> -->
				<div class="col-lg-12">
		            <h4 class="page-header">1. Make Query</h4>
		                <!--  <div class="bs-component">-->
		                
		                
		                <!-- drug query -->
		                <div class="col-sm-5">
			                <form method="POST" action="/drugprog1" accept-charset="UTF-8" autocomplete="off" name="findForm"><input name="_token" type="hidden" value="fCFVRApoko2AgBExIGU8gvtgtvexKCw3MQpJvTKb">
			                	<div class="form-group">
					                <div class="col-sm-8 col-sm-offset-2">
					                    <table class="table table-bordered" id="tbl_posts" style="text-align: center">
						                    <thead>
						                        <tr>
						                            <td style="text-align: center"><b>Repositioning Entity Name</b></td>
						                        </tr>
						                    </thead>
						                    <tbody id="tbl_posts_body">
						                        <tr id="rec-1">
						                            <td>
						                                <input type="text" id="search_query_dr" name="drugname[]" class="form-control"/>
						                            </td>
						                            
						                        </tr>
						                    </tbody>
						                </table>
						                <div style="text-align: right">
							            	<button class="btn btn-default">Cancel</button>
											<button type="submit" class="btn" id="submit" style="background-color: #163851; color: white;">Submit</button>
							            </div>  
					                </div>
								</div>                
							</form>      
						</div>
						<div class="col-sm-1">
							|</br>
							|</br>
							|</br>
							|</br>
							|</br>
							|</br>
							|</br>
							|</br>
							</br>
						</div>
						
						
						<!-- drug & disease query -->	
						<div class="col-sm-6">
			                <form method="POST" action="/drugprog2" accept-charset="UTF-8" autocomplete="off" name="findForm"><input name="_token" type="hidden" value="fCFVRApoko2AgBExIGU8gvtgtvexKCw3MQpJvTKb">
			                	<div class="form-group">
					                <div class="col-6 col-md-4">
					                	<table class="table table-bordered" id="tbl_posts" style="text-align: center">
						                    <thead>
						                        <tr>
						                            <td style="text-align: center"><b>Drug Entity Name</b></td>
						                        </tr>
						                    </thead>
						                    <tbody id="tbl_posts_body">
						                        <tr id="rec-1">
						                            <td>
						                                <input type="text" id="search_query_dr2" name="drugname[]" class="form-control"/>
						                            </td>
						                            
						                        </tr>
						                    </tbody>
						                </table>
									</div>
									<div class="col-6 col-md-4">
										
										<table class="table table-bordered" id="tbl_posts" style="text-align: center; border:none">
						                    <thead>
						                        <tr>
						                            <td style="text-align: center"><b>∩</b></td>
						                        </tr>
						                    </thead>
						                </table>
									</div>
					                <div class="col-6 col-md-4">							
					                    <table class="table table-bordered" id="tbl_posts" style="text-align: center">
						                    <thead>
						                        <tr>
						                            <td style="text-align: center"><b>Disease Name</b></td>
						                        </tr>
						                    </thead>
						                    <tbody id="tbl_posts_body">
						                        <tr id="rec-1">
						                            <td>
						                                <input type="text" id="search_query_disease" name="diseasename[]" class="form-control"/>
						                            </td>
						                            
						                        </tr>
						                    </tbody>
						                </table>
						                <div style="text-align: right">
							            	<button class="btn btn-default">Cancel</button>
											<button type="submit" class="btn" id="submit" style="background-color: #163851; color: white;">Submit</button>
							            </div>  
					                </div>
								</div>                
							</form>      
						</div>								  
				</div>
		</div>	

    <!-- .modal -->
    <div class="modal fade" id="myCartModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              
              <h4 class="modal-title">Load stored results</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                                                                            </div>    
            </div>
            <div class="modal-footer">
                            
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
<!-- /.modal -->

<!-- .modal -->
    <div class="modal fade" id="loadModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">Loading Now</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    Please Wait..
                        <i class="fa fa-refresh fa-spin"></i>
                </div>    
            </div>
            <div class="modal-footer">
            
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
<!-- /.modal --> 	
 	
</div>      
<footer class="main-footer">
<div class="pull-right hidden-xs">
    <b>Version</b> 1.0
</div>
<strong>Copyright © 2019 <a href="http://bike.snu.ac.kr">BiKE LAB</a>.</strong> All rights
reserved.
</footer>
      <!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
    <script src="${pageContext.request.contextPath}/resources/static/bower_components/jquery/dist/jquery.min.js"></script>    
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="http://bike-bee.snu.ac.kr/js/finder.js?newversion"></script>
    <script src="${pageContext.request.contextPath}/resources/static/search_drugreposition.js"></script>
    
</body>
</html>