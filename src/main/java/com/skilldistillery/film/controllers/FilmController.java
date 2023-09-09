package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


	@RequestMapping(path = "getFilm.do", params="filmId" , method = RequestMethod.GET)
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
	@RequestMapping(path = "getFilm.do", params="keyword" , method = RequestMethod.GET)
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
	@RequestMapping(path = "getFilm.do", params="delete" , method = RequestMethod.GET)
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
	
	
}
