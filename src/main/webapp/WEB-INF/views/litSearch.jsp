<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>Bike-DrugProg</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
	<link rel="stylesheet"
		href="http://bike-bee.snu.ac.kr/css/bootstrap.css">
	<link rel="stylesheet"
		href="http://bike-bee.snu.ac.kr/css/usebootstrap.css">
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" type="text/css"
		href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css" />
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

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
        <h3 class="page-header">Literature Search</h3>
    </div>
    <div class="col-lg-12" id="searchContent">
    	<!-- <p>search drug name: <b>${drugname}</b></p> -->
    </div>
    <div class="col-lg-12">
			<div class="col-xs-3">
				<select id="search_type" class="form-contorl" name="search_type"
					style="width: 100%; height: 34px; border-color: #d2d6de;">
					<option value="disease_name">Disease Name</option>
					<option value="geneSymbol">Gene symbol</option>
					<option value="pmid">pmid</option>
				</select>
			</div>

			<div class="col-xs-9">
				<div class="input-group input-group">
					<input id="search_query" type="text" class="form-control"
						placeholder="Glioma" /> <span class="input-group-btn">
						<button id="search_button" type="button"
							class="btn btn-gray btn-flat">Search</button>
					</span>
				</div>
			</div>
		</div>
    
	<div class="table table-bordered table-hover dataTable" id="result"></div>


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
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2019 <a href="http://bike.snu.ac.kr">Biomedical Knowledge Engineering Laboratory (BiKE Lab)</a>.</strong> All rights
    reserved.
  </footer>


<!-- ./wrapper -->
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
	<script src="${pageContext.request.contextPath}/resources/static/search.js"></script>

  </body>
</html>
