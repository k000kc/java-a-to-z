package ru.apetrov.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ShowUserTest {

    @Test
    public void whenTheDoGetMethodIsExecutedThenReturnPage() throws ServletException, IOException {
        ShowUser showUser = new ShowUser();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/ShowUsers.jsp")).thenReturn(dispatcher);

        showUser.doGet(request, response);

        verify(request).getSession();
        verify(request).getRequestDispatcher("/WEB-INF/views/ShowUsers.jsp");
        verify(dispatcher).forward(request, response);
    }

}