package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Description;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMesure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMesureRepository;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private RecipeRepository recipeRepository;
	private CategoryRepository categoryRepository; 
	private UnitOfMesureRepository unitOfMesureRepository;
	
	
	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMesureRepository unitOfMesureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMesureRepository = unitOfMesureRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		recipeRepository.saveAll(getRecipies());
	}

	private List<Recipe> getRecipies() {
		List<Recipe> recipes = new ArrayList<>(2);
		Optional<UnitOfMesure> eachUOomOtional = unitOfMesureRepository.findByDescription("Each");
		if (!eachUOomOtional.isPresent()) {
			throw new RuntimeException("Expected UOM not found : Each");
		}
		Optional<UnitOfMesure> tablespoonUomOptional = unitOfMesureRepository.findByDescription("Tablespoon");
		if (! tablespoonUomOptional.isPresent())
		{
			throw new RuntimeException("expected UOM not found : Tablespoon");
		}
		Optional<UnitOfMesure> teaspoonUomOptional = unitOfMesureRepository.findByDescription("Teaspoon");
		if (! teaspoonUomOptional.isPresent())
		{
			throw new RuntimeException("expected UOM not found : Teaspoon");
		}
		Optional<UnitOfMesure> dashUomOptional = unitOfMesureRepository.findByDescription("Dash");
		if (! dashUomOptional.isPresent())
		{
			throw new RuntimeException("expected UOM not found : Dash");
		}
		Optional<UnitOfMesure> PintUomOptional = unitOfMesureRepository.findByDescription("Pint");
		if (! PintUomOptional.isPresent())
		{
			throw new RuntimeException("expected UOM not found : Pint");
		}
		Optional<UnitOfMesure> CupUomOptional = unitOfMesureRepository.findByDescription("Cup");
		if (! CupUomOptional.isPresent())
		{
			throw new RuntimeException("expected UOM not found : Cup");
		}
		
		UnitOfMesure eachUOM       = eachUOomOtional.get();
		UnitOfMesure tablespoonUOM = tablespoonUomOptional.get();
		UnitOfMesure teaspoonUOM   = teaspoonUomOptional.get(); 
		UnitOfMesure dashUOM       = dashUomOptional.get();
		UnitOfMesure PintUOM       = PintUomOptional.get();
		UnitOfMesure CupUOM           = CupUomOptional.get();
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if ( ! americanCategoryOptional.isPresent()) {
			throw new RuntimeException("expected category not found : American");
		}
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if ( ! mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("expected cateogory not found : Mexican");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory  = mexicanCategoryOptional.get();
		// Yummyu Guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon." +
				"\n" +
				"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
				"\n" + 
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
				"\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\r\n" + 
				"\n" + 
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\r\n" + 
				"\n" + 
				"4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve." +
				"Read more on : https://https://www.simplyrecipes.com/recipes/perfect_guacamole/"
				);
		Notes guacNotes = new Notes(guacRecipe);
		guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados." +
				"feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!");
		
		
		guacRecipe.setNotes(guacNotes);
		guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUOM, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(5), teaspoonUOM, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("fresh line juice or lemon juice", new BigDecimal(2), tablespoonUOM, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("minced red onion or thnly sliced green onion", new BigDecimal(2) , tablespoonUOM, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUOM, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUOM, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("freshly frated black pepper", new BigDecimal(2), dashUOM, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulo removed, chopped", new BigDecimal(5), eachUOM, guacRecipe));
		
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(guacRecipe);
		
		//Yummy Tacos
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grillled chicken tacos");
		tacosRecipe.setCookTime(9);;
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\r\n" + 
				"\r\n" + 
				"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\r\n" + 
				"\r\n" + 
				"Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
				"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes." +
				"\n" +
				"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side." +
				"\n" +
				"Wrap warmed tortillas in a tea towel to keep them warm until serving." + 
				"\n" +
				"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		Notes tacosNotes = new Notes(tacosRecipe);
		tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\r\n" + 
				"\r\n" + 
				"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house." +
				"Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\r\n" + 
				"\r\n" + 
				"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!"				
				);
		tacosRecipe.setNotes(tacosNotes);
		
		tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Dried OreganoPowder", new BigDecimal(1), teaspoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Salt OreganoPowder", new BigDecimal(5), teaspoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Clove of Garlic, chopped", new BigDecimal(1), eachUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Finely gated orange zest", new BigDecimal(1), tablespoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Frech-squeezed orange juice", new BigDecimal(3), tablespoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoonUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("small corn tortillas", new BigDecimal(8), eachUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Packed baby arugula", new BigDecimal(3), CupUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Medium ripe avocado, slic", new BigDecimal(2), eachUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Cherry tomatoes, halved", new BigDecimal(5), PintUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("redonion, thinly sliced", new BigDecimal(25), eachUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Cup sour cream, thinned with 1/4 cup milk", new BigDecimal(4), CupUOM,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Lime, cut into wedges", new BigDecimal(4), eachUOM,tacosRecipe));
		
		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory);
		recipes.add(tacosRecipe);
		
		return recipes; 
		
	}
	
}
