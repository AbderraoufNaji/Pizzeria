package pizzaStore.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzaStore.beans.Pizza;


public class Ajouter extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the session or create a new one if it doesn't exist
        HttpSession session = request.getSession(true);

        // Get the selected pizza name from the request
        String pizzaName = request.getParameter("pizzaName");

        // Retrieve the list of pizzas from the session
        List<Pizza> pizzas = (List<Pizza>) session.getAttribute("pizzas");

        if (pizzas == null) {
            // If the list doesn't exist, create a new one
            pizzas = new ArrayList<>();
        }

        // Check if the selected pizza is already in the list
        Pizza selectedPizza = null;
        for (Pizza pizza : pizzas) {
            if (pizza.getNom().equals(pizzaName)) {
                selectedPizza = pizza;
                break;
            }
        }

        if (selectedPizza == null) {
            // If the pizza is not in the list, create a new Pizza object and add it
            selectedPizza = new Pizza(pizzaName, 10.0, 1);
            pizzas.add(selectedPizza);
        } else {
            // If the pizza is already in the list, increment its quantity
            selectedPizza.setQuantite(selectedPizza.getQuantite() + 1);
        }

        // Update the list of pizzas in the session
        session.setAttribute("pizzas", pizzas);

        // Redirect back to the menu page
        response.sendRedirect("MenuPizza.html");
    }
}
