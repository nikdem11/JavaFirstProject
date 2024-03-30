package pl.polsl.lab1.nikodem.chylaszek.guessLan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc.SelectDataApp;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.model.Player;

/**
 * Servlet responsible for showing best results.
 * @author Nikodem
 * @version 1.0
 */
@WebServlet(name = "Results", urlPatterns = {"/Results"})
public class Results extends HttpServlet {

    private SelectDataApp selectDataApp = new SelectDataApp();

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Player> players = selectDataApp.loadResults(); // Load results from database.

        // Sort results and select top 10 players.
        List<Player> topPlayers = players.stream()
                                         .sorted(Comparator.comparing(Player::getScore).reversed())
                                         .limit(10)
                                         .collect(Collectors.toList());

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // HTML code to display the top 10 player results.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Results</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>TOP 10 | BEST SCORES</h1>");
            out.println("<table>");
            out.println("<tr><th><b>Score | </b></th><th><b>Nickname</b></th></tr>");
            for (Player player : topPlayers) {
                out.println("<tr><td>" + player.getScore() + "</td><td>" + player.getName() + "</td></tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Handle POST by forwarding to doGet method.
    }

    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Servlet that displays top 10 players' scores.";
    }
}
