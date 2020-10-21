import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Recipe;
import util.UtilDB;

@WebServlet("/SearchRecipes")
public class SearchRecipes extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SearchRecipes() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      search(keyword, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      
      List<Recipe> recipes = UtilDB.listRecipes(keyword);
      
      for(Recipe recipe : recipes) {
    	  out.println("Recipe Name: " + recipe.getName());
    	  out.println("Recipe Description: " + recipe.getDesc());
    	  out.println("Recipe Ingredients: " + recipe.getIngredients());
    	  out.println("Recipe Instructions: " + recipe.getInstructions()+ "<br>");
      }
      
      out.println("<a href=/CookBook/SearchRecipes.html>Search Recipes</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
