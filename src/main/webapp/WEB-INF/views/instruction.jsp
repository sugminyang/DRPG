<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
        <li><a href="${pageContext.request.contextPath}/"><i class="fa fa-book"></i> <span>About</span></a></li>        
		<!--  <li><a href="${pageContext.request.contextPath}/wordcloud"><i class="fa fa-book"></i> <span>Prognostic word statistics</span></a></li> -->
		<li><a href="${pageContext.request.contextPath}/litsearch"><i class="fa fa-book"></i> <span>literature search</span></a></li>
        <li><a href="${pageContext.request.contextPath}/drugrepositor"><i class="fa fa-book"></i> <span>drug repositioning</span></a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/instruction"><i class="fa fa-book"></i> <span>instruction</span></a></li>
        <!--  <li class="active"><a href="${pageContext.request.contextPath}/dataset"><i class="fa fa-book"></i> <span>Dataset</span></a></li> -->
		<li><a href="${pageContext.request.contextPath}/contact"><i class="fa fa-book"></i> <span>Contact</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>instruction</h1>
			</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<h3>
							<p style="color: #FF0000";>- Literature Search section</p>
						</h3>
						</hr>
						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/1_literature_categories.png"
								class="center" width="100%">
								<blockquote>
									<p>키워드를 이용한 논문 검색은 총 3가지 유형으로 구성되어 있다. 첫번째는 Disease Name을
										이용하여 pubmed에서 abstract를 추출하고, better prognosis와 관련된 target
										disease, gene, SNP 정보를 확인할 수 있다. 두번째는 Gene symbol을 이용한 검색
										방벙으로,연구자가 관심 있는 유전자가 포함된 논문들 중 better survival을 유도하는 정보를 포함하는
										결과를 제공한다. 세번째는 pmid를 이용한 논문의 직접적인 접근을 허용한다. 연구자가 원하는 논문의 pmid를
										이용하여 논문을 제공한다.</p>
								</blockquote>
						</div>
						</hr>

						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/2_literature_diseaseName_search_result.png"
								class="center" width="100%">
								<blockquote>
									<p>위의 이미지는 검색 카테고리를 disease name으로 설정하고 keywor를 breast
										neoplasms로 설정한 예시이다. 엔터 또는 우측의 Search버튼을 이용해 검색을 수행할 수 있다. 첫번째
										나오는 column은 해당 disease-gene-SNP triplet 정보를 포함하는 원본 논문을 의미한다.
										이어서 better prognosis를 보인다고 연구된 disease와 이를 유도하는 gene 그리고
										SNP정보가 순서대로 표현되어 있다. 대다수의 triplet데이터의 SNP는 공백으로 표현되어 있는데, 이는
										논문에서 target gene이 disease의 prognosis에 긍정적인 영향을 보인다는 정보만 포함하기
										때문이다. 마지막으로 NER viz colunm에는 abstract에서 표현되어 있는 disease들과
										gene, snp를 가시적으로 보여준다.</p>
								</blockquote>
						</div>
						</hr>

						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/3_paperviz.png"
								class="center" width="100%">
								<blockquote>
									<p>literature search page에서 NER viz의 pmid를 클릭하면 위와 같은 페이지가
										실행된다. 이 페이지의 정보는 NCBI의 pubtator를 활용하여 제공한다. Disease, Gene, SNP
										Named Entity Recognition의 결과를 서로 다른 색깔로 표현해주고 있다. 현재까 NER 연구에서
										Gene, Disease Name, SNP를 detection하는 알고리즘이 계속 개발 중이다. 이
										abstract로부터 우리 연구에서 사용할 triplet data를 자동으로 생성하였다.</p>
								</blockquote>
						</div>
						</hr>

						<h3>
							<p style="color: #FF0000";>- Drug Repositioning section</p>
						</h3>
						</hr>
						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/4_dr_category_types.png"
								class="center" width="100%">
								<blockquote>
									<p>다음으로 우리 연구의 메인인 drug repositioning section에서는 앞에서와 마찬가지로
										3가지 검색 카테고리를 제공한다. 첫번째는 Disease Name을 이용하여 원하는 질병에 승인된 약물들과
										약물의 target gene, 작용 방법, external reference를 표현한다. 두번째로 Gene
										symbol을 이용하여 여러개의 관심있는 target gene과 작용하는 drug들을 살펴볼 수 있다. 이들은
										서로 다른 작용 매커니즘을 갖을 수 있다. 또한, target gene에 따라 다양한 disease와 연관되어
										치료되고 있거나 치료 될 수 있는 후보 disase를 찾을 수 있다. 마지막으로, chemical name은
										관심있는 약물의 새로운 target을 찾아 target gene과 관련된 치료 가능한 후보 disease를
										제시한다.</p>
								</blockquote>
						</div>
						</hr>

						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/5_drug types.png"
								class="center" width="100%">
								<blockquote>
									<p>다음은 약물을 분류하는 3가지 종류를 설명한다. 먼저 approved drug은 해당 약물이 FDA
										approved drug이며, withdrawn이력이 없는 약물을 보여준다. 두번째는 candidate
										drug으로 FDA승인 과정에서 interrupted된 drug을 의미한다. candidate option을
										선택하면 오직 phase 1,2,3인 drug들을 보여준다. 상당히 많은 종류 약물들이 phase 0에서
										중단되었는데, 이는 연구의 부득이한 종료나 preclinical단계에서 중단되어 repositioning 될 수
										없다고 판단하여 사용하지 않았다.</p>
								</blockquote>
						</div>
						</hr>

						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/6_dr_searchGeneSymbol.png"
								class="center" width="100%">
								<blockquote>
									<p>첫번째 drug repositioning의 첫번째 예제는 Gene symbol을 이용한 검색이다. 위의 그림은 ERBB2 gene을 입력한 예시이다.
										better prognosis와 관련된 target gene을 입력했다면 아래와 같은 결과를 얻을 수 있다. 유전자 별로 상이한 개수의 결과셋을 얻게 된다.
										먼저, GEFITINIB은 ERBB2에 inhibitor로 작용하며, ERBB2는 breast neoplasm, machado-joseph disesase, colorectal neoplasms, stomach neoplasms에 처치 가능할 것으로 보인다.
										기존의 약물은 Carcinoma, Non-Small-Cell Lung and Neoplasms에서 approved drug으로 사용되고 있으며, Breast Neoplasms, Urinary Bladder Neoplasms에서는 phase 3까지 진행된 바 있다.
										이 약물들은 여러 소스를 통해서 cross validation이 가능하다. Gene을 클릭하면 MalaCard website와 연동되고, Drug name을 클릭하면 DGIdb 데이터를 추가로 확인할 수 있다.
										또한 chemblID를 통해서는 EBI에서 개발한 chembl database와 external link를 제공한다.
									</p>
								</blockquote>
						</div>
						</hr>

						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/7_dr_searchByGeneSymbolWithCandidateDrugs.png"
								class="center" width="100%">
								<blockquote>
									<p> 위 이미지는 검색된 drug에서 candidate drug들로 필터링한 결과를 보여준다. 
										이 옵션은 기존에 FDA승인되지 않은 약물을 새로운 disease의 target therapy로 사용 가능성을 보여준다.
										약물들은 phase 3 ~ phase 1 까지 정렬되어 표현된다.  
									</p>
								</blockquote>
						</div>
						</hr>

						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/8_dr_searchDiseaseName.png"
								class="center" width="100%">
								<blockquote>
									<p> 두번째 drug repositioning 옵션은 disease name을 이용하여 사용한다.
										위의 캡쳐는 disease name으로 glioma를 입력하였고, 이에 따른 FDA승인 약물들과 target gene을 살펴볼 수 있다.
									</p>
								</blockquote>
						</div>
						</hr>

						<div>
							<img
								src="${pageContext.request.contextPath}/resources/img/instruction/9_dr_searchByDrugName.png"
								class="center" width="100%">
								<blockquote>
									<p> 마지막으로 drug repositioning 옵션으로는 chemical name을 이용하는 방법이 있다. 
										이 검색 방법을 통해서 기존 약물의 indication과 target gene이 아닌 새로운 mechanism of action을 찾을 수 있을 것이다.
										chemical name을 입력하여 검색하는 경우에는 해당 약물이 FDA승인 약물이어야 한다.
										그리고 검색 결과의 좌측 하단에는 검색된 전체 데이터의 수가 표현되어 있고, 우측에는 결과가 paging되어 표시된다.
									</p>
								</blockquote>
						</div>
						</hr>
					</div>
				</div>
				</div>
			</div>
			</section>
			<!-- /.content -->

			</div>
			<!-- /.content-wrapper -->

			<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.0
			</div>
			<strong>Copyright &copy; 2019 <a
				href="http://bike.snu.ac.kr">Biomedical Knowledge Engineering
					Laboratory (BiKE Lab)</a>.
			</strong> All rights reserved. </footer>
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
