<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Film</title>
</head>
<body>
	<c:choose>
		 <c:when test="${! empty film}"> 
		 ${film.title} has been added<br>
		 film id is ${film.id}
	 	</c:when> 
		<c:otherwise>
		your film has not been added
		</c:otherwise>
	</c:choose>



</body>
</html>