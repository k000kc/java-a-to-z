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

public class DeleteUserTest {

    private UpdateUser updateUser;
    private UserStore store;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private User user;

    @Before
    public void init() throws SQLException {
        this.updateUser = new UpdateUser();
        this.store = UserStore.getInstance();

        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
    }

    @Test
    public void checkPutUser() throws SQLException {
        this.user = new User("test3", "test3", "test3", "test3@test", new Timestamp(System.currentTimeMillis()), "user");
        this.store.put(user);
        boolean res = false;
        for (User user : this.store.getAll()) {
            if (user.getLogin().equals("test3")) {
                res = true;
            }
        }
        assertThat(res, is(true));
    }

    @Test
    public void deleteUser() throws ServletException, IOException {
        when(this.request.getParameter("login")).thenReturn("test3");

        this.updateUser.doPost(this.request, this.response);

        assertNull(this.user);
    }

}