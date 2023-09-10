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
      <br>
      	<form action="updateFilm.do" method="get">
		<h3>Update Film?</h3>
		<br>

		Title:
		<input type="text" name="title" value="${film.title}" required="required field"><br>
		Description:
		<input type="text" name="description" value="${film.description}"><br>
		Release Year:
		<input type="number" name="release_year" value="${film.releaseYear}"><br>
		Language_id:
		<select name="language_id" >
		
			<option value="${film.languageId}">Keep Same</option>
			<option value="1">English</option>
			<option value="2">Italian</option>
			<option value="3">Mandarin</option>
			<option value="4">French</option>
			<option value="5">German</option>
		</select>
		<br>
		Rental Duration:
		<input type="number" name="rental_duration" value="${film.rentalDuration}"><br>
		Rental Rate:
		<input type="number" name="rental_rate" value="${film.rentalRate}"><br>
		Length of Film:
		<input type="number" name="length" value="${film.length}"><br>
		Replacement Cost
		<input type="number" name="replacement_cost" value="${film.replacementCost}"><br>
		Rating:
		<select name="rating" >
			
			<option value="${film.rating}">Keep Same</option>
			<option value="G">G</option>
			<option value="PG">PG</option>
			<option value="PG13">PG13</option>
			<option value="R">R</option>
			<option value="NC17">NC17</option>
		</select>
		<br>
		Features:
		<select name="features" >
			
			<option value="${film.specialFeatures}">Keep Same</option>
			<option value="Trailers">Trailers</option>
			<option value="Commentaries">Commentaries</option>
			<option value="Deleted Scenes">Deleted Scenes</option>
			<option value="Behind the Scenes">Behind the Scenes</option>
		</select>
		<br>

		<button name="id" value="${film.id }">Update Film</button>
	</form>
    </c:when>
    <c:otherwise>
    No film found
    </c:otherwise>
    </c:choose>
    </c:otherwise>
    </c:choose>
    
    
</body>
</html>