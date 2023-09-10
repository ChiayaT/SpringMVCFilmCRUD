package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = "getFilm.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView getFilm(@RequestParam int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/film.jsp");
		Film film = null;
		try {
			film = filmDao.findFilmById(filmId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("film", film);
//		get film from DAO, add to model to be displayed in JSP
		return mv;
	}

	@RequestMapping(path = "getFilm.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView getFilm(@RequestParam String keyword) {
		List<Film> films = null;
		ModelAndView mv = new ModelAndView();
		try {
			films = filmDao.findFilmWithKeyword(keyword);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("WEB-INF/film.jsp");
		mv.addObject("films", films);
//		get film from DAO, add to model to be displayed in JSP
		return mv;
	}

	@RequestMapping(path = "getFilm.do", params = "delete", method = RequestMethod.GET)
	public ModelAndView deletefilm(@RequestParam int delete) {
		boolean deleted = false;
		Film film = null;
		System.out.println(delete);
		try {
			film = filmDao.findFilmById(delete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		deleted = filmDao.deleteFilm(film);
		mv.setViewName("WEB-INF/delete.jsp");
		mv.addObject("deleted", deleted);
		mv.addObject("film", film);
//		get film from DAO, add to model to be displayed in JSP
		return mv;
	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.GET)
	public ModelAndView addFilm(HttpServletRequest req, HttpServletResponse resp) {
		Film film = new Film();
		film.setTitle(req.getParameter("title"));
		film.setDescription(req.getParameter("description"));
		film.setReleaseYear(Integer.parseInt(req.getParameter("releaseYear")));
		film.setLanguageId(Integer.parseInt(req.getParameter("languageId")));
		film.setRentalDuration(Integer.parseInt(req.getParameter("rentalDuration")));
		film.setRentalRate(Double.parseDouble(req.getParameter("rentalRate")));
		film.setLength(Integer.parseInt(req.getParameter("length")));
		film.setReplacementCost(Double.parseDouble(req.getParameter("replacementCost")));
		film.setRating(req.getParameter("rating"));
		film.setSpecialFeatures(req.getParameter("features"));
		ModelAndView mv = new ModelAndView();
		Film newFilm = filmDao.createFilm(film);
		System.out.println(newFilm);
		mv.addObject("film", newFilm);
		mv.setViewName("WEB-INF/add.jsp");

		return mv;

	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.GET)
	public ModelAndView updateFilm(HttpServletRequest req, HttpServletResponse resp) {
		int change = 0;
		Film changedFilm = null;
		Film ogFilm = null;
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			ogFilm = filmDao.findFilmById(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		int releaseYear = Integer.parseInt(req.getParameter("release_year"));
		int langId = Integer.parseInt(req.getParameter("language_id"));
		int rentDur = Integer.parseInt(req.getParameter("rental_duration"));
		double rate = Double.parseDouble(req.getParameter("rental_rate"));
		int length = Integer.parseInt(req.getParameter("length"));
		double repCost = Double.parseDouble(req.getParameter("replacement_cost"));
		String rating = req.getParameter("rating");
		String features = req.getParameter("features");
		
		
		
		
			if (!ogFilm.getTitle().equals(title)) {
				if (filmDao.updateFilm(ogFilm, "title", title));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (!ogFilm.getDescription().equals(description)) {
				if (filmDao.updateFilm(ogFilm, "description", description));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ogFilm.getReleaseYear() != (releaseYear)) {
				if (filmDao.updateFilm(ogFilm, "release_year", releaseYear + ""));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ogFilm.getLanguageId() != (langId)) {
				if (filmDao.updateFilm(ogFilm, "language_id", langId +""));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ogFilm.getRentalDuration() !=rentDur) {
				if (filmDao.updateFilm(ogFilm, "rental_duration", rentDur + ""));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ogFilm.getRentalRate() != (rate)) {
				if (filmDao.updateFilm(ogFilm, "rental_rate", rate + ""));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ogFilm.getLength() != (length)) {
				if (filmDao.updateFilm(ogFilm, "length", length + ""));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ogFilm.getReplacementCost() != (repCost)) {
				if (filmDao.updateFilm(ogFilm, "replacement_cost", repCost + ""));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (!ogFilm.getRating().equals(rating)) {
				if (filmDao.updateFilm(ogFilm, "rating", rating));
				try {
					changedFilm = filmDao.findFilmById(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (!ogFilm.getSpecialFeatures().equals(features)) {
				if (filmDao.updateFilm(ogFilm, "special_features", features));
				try {
					changedFilm = filmDao.findFilmById(id);
					//changedFilm.setSpecialFeatures(features);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		ArrayList<String> values = new ArrayList<>();
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", changedFilm);
		mv.setViewName("WEB-INF/update.jsp");

		return mv;

	}
}
