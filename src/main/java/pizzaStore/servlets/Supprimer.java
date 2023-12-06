package  pizzaStore.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.beans.Pizza;

@WebServlet("/Supprimer")
public class Supprimer extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the session
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Get the selected pizza name to be removed
            String pizzaName = request.getParameter("pizzaName");

            // Retrieve the list of pizzas from the session
            List<Pizza> pizzas = (List<Pizza>) session.getAttribute("pizzas");

            if (pizzas != null) {
                // Find and remove the selected pizza from the list
                Iterator<Pizza> iterator = pizzas.iterator();
                while (iterator.hasNext()) {
                    Pizza pizza = iterator.next();
                    if (pizza.getNom().equals(pizzaName)) {
                        iterator.remove();
                        break;
                    }
                }

                // Update the list of pizzas in the session
                session.setAttribute("pizzas", pizzas);
            }
        }

        // Redirect back to the menu page
        response.sendRedirect("MenuPizza.html");
    }
}
