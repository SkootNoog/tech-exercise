
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilDB;

@WebServlet("/CreateRecipe")
public class CreateRecipe extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public CreateRecipe() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      String ingredients = request.getParameter("ingredients");
      String instructions = request.getParameter("instructions");

      UtilDB.createRecipes(name, description, ingredients, instructions);
      
      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Create Recipe";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Recipe Name</b>: " + name + "\n" + //
            "  <li><b>Recipe Description</b>: " + description + "\n" + //
            "  <li><b>Ingredients</b>: " + ingredients + "\n" + //
            "  <li><b>Instructions</b>: " + instructions + "\n" + //

            "</ul>\n");

      out.println("<a href=/Cookbook/SearchRecipe.html>Search Recipes</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
