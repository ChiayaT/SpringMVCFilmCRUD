<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Deleted</title>
</head>
<body>
<c:choose>
<c:when test="${deleted == true }">
film was erased.
<a href="index.html">Go Back To Home</a>
</c:when>
<c:otherwise>
ERROR: could not delete ${film.title }
</c:otherwise>
</c:choose>

    
    
</body>
</html>