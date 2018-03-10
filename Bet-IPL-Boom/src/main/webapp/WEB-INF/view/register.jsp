<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
  xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="description" content="User account registration" />
  <meta name="author" content="Amir Boroumand" />
  <link rel="icon" href="favicon.ico" />

  <title>Registration Page</title>


</head>

<body >

  <div >
    <div>
      
      <h4>New User Registration</h4>
    <c:if test="${not empty errormessage}">
  		<h2>${errormessage}</h2>
	</c:if>
	<form:form method="post" modelAttribute="userForm" action="/register">
		<form:input path="firstName" type="text" /> <!-- bind to user.name-->
		<form:input path="lastName" type="text" />
		<form:input path="email" type="text" />
	
		<input type="submit" value="Submit"/>
	</form:form>
	
     
    </div>
  </div>



 
</body>

</html>