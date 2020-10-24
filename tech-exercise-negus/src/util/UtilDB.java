/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import datamodel.Recipe;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   // Search Recipe Names
   public static List<Recipe> searchRecipes(String keyword) {
      List<Recipe> resultList = new ArrayList<Recipe>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         
         System.out.println((Recipe)session.get(Recipe.class, 1)); 
         List<?> recipes = session.createQuery("FROM Recipe").list();
         
         for (Iterator<?> iterator = recipes.iterator(); iterator.hasNext();) {
        	 Recipe recipe = (Recipe) iterator.next();
            if (recipe.getName().toLowerCase().contains(keyword) || recipe.getName().contains(keyword)) {
               resultList.add(recipe);
            }
         }
         tx.commit();
         
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      
      return resultList;
   }
   
   //Search Recipe Ingredients
   public static List<Recipe> searchIngredients(String keyword) {
	      List<Recipe> resultList = new ArrayList<Recipe>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         
	         System.out.println((Recipe)session.get(Recipe.class, 1)); 
	         List<?> recipes = session.createQuery("FROM Recipe").list();
	         
	         for (Iterator<?> iterator = recipes.iterator(); iterator.hasNext();) {
	        	 Recipe recipe = (Recipe) iterator.next();
	            if (recipe.getIngredients().toLowerCase().contains(keyword) || recipe.getIngredients().contains(keyword)) {
	               resultList.add(recipe);
	            }
	         }
	         tx.commit();
	         
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      
	      return resultList;
	   }

   //Create Recipes
   public static void createRecipes(String name, String description, String ingredients, String instructions) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Recipe(name, description, ingredients, instructions));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
