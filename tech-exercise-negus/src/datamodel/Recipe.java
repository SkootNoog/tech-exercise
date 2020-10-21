package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "name", nullable = false, columnDefinition = "varchar(100)")
   private String name;

   @Column(name = "description", columnDefinition = "varchar(10000)")
   private String description;
   
   @Column(name = "ingredients", columnDefinition = "varchar(10000)")
   private String ingredients;

   @Column(name = "instructions", columnDefinition = "varchar(10000)")
   private String instructions;
   
   public Recipe() {
   }

   public Recipe(Integer id, String name, String description, String ingredients, String instructions) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.ingredients = ingredients;
      this.instructions = instructions;
   }

   public Recipe(String name, String description, String ingredients, String instructions) {
      this.name = name;
      this.description = description;
      this.ingredients = ingredients;
      this.instructions = instructions;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
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
   
   public String getInstructions(){
	   return instructions;
   }
   
   public void setInstructions(String instructions) {
	   this.instructions = instructions;
   }
   
   public String getIngredients(){
	   return ingredients;
   }
   
   public void setIngredients(String ingredients) {
	   this.ingredients = ingredients;
   }

   @Override
   public String toString() {
      return "Recipe: " + this.id + ", " + this.name + ", " + this.description + ", " + this.ingredients + ", " +this.instructions;
   }
}