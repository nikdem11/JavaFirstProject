package pl.polsl.lab1.nikodem.chylaszek.guessLan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc.ActivityLogApp;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.model.Database;

/**
 * Servlet responsible for showing logs.
 * @author Nikodem
 * @version 1.0
 */
@WebServlet(name = "Logs", urlPatterns = {"/Logs"})
public class Logs extends HttpServlet {

    private ActivityLogApp activityLogApp = new ActivityLogApp();

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
        List<Map<String, Object>> logs = activityLogApp.loadLogs(); // Load recent logs from database.
        Database database = new Database();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Activity Logs</title>");
            out.println("<style>");
            out.println("table {");
            out.println("    border-collapse: collapse;");
            out.println("}");
            out.println("th, td {");
            out.println("    border: 1px solid black;");
            out.println("    padding: 8px;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Recent Activity Logs</h1>");
            out.println("<table>");
            out.println("<tr><th>Name</th><th>Action</th><th>Timestamp</th><th>Details</th></tr>");
            for (Map<String, Object> log : logs) {
                out.println("<tr><td>" + log.get("name") + "</td><td>" + log.get("action") + "</td><td>" + log.get("timestamp") + "</td><td>" + log.get("details") + "</td></tr>");
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
        return "Servlet that displays recent activity logs.";
    }
}