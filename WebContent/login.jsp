<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="_csrf" content="${_csrf.token}"/>
  <base href="http://<%= request.getLocalName() + ":" + request.getLocalPort() + request.getContextPath() + "/" %>">
  <script src="resources/jQuery/jquery-1.12.1.min.js"></script>
  <script src="resources/jQuery/jquery-ui-1.11.4/jquery-ui.min.js"></script>
  <script src="resources/jQuery/validate-1.1.2/jquery.validate.min.js"></script>
  
  <title>GRRecurso</title>
  <link rel="stylesheet" href="resources/login/css/style.css">
  <link rel="stylesheet" href="resources/jQuery/jquery-ui-1.11.4/jquery-ui.css">
  <link rel="stylesheet" href="resources/jQuery/jquery-ui-1.11.4/jquery-ui.theme.css">
  <link rel="stylesheet" href="resources/jQuery/jquery-ui-1.11.4/jquery-ui.structure.css">
  
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
  <script>
  	var queryString = function () {
	  // This function is anonymous, is executed immediately and 
	  // the return value is assigned to QueryString!
	  var query_string = {};
	  var query = window.location.search.substring(1);
	  var vars = query.split("&");
	  for (var i=0;i<vars.length;i++) {
	    var pair = vars[i].split("=");
	        // If first entry with this name
	    if (typeof query_string[pair[0]] === "undefined") {
	      query_string[pair[0]] = decodeURIComponent(pair[1]);
	        // If second entry with this name
	    } else if (typeof query_string[pair[0]] === "string") {
	      var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
	      query_string[pair[0]] = arr;
	        // If third or later entry with this name
	    } else {
	      query_string[pair[0]].push(decodeURIComponent(pair[1]));
	    }
	  } 
	  return query_string;
	}();
	
	function validate(){
		var msg = $("#messageDialog");
		var message = "";
		if($("#email").val() == ""){
			message += "<li>O login é obrigatório.</li>";
		}
		if($("#senha").val() == ""){
			message += "<li>A senha é obrigatória.</li>";
		}
		
		if(message != ""){
			msg.html("<ul>" + message + "</ul>");
			msg.dialog();			
			return false;
		} else {			
			return true;
		}
	}
	
	$(document).ready(function(){
		var msg = $("#messageDialog");
		
		if(queryString.mensagem){
			msg.html(queryString.mensagem.replace(/\+/g,' '));			
			msg.dialog();
			return;
		}
		
	});
  </script>
</head>
<body>
  <c:url value="/login" var="loginUrl"/>
  <form action="${loginUrl}" class="login" method="post">
  	<c:if test="${param.error != null}">
	<p>Usuário ou senha inválida.</p>
    </c:if>
    
    <c:if test="${param.logout != null}">
    <p>Deslogado.</p>
  	</c:if>
  	
  	<c:if test="${param.maxSessionLimit != null}">
    <p>O usuário já se encontra logado em outro browser ou máquina.</p>
  	</c:if>
  	
    <p>
      <label for="email">Email:</label>
      <input type="text" name="email" id="email" value="">
    </p>

    <p>
      <label for="senha">Senha:</label>
      <input type="password" name="senha" id="senha" value="">
    </p>

    <input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p class="login-submit">
      <button id="btnSubmit" type="submit" class="login-button" onclick="return validate()">Login</button>
    </p>
  </form>
  
 <div id="messageDialog" title="Basic dialog" style="display: none;">
 	<p><%= request.getAttribute("mensagem") %></p>
 </div>
  
</body>
</html>