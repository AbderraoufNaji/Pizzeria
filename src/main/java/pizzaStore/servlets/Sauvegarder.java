package  pizzaStore.servlets;

import  pizzaStore.beans.Commande;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

@WebServlet("/Sauvegarder")
public class Sauvegarder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();

        List<Commande> commandes = (List<Commande>) context.getAttribute("commandes");

        if (commandes != null && !commandes.isEmpty()) {
            // Perform database storage logic here
            // For example, you can use JDBC to connect to a database and save the commandes

            commandes.clear();
            context.setAttribute("commandes", commandes);
        }

        response.sendRedirect("AdminInterface.html");
    }
}
