package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMesure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMesureRepository;
import guru.springframework.services.RecipeService;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

	private final RecipeService recipeService; 
	
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
    }
}
