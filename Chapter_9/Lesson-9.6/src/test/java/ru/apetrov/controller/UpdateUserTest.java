package ru.apetrov.controller;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.model.User;
import ru.apetrov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateUserTest {

    private UpdateUser updateUser;
    private UserStore store;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private boolean res;

    @Before
    public void init() throws SQLException {
        this.updateUser = new UpdateUser();
        this.store = UserStore.getInstance();
        this.store.put(new User("test2", "test2", "test2", "test2@test", new Timestamp(System.currentTimeMillis()), "user"));

        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.res = false;
    }

    @Test
    public void updateUser() throws SQLException, ServletException, IOException {
        when(this.request.getParameter("login")).thenReturn("test2");
        when(this.request.getParameter("password")).thenReturn("test2-update");
        when(this.request.getParameter("name")).thenReturn("test2-update");
        when(this.request.getParameter("email")).thenReturn("test2@tes-updatet");
        when(this.request.getParameter("role")).thenReturn("admin");

        this.updateUser.doPost(this.request, this.response);

        for (User user : this.store.getAll()) {
            if (user.getName().equals("test2-update")) {
                this.res = true;
            }
        }
        assertThat(this.res, is(true));
    }

}