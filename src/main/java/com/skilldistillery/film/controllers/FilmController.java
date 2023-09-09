package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.film.dao.FilmDAO;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDao;
	
	@RequestMapping(path = "getFilm.do", method=RequestMethod.GET)
	public String getFilm(@RequestParam Integer filmId, Model model) {
		System.out.println("filmID: " + filmId);
		model.addAttribute("filmId", filmId);
		return "WEB-INF/film.jsp";
	}
}
