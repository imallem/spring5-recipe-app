package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMesure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMesureRepository;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

	private CategoryRepository categoryRepository; 
	private UnitOfMesureRepository unitOfMesureRepository; 
	
	
    public IndexController(CategoryRepository categoryRepository, UnitOfMesureRepository unitOfMesureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMesureRepository = unitOfMesureRepository;
	}


	@RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
    
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMesure> unitOfMesure = unitOfMesureRepository.findByDescription("Teaspoon");
		System.out.println("cat id is : " + categoryOptional.get().getId());
		System.out.println("uom id is : " + unitOfMesure.get().getId());
		return "index";
    }
}
