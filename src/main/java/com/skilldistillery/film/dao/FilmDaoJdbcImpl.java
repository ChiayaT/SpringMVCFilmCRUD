package com.skilldistillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver");
			throw new RuntimeException("Unable to load MySQL Driver class");
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor createActor(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film createFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteActor(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveActor(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getLanguage(int langNumber) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Film> findFilmWithKeyword(String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
