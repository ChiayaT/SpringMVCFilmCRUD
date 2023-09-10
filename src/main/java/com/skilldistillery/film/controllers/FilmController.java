package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

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

	@RequestMapping(path = "createFilm.do",  method = RequestMethod.GET)
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
}
