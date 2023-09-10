package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USERNAME = "student";
	private static final String PASSWD = "student";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;

		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);

		String sql = "SELECT * FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			// Here is our mapping of query columns to our object fields:
			String title = rs.getString("title");
			String desc = rs.getString("description");
			short releaseYear = rs.getShort("release_year");
			int langId = rs.getInt("language_id");
			int rentDur = rs.getInt("rental_duration");
			double rate = rs.getDouble("rental_rate");
			int length = rs.getInt("length");
			double repCost = rs.getDouble("replacement_cost");
			String rating = rs.getString("rating");
			String features = rs.getString("special_features");
			film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating, features);

			film.setActors(findActorsByFilmId(filmId));
		}
		rs.close();
		stmt.close();
		conn.close();// ...
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		// ...

		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));

			actor.setFilms(findFilmsByActorId(actorId));
		}
		actorResult.close();
		stmt.close();
		conn.close();// ...
		return actor;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		// Check if size is greater than zero
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Change Numbers to column names
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");

				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public String findCategoryByFilmId(int filmId) {
		String category = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			String sql = "SELECT name FROM category JOIN film_category ON category.id = film_category.category_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			category = rs.getString("name");

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			String sql = "SELECT id, first_name, last_name";
			sql += " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public List<Film> findFilmWithKeyword(String keyword) throws SQLException {
		int counter = 1;
		List<Film> films = new ArrayList<>();
		keyword = "%" + keyword + "%";

		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);

		String sql = "SELECT * FROM film WHERE description Like ? or title like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, keyword);
		stmt.setString(2, keyword);
		ResultSet rs = stmt.executeQuery();
		// Here is our mapping of query columns to our object fields:
		while (rs.next()) {
			int filmId = rs.getInt("id");
			String title = rs.getString("title");
			String desc = rs.getString("description");
			short releaseYear = rs.getShort("release_year");
			int langId = rs.getInt("language_id");
			int rentDur = rs.getInt("rental_duration");
			double rate = rs.getDouble("rental_rate");
			int length = rs.getInt("length");
			double repCost = rs.getDouble("replacement_cost");
			String rating = rs.getString("rating");
			String features = rs.getString("special_features");
			Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
					features);

			film.setActors(findActorsByFilmId(filmId));
			films.add(film);
			counter += 1;

		}
		rs.close();
		stmt.close();
		conn.close();// ...
		return films;
	}

	public String getLanguage(int langNumber) throws SQLException {
		String lang = "";
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			String sql = "SELECT name";
			sql += " FROM language JOIN film ON film.language_id = language.id " + " WHERE language_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, langNumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				lang = rs.getString("name");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lang;
	}

	public Actor createActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);
					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}

	public boolean saveActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;

	}

	public boolean deleteActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			int updateCount = stmt.executeUpdate();
			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			updateCount = stmt.executeUpdate();
			if (updateCount != 1) {
				System.out.println("ERROR");
				conn.rollback();
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public Film createFilm(Film film) {
		Connection conn = null;
		Film newFilm = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration,"
					+ " rental_rate, length, replacement_cost, rating, special_features) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			System.out.println(stmt);
			int updateCount = stmt.executeUpdate();
			System.out.println(updateCount + "sdfasdfasdfasdfasdf");
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					System.out.println("new film with id = " + newFilmId);
					film.setId(newFilmId);
					newFilm = film;
				}
			} else {
				film = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
		}
		return newFilm;
	}

	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film_actor WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			int updateCount = stmt.executeUpdate();
			sql = "DELETE FROM film WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updateCount = stmt.executeUpdate();
			if (updateCount != 1) {
				System.out.println("ERROR");
				conn.rollback();
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
}
