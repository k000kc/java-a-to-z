package ru.apetrov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.Settings;
import ru.apetrov.model.User;
import ru.apetrov.storage.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andrey on 05.02.2018.
 */
public class UsersServlet extends HttpServlet {

    private UserStore userStore;
    private static final Logger log = LoggerFactory.getLogger(Settings.class);

    @Override
    public void init() throws ServletException {
        this.userStore = UserStore.getInstance();
        this.userStore.initConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (User user : this.userStore.getAll()) {
            writer.append(user.toString());
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void destroy() {
        try {
            this.userStore.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
