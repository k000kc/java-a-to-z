package ru.apetrov.controller;

import org.junit.Test;
import ru.apetrov.model.User;
import ru.apetrov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddUserTest {

    @Test
    public void addUser() throws IOException, SQLException {
        AddUser addUser = new AddUser();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("email")).thenReturn("test@test");
        when(request.getParameter("role")).thenReturn("admin");

        addUser.doPost(request, response);

        UserStore store = UserStore.getInstance();
        List<User> list = store.getAll();

        assertThat(list.get(0).getLogin(), is("test"));
    }
}