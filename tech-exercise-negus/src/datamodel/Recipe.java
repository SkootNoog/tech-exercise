package datamodel;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE recipe (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "recipe")
public class Recipe {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "recipe_id") // specify the column name. Without it, it will use method name
   private Integer recipe_id;

   @Column(name = "name")
   private String name;

   @Column(name = "description")
   private String description;
   
   @OneToMany(mappedBy="recipe")
   private List<Ingredient> ingredients;

   @OneToMany(mappedBy="recipe")
   private List<Instruction> instructions;
   
   public Recipe() {
   }

   public Recipe(Integer id, String name, String description) {
      this.recipe_id = id;
      this.name = name;
      this.description = description;
   }

   public Recipe(String name, String description) {
      this.name = name;
      this.description = description;
   }

   public Integer getId() {
      return recipe_id;
   }

   public void setId(Integer id) {
      this.recipe_id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDesc() {
      return description;
   }

   public void setDesc(String description) {
      this.description = description;
   }
   
   public List<Instruction> getInstruction(){
	   return instructions;
   }
   
   public void setInstructions(List<Instruction> instructions) {
	   this.instructions = instructions;
   }
   
   public List<Ingredient> getIngredients(){
	   return ingredients;
   }
   
   public void setIngredients(List<Ingredient> ingredients) {
	   this.ingredients = ingredients;
   }

   @Override
   public String toString() {
      return "Recipe: " + this.recipe_id + ", " + this.name + ", " + this.description;
   }
}