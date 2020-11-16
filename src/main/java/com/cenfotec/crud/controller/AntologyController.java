package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.crud.domain.Antology;
import com.cenfotec.crud.service.AntologyService;

import java.util.Optional;

@Controller
public class AntologyController {

	@Autowired
	AntologyService anthologyService;
	@Autowired
	ArticleService articleService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Antology());
		return "insertar";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Antology antology, BindingResult result, Model model) {
		anthologyService.save(antology);
		return "index";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("anthologies",anthologyService.getAll());
		return "listar";
	}

	@RequestMapping("/editar/{id}")
	public String bucarAntologiaId(Model model, @PathVariable long id) {
		Optional<Antology> possibleData = anthologyService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("anthologyToEdit",possibleData.get());
			return "editar";
		}
		return "notfound";
	}

	@RequestMapping(value="/editar/{id}",  method = RequestMethod.POST)
	public String salvarAntologiaEdit(Antology antology, Model model, @PathVariable long id) {
		anthologyService.save(antology);
		return "index";
	}

	@RequestMapping(value="/detalle/{id}")
	public String salvarAntologiaEdit(Model model, @PathVariable long id) {
		Optional<Antology> possibleData = anthologyService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("antologyData",possibleData.get());
			return "detalle";
		}
		return "notfound";
	}

	@RequestMapping(value="/agregarArticulo/{id}")
	public String recoverForAddArticle(Model model, @PathVariable long id) {
		Optional<Antology> antology = anthologyService.get(id);
		Article newArticle = new Article();
		if (antology.isPresent()) {
			newArticle.setAnthology(antology.get());
			model.addAttribute("antology",antology.get());
			model.addAttribute("article",newArticle);
			return "agregarArticulo";
		}
		return "notfound";
	}

	@RequestMapping(value="/agregarArticulo/{id}", method = RequestMethod.POST)
	public String saveArticle(Article article, Model model, @PathVariable long id) {
		Optional<Antology> antology = anthologyService.get(id);
		if (antology.isPresent()) {
			article.setAnthology(antology.get());
			articleService.save(article);
			return "index";
		}
		return "errorArticle";
		/*
		Article newArticle = new Article();
		if (antology.isPresent()) {
			Antology updatedAntology = antology.get();
			updatedAntology.getArticles().add(article);
			anthologyService.save(updatedAntology);
			return "listar";
		}*/
		/*return "notfound";*/
	}
}
