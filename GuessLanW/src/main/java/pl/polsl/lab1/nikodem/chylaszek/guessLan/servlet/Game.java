package pl.polsl.lab1.nikodem.chylaszek.guessLan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc.CreateTablesApp;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc.InsertDataApp;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.model.Database;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.model.GameEngine;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.model.Question;

/**
 * Servlet responsible for managing the game.
 * @author Nikodem
 * @version 1.1
 */
@WebServlet(name = "Game", urlPatterns = {"/Game"})
public class Game extends HttpServlet {

    private Database database;

    /**
     *
     */
    @Override
    public void init() {
        database = new Database();
        
        // Creating tables in DB.
        CreateTablesApp createTablesApp = new CreateTablesApp();
        createTablesApp.createTables();
        
        // Inserting questions data into DB.
        InsertDataApp insertDataApp = new InsertDataApp();
        insertDataApp.insertData();

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String lastPlayerName = null;

        try {
            GameEngine gameEngine = (GameEngine) session.getAttribute("gameEngine");
            String playerName = (String) session.getAttribute("playerName");
            String action = request.getParameter("action");

            
            if (gameEngine == null || "newPlayer".equals(action)) {
                // Starting a new game for a new player.
                playerName = request.getParameter("name");
                if (playerName != null && !playerName.isEmpty()) {
                    session.setAttribute("playerName", playerName);
                    gameEngine = new GameEngine(database);
                    session.setAttribute("gameEngine", gameEngine);
                    gameEngine.startNewGame(playerName);
                }
            }

            // Processing player answers and hint requests.
            String userAnswer = request.getParameter("answer");
            String hintRequest = request.getParameter("hint");
            boolean newQuestionNeeded = true;

            if ("true".equals(hintRequest) && gameEngine.isGameStarted()) {
                // Using a hint for the current question.
                gameEngine.useHint();
                database.saveLog(gameEngine.getPlayer(), "Used Hint", "Hint used for question: " + gameEngine.getCurrentQuestion().getText());
                newQuestionNeeded = false;
            } else if (userAnswer != null && !userAnswer.isEmpty()) {
                // Checking the player's answer.
                boolean isCorrect = gameEngine.checkAnswer(userAnswer);
                String resultMessage = isCorrect ? "Correct!" : "Incorrect!";
                session.setAttribute("lastResult", resultMessage);
                database.saveLog(gameEngine.getPlayer(), "Answered Question", "Question: " + gameEngine.getCurrentQuestion().getText() + ", Answer: " + userAnswer + ", Result: " + resultMessage);
                newQuestionNeeded = isCorrect;
            }

            // Loading the next question if needed.
            if (newQuestionNeeded && gameEngine.isGameStarted() && gameEngine.getPlayer().getLives() > 0) {
                gameEngine.getNextQuestion();
            }

            // Generating the game view for the player.
            Question currentQuestion = gameEngine.getCurrentQuestion();

            // Managing cookies for player's last used name.
            
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("lastPlayerName".equals(cookie.getName())) {
                        lastPlayerName = cookie.getValue();
                        break;
                    }
                }
            }

            if (playerName != null && !playerName.equals(lastPlayerName)) {
                Cookie newCookie = new Cookie("lastPlayerName", playerName);
                newCookie.setMaxAge(60 * 60 * 24 * 30); // Keep for 30 days.
                response.addCookie(newCookie);
            }

            try (PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<body>");
                
                out.println("<h1>Welcome " + (playerName != null ? playerName : "") + "</h1>");

                if (gameEngine.isGameStarted() && gameEngine.getPlayer().getLives() > 0) {
                    if (currentQuestion != null) {
                        out.println("<p>Question: " + currentQuestion.getText() + "</p>");
                        out.println("<form action='Game' method='POST'>");
                        out.println("<input type='text' name='answer'>");
                        out.println("<input type='submit' value='Submit Answer'>");
                        if (!gameEngine.isHintUsed()) {
                            out.println("<button name='hint' value='true' type='submit'>Use Hint</button>");
                        } else {
                            out.println("<p>Hint: " + currentQuestion.getHint() + "</p>");
                        }
                        out.println("</form>");
                    }

                    out.println("<p>Score: " + gameEngine.getPlayer().getScore() + "</p>");
                    out.println("<p>Lives: " + gameEngine.getPlayer().getLives() + "</p>");
                }

                if (session.getAttribute("lastResult") != null && gameEngine.getPlayer().getLives() > 0) {
                    out.println("<p>" + session.getAttribute("lastResult") + "</p>");
                }

                if (gameEngine.isGameStarted() && gameEngine.getPlayer().getLives() == 0) {
                    out.println("<p>Game over. Your score: " + gameEngine.getPlayer().getScore() + "</p>");
                    out.println("<form action='index.html' method='get'>");
                    out.println("<button type='submit'>Return to Main Menu</button>");
                    out.println("</form>");
                    database.saveResult(gameEngine.getPlayer());
                    session.removeAttribute("gameEngine"); // Remove the gameEngine from session.
                    session.removeAttribute("playerName"); // Remove the playerName from session.
                }

                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Game Servlet for Guess the Language Game";
    }
}