
<%@ page  contentType="text/html; charset=utf-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="UI/CSS/login.css">
<link rel="stylesheet" type="text/css" href="UI/CSS/bootstrap.css">
<title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>

	<hgroup>
          <img  class="icon" src="UI/img/hamtag-logo.png" >
          
</hgroup>



<c:if test="${not empty error}">
        <div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
</c:if>

<form name='loginForm'
        action="<c:url value='/login' />" method='POST'>

        <table>
                <div  class="group">
                        <input type='text' name='username'>
                        <span class="bar"></span>
                        <label>نام کاربری</label>
                </div>
                <div class="group">
                        <input type='password' name='password' />
                        <span class="bar"></span>
                        <label>رمز</label>
                </div>
                <input  class="btn btn-primary btn-lg btn-block" type="submit" value="ﻭﺭﻭﺩ" />
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />

</form>
	<footer>
		<p class="eng">Copyright to Hamtag</p>
	</footer>

<script src="UI/Javascript/efect.js"></script>

</body>
</html>
