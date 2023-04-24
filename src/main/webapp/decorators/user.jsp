<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href= <c:url value = "/templates/user/css/bootstrap.min.css"></c:url> rel="stylesheet">
  <link href= <c:url value = "/templates/user/css/card.css"></c:url> rel="stylesheet">
  <link href= <c:url value = "/templates/user/css/cart.css"></c:url> rel="stylesheet">
  
  
  <!-- Material Design Bootstrap -->
 <link
  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.css"
  rel="stylesheet"/>
  <link
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
  rel="stylesheet"
/>
<link
  href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
  rel="stylesheet"
/>
  <link href=<c:url value = "/templates/user/css/style.min.css"></c:url> rel="stylesheet">
  
  <script
  type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.js"
></script>
  <script type="text/javascript" src="	https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js"></script>
  <script type="text/javascript" src="/templates/userjs/bootstrap.min.js"></script>
  <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
  <style type="text/css">
    html,
    body,
    header,
    .carousel {
      height: 60vh;
    }

    @media (max-width: 740px) {

      html,
      body,
      header,
      .carousel {
        height: 100vh;
      }
    }

    @media (min-width: 800px) and (max-width: 850px) {

      html,
      body,
      header,
      .carousel {
        height: 100vh;
      }
    }
	.background{
	 background-color: #dcdcdc;
	}
  </style>
</head>
<body>
	<jsp:include page="/commons/user/hearder.jsp"></jsp:include>
	
	<dec:body/>
	
	<jsp:include page="/commons/user/footer.jsp"></jsp:include>
</body>
</html>