package guru.springframework.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id; 
	
	private String description; 
	private BigDecimal amount; 
	
	@ManyToOne
	private Recipe recipe;

	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMesure uom;
	
	


	public Ingredient(String description, BigDecimal amount, UnitOfMesure uom, Recipe recipe) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.recipe = recipe;
		this.uom = uom;
	}

	public UnitOfMesure getUom() {
		return uom;
	}

	public void setUom(UnitOfMesure uom) {
		this.uom = uom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	
	
}
