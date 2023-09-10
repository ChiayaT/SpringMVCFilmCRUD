package com.skilldistillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	  public Film findFilmById(int filmId)throws SQLException;
	  public List<Actor> findActorsByFilmId(int filmId);

		public Actor findActorById(int actorId)throws SQLException;
		public Actor createActor(Actor actor);
		public Film createFilm(Film film);
		public boolean deleteActor(Actor actor);
		public boolean deleteFilm(Film film);
		public boolean saveActor(Actor actor);
		public String getLanguage(int langNumber) throws SQLException;
		public List<Film> findFilmWithKeyword(String keyword) throws SQLException;
		public boolean updateFilm(Film film, String field, String param);
}
