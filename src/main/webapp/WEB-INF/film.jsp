<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body>
	<c:choose>
	<c:when test="${! empty films}">
	<c:forEach var="film" items="${films}">
      ${film.title} :
      ${film.description}<br/>
      Rated: ${film.rating}
      <br>
      Category: ${film.category}
      <br>
      Actors: 
      <c:forEach var="actor" items="${film.actors}">
      ${actor.firstName} ${actor.lastName}
      <br>
      </c:forEach>
      <br>
      <a href="/MVCFilmSite/getFilm.do?delete=${film.id}"> Delete ${film.title }</a>
      <br>
    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:choose>
    <c:when test="${! empty film}">
      ${film.title}
      ${film.description}<br/>
      Rated: ${film.rating}
      <br>
      Category: ${film.category}
      <br>
      Actors: 
      <c:forEach var="actor" items="${film.actors}">
      ${actor.firstName} ${actor.lastName}
      <br>
      </c:forEach>
      <br>
      <a href="/MVCFilmSite/getFilm.do?delete=${film.id}"> Delete ${film.title }</a>
    </c:when>
    <c:otherwise>
    No film found
    </c:otherwise>
    </c:choose>
    </c:otherwise>
    </c:choose>
    
    
</body>
</html>