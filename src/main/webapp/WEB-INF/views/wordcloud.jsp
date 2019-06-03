<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>
      Drug Repositioning
    </title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/bower_components/bootstrap/dist/css/bootstrap.min.css" />
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/bower_components/font-awesome/css/font-awesome.min.css" />
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/bower_components/Ionicons/css/ionicons.min.css" />
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css/AdminLTE.min.css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css/skins/skin-black.min.css" />
    <!--  img css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/img.css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">

  <header class="main-header">

    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>DR</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Drug</b>repositioning</span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">Categories</li>
        <li><a href="${pageContext.request.contextPath}/"><i class="fa fa-book"></i> <span>About</span></a></li>        
		<li class="active"><a href="${pageContext.request.contextPath}/wordcloud"><i class="fa fa-book"></i> <span>Prognostic word statistics</span></a></li>
		<li><a href="${pageContext.request.contextPath}/litsearch"><i class="fa fa-book"></i> <span>literature search</span></a></li>
        <li><a href="${pageContext.request.contextPath}/drugrepositor"><i class="fa fa-book"></i> <span>drug repositioning</span></a></li>
        <li><a href="${pageContext.request.contextPath}/dataset"><i class="fa fa-book"></i> <span>Dataset</span></a></li>
		<li><a href="${pageContext.request.contextPath}/contact"><i class="fa fa-book"></i> <span>Contact</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
    	<h1>Prognostic word statistics</h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
			<div class="box1">
				<img src="${pageContext.request.contextPath}/resources/img/disease_word_cloud.jpg" class="center">
			</div>
			<div class="center">
				<!-- <h4>histogram of word frequency associcated <b>disease</b> term with all of words.</h4> -->
				<h4>Frequency of words used with 'Disease'. Each bar represents the percentage of occurrences of words.</h4>
			</div>
			<hr>
			<hr>
			
			<div class="box2">
				<img src="${pageContext.request.contextPath}/resources/img/wordcloud_without_axisoff_disease0509.jpg" class="center">
			</div>
			<div class="center">
				<h4>word coud associcated <b>disease</b> term with all of words.</h4>
			</div>
			<hr>
			<hr>
			
			<div class="box3">
				<img src="${pageContext.request.contextPath}/resources/img/disease_positive_exp_word.jpg" class="center">
			</div>	
			<div class="center">
				<h4>histogram of word frequency associcated <b>positive expression</b> terms.</h4>
			</div>
			<hr>
			<hr>
			
			<div class="box4">
				<img src="${pageContext.request.contextPath}/resources/img/wordcloud_positiveExp_disease.jpg" class="center">
			</div>
			<div class="center">
				<h4>word coud associcated <b>positive expression</b> terms.</h4>
			</div>
			<hr>
			<hr>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2019 <a href="http://bike.snu.ac.kr">Biomedical Knowledge Engineering Laboratory (BiKE Lab)</a>.</strong> All rights
    reserved.
  </footer>

</div>
<!-- ./wrapper -->
    <!-- jQuery 3 -->
    <script src="${pageContext.request.contextPath}/resources/static/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="${pageContext.request.contextPath}/resources/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="${pageContext.request.contextPath}/resources/static/bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="${pageContext.request.contextPath}/resources/static/dist/js/adminlte.min.js"></script>
	<!-- Sparkline -->
	<script src="${pageContext.request.contextPath}/resources/static/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- SlimScroll -->
	<script src="${pageContext.request.contextPath}/resources/static/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
  </body>
</html>
