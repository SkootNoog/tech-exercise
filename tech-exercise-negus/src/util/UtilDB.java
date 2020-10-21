/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.Recipe;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
   
   //Recipe
   public static List<Recipe> listRecipes() {
	      List<Recipe> resultList = new ArrayList<Recipe>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

	      try {
	         tx = session.beginTransaction();
	         List<?> recipes = session.createQuery("FROM Recipe").list();
	         for (Iterator<?> iterator = recipes.iterator(); iterator.hasNext();) {
	        	 Recipe recipe = (Recipe) iterator.next();
	            resultList.add(recipe);
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

	   public static List<Recipe> listRecipes(String keyword) {
	      List<Recipe> resultList = new ArrayList<Recipe>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         System.out.println((Recipe)session.get(Recipe.class, 1)); // use "get" to fetch data
	        // Query q = session.createQuery("FROM Recipe");
	         List<?> recipes = session.createQuery("FROM Recipe").list();
	         for (Iterator<?> iterator = recipes.iterator(); iterator.hasNext();) {
	        	 Recipe recipe = (Recipe) iterator.next();
	            if (recipe.getName().startsWith(keyword)) {
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
