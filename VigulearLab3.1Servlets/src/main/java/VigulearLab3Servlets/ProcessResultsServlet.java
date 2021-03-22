/*
Лабораторная работа 3-1. Программирование сервлетов (Servlet)
1.     Разработать страницу html для тестирования знаний или чего-либо ещё в какой-либо области. Например, география, геометрия, иностранный язык, анатомия, и т.д. Тему можно выбрать самостоятельно и прислать мне на одобрение.
Необходимо использовать все элементы формы (html-form). Используйте также элемент map (map с примером), для генерации кода можно использовать map генератор (http://htmlmapgenerator.ru/), данные от map можно хранить в hidden поле, а записывать данные в это поле можно при помощи маленькой JavaScript функции.
2.     Данные из формы отправляются сервлету для обработки. Сервлет обрабатывает данные, формирует и отправляет страницу с результатами теста клиенту (браузеру).
3.     Вопросы и ответы хранить в базе данных. Это требование не касается п1.

 */

package VigulearLab3Servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "processResultsServlet", value = "/processResults-servlet")
public class ProcessResultsServlet extends HttpServlet {
    private String message;
    static Connection connection;
    static Statement statement;
    static String query;

    public void init() {
        message = "Answers for ";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String firstName = request.getParameter("firstName");
        String familyName = request.getParameter("familyName");
        String question1 = request.getParameter("question1");
        String question2 = request.getParameter("question2");
        String question3 = request.getParameter("question3");
        String question4 = request.getParameter("question4");
        String question5 = request.getParameter("question5");
        String question6 = request.getParameter("question6");
        String[] question7 = request.getParameterValues("question7");
        String question7String = "";
        String question8 = request.getParameter("pictureId");
        String question9 = request.getParameter("question9");

        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Answers from " + firstName + " " + familyName +"</title><link rel = icon " +
                "href=\"icon.png\" type=\"image/png\"></head><body>");
        out.println("<h1>Thank you for taking this test!</h1><br/>");
        out.println("<h2>" + message + "<b>" + firstName + " " + familyName + "</b></h2>");
        out.println("1. Perpetual:<br/>" + question1 + "<br/><br/>");
        out.println("2. In a nutshell:<br/>" + question2 + "<br/><br/>");
        out.println("3. Unutterable:<br/>" + question3 + "<br/><br/>");
        out.println("4. Ambiguous:<br/>" + question4 + "<br/><br/>");
        out.println("5. Scrumptious:<br/>" + question5 + "<br/><br/>");
        out.println("6. Ample:<br/>" + question6 + "<br/><br/>");
        if (question7 != null) {
            out.println("7. Preposterous:<br/>" + "<br/>");
            for (String answer : question7) {
                out.println(answer + "<br/>");
                question7String = question7String.concat(answer).concat(" ");
            }
        } else out.println("7. Preposterous:<br/>No Answer" + "<br/>");
        out.println("<br/>");
        out.println("8. Picked in map: " + question8 + "<br/><br/>");
        out.println("9. Have a bone to pick:<br/>" + question9 + "<br/><br/>");

        // Connecting to DataBase
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/englishtest", "root", "root");
            statement = connection.createStatement();
            query = "insert into answers(firstName, familyName, Perpetual, InANutshell, Unutterable, Ambiguous, Scrumptuous, Ample, Preposterous, PickedInMap, HaveABoneToPick) " +
                    "values('" + firstName + "', '" + familyName + "', '" + question1 + "', '" + question2 +"', '" + question3 +"', '" + question4 + "', " +
                    "'" + question5 + "', '" + question6 + "', '" + question7String + "', '" + question8 + "', '" + question9 + "')";
            int flag = statement.executeUpdate(query);
            if (flag == 1)
                out.println("<b>Your answers were successfully saved on server!</b>");
            else
                out.println("Error saving answers on server!");
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }

        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void destroy() {
    }

}