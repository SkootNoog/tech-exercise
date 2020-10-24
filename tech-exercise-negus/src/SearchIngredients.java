import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Recipe;
import util.UtilDB;

@WebServlet("/SearchIngredients")
public class SearchIngredients extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SearchIngredients() {
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
      
      List<Recipe> recipes = UtilDB.searchIngredients(keyword);
      
      for(Recipe recipe : recipes) {
    	  out.println("<h2 style=\"text-align:center;\">" + recipe.getName()+ "</h2><br>");
    	  out.println("<b>Recipe Description: </b>" + recipe.getDesc()+ "<br>");
    	  out.println("<b>Recipe Ingredients: </b>" + recipe.getIngredients()+ "<br>");
    	  out.println("<b>Recipe Instructions: </b>" + recipe.getInstructions()+ "<br>");
      }
      
      out.println("<a href=/Cookbook/SearchRecipes.html>Search Recipes</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
