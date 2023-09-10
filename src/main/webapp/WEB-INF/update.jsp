<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film</title>
<c:choose>
		 <c:when test="${! empty film}"> 
		 ${film.title} has been updated<br>
		 Film ID: ${film.id}<br>
		 Description: ${film.description}<br>
		 Release Year: ${film.releaseYear}<br>
		 Language id: ${film.languageId}<br>
		 Rental Rate: ${film.rentalRate}<br>
		 Length: ${film.length}<br>
		 Replacement Cost: ${film.replacementCost}<br>
		 Rating: ${film.rating}<br>
		 Features: ${film.specialFeatures}<br>
		 <br>
		 <a href="/MVCFilmSite/getFilm.do?delete=${film.id}"> Delete ${film.title }</a>
		 <a href="index.html">Home</a>
	 	</c:when> 
		<c:otherwise>
		ERROR: your film has not been updated
		</c:otherwise>
	</c:choose>