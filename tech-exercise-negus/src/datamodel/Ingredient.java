package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE ingredient (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ingredient_id") // specify the column name. Without it, it will use method name
   private Integer ingredient_id;

   @Column(name = "name")
   private String name;
   
   @ManyToOne
   @JoinColumn(name="recipe_id", nullable=false)
   private Recipe recipe;

   public Ingredient() {
   }

   public Ingredient(Integer id, String name) {
      this.ingredient_id = id;
      this.name = name;
   }

   public Ingredient(String name) {
      this.name = name;
   }

   public Integer getId() {
      return ingredient_id;
   }

   public void setId(Integer id) {
      this.ingredient_id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   public Recipe getRecipe() {
	   return recipe;
   }
   
   public void setRecipe(Recipe recipe) {
	   this.recipe = recipe;
   }
   

   @Override
   public String toString() {
      return "Ingredient: " + this.ingredient_id + ", " + this.name;
   }
}