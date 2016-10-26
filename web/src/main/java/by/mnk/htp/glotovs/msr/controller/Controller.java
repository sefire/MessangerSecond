package by.mnk.htp.glotovs.msr.controller;

import by.mnk.htp.glotovs.msr.command.ActionCommand;
import by.mnk.htp.glotovs.msr.command.factory.ActionFactory;
import by.mnk.htp.glotovs.msr.resource.ConfigurationManager;
import by.mnk.htp.glotovs.msr.resource.MessageManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Sefire on 25.10.2016.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    public DataSource dataSource = null;

    @Override
    public void init() throws ServletException {

        //using JNDI

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/Messenger");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
// определение команды, пришедшей из JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
/*
* вызов реализованного метода execute() и передача параметров
* классу-обработчику конкретной команды
*/
        page = command.execute(request);
// метод возвращает страницу ответа
// page = null; // поэксперементировать!
        if (page != null) {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);
// вызов страницы ответа на запрос
            dispatcher.forward(request, response);
        } else {
// установка страницы c cообщением об ошибке
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
