<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>
      DrugProg
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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="alternate" type="application/rdf+xml" href="$rdf_link?output=rdfxml" title="This page in RDF (XML)" />
    <link rel="alternate" type="text/turtle" href="$rdf_link?output=ttl" title="This page in RDF (Turtle)" />
  </head>
  
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">

  <header class="main-header">

    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>DR</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Drug</b>Prog</span>
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
        <li class="active"><a href="${pageContext.request.contextPath}/"><i class="fa fa-book"></i> <span>About</span></a></li>        
		<!-- <li><a href="${pageContext.request.contextPath}/wordcloud"><i class="fa fa-book"></i> <span>Prognostic word statistics</span></a></li> -->
		<li><a href="${pageContext.request.contextPath}/litsearch"><i class="fa fa-book"></i> <span>literature search</span></a></li>
        <li><a href="${pageContext.request.contextPath}/drugrepositor"><i class="fa fa-book"></i> <span>drug repositioning</span></a></li>
        <li><a href="${pageContext.request.contextPath}/instruction"><i class="fa fa-book"></i> <span>instruction</span></a></li>
        <!-- <li><a href="${pageContext.request.contextPath}/dataset"><i class="fa fa-book"></i> <span>Dataset</span></a></li> -->
		<li><a href="${pageContext.request.contextPath}/contact"><i class="fa fa-book"></i> <span>Contact</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
      About
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
              <h2 class="" style="font-family: 'Helvetica Neue';">Title of our research</h2>
              <hr />
              
              <h4>
                <b>Authors</b>
              </h4>
              <blockquote>
              	<p>
              		Sungmin Yang, kyungsik ha, Hong-Gee Kim <br />
              		BIKE Biomedical Knowledge Engineering Laboratory, Seoul National University
              	</p>
              </blockquote>
              
              <h4>
              	<b>Abstract</b>
              </h4>
              	<blockquote>
              		<p>
	              		DrugProg was developed to aid repurposing pre-existing drugs to several diseases by providing open-resource which connect prognostic status and molecular status, 
	              		and developing tools to identify targets for possible achievement of positive prognosis. 
	              		These could be useful to researchers who want to find more relevant drug targets and serve as a potential leverage to develop new computational methodologies for drug repurposing. 
	              		DrugProg currently has 3,230 drugs associated with 1,140 mutations, thus covering 3,616 targets from 823 disease types.
              		</p>
              	</blockquote>

              <h4>
                <b> Main process of our research</b>
              </h4>   
			  	<img src="${pageContext.request.contextPath}/resources/img/processOfourProject.png" class="center" width="100%">
              <h4>
                <b>Useful Links</b>
              </h4>         
        		<blockquote>
        			<p>
        				<a href='https://www.ncbi.nlm.nih.gov/CBBresearch/Lu/Demo/PubTator/index.cgi'>PubTator</a><br />
        			</p>
        		</blockquote>
            </div>
            <!-- /.box-body -->
          </div>
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
