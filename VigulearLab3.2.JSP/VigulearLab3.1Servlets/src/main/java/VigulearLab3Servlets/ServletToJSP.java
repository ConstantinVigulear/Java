/*
Лабораторная работа 3.2. Программирование Java Server Pages (JSP)
1.     Разработать страницу html для тестирования знаний или чего-либо ещё в какой-либо области. Например, география, геометрия, иностранный язык, анатомия, и т.д. Тему можно выбрать самостоятельно и прислать мне на одобрение.
Необходимо использовать все элементы формы (html-form). Используйте также элемент map (map с примером), для генерации кода можно использовать map генератор (http://htmlmapgenerator.ru/), данные от map можно хранить в hidden поле, а записывать данные в это поле можно при помощи маленькой JavaScript функции.
2.     Данные из формы отправляются файлу JSP. JSP обрабатывает данные, формирует и отправляет страницу с результатами теста клиенту (браузеру).
3.     Вопросы и ответы хранить в базе данных.
 */

package VigulearLab3Servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "servletToJsp", value = "/servlet-to-jsp")
public class ServletToJSP extends HttpServlet {
    public ServletToJSP() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/ProcessResultsJSP.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    public void destroy() {
    }
}
