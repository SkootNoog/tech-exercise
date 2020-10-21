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
 CREATE TABLE instruction (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "instruction")
public class Instruction {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "instruction_id") // specify the column name. Without it, it will use method name
   private Integer instruction_id;

   @Column(name = "name")
   private String name;

   @Column(name = "description")
   private String description;
   
   @ManyToOne
   @JoinColumn(name="recipe_id", nullable=false)
   private Recipe recipe;

   public Instruction() {
   }

   public Instruction(Integer id, String name, String description) {
      this.instruction_id = id;
      this.name = name;
      this.description = description;
   }

   public Instruction(String name, String description) {
      this.name = name;
      this.description = description;
   }

   public Integer getId() {
      return instruction_id;
   }

   public void setId(Integer id) {
      this.instruction_id = id;
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
   
   public Recipe getRecipe() {
	   return recipe;
   }
   
   public void setRecipe(Recipe recipe) {
	   this.recipe = recipe;
   }

   @Override
   public String toString() {
      return "Instruction: " + this.instruction_id + ", " + this.name + ", " + this.description;
   }
}