package ru.apetrov.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SigninControllerTest {

    @Test
    public void whenInputLoginAndPasswordCorrectThenGetShowUserPage() throws IOException, ServletException {
        SigninController signinController = new SigninController();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("root");
        when(request.getParameter("password")).thenReturn("root");

        signinController.doPost(request, response);

        verify(response).sendRedirect(String.format("%s/", request.getContextPath()));
    }

    @Test
    public void whenInputErrorLoginAndPasswordThenGetErrorMessageAndRedirectLoginPage() throws ServletException, IOException {
        SigninController signinController = new SigninController();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("login")).thenReturn("roo");
        when(request.getParameter("password")).thenReturn("roo");
        when(request.getRequestDispatcher("/WEB-INF/views/Login.jsp")).thenReturn(dispatcher);

        signinController.doPost(request, response);

        verify(request).setAttribute("error", "Credentional invalid");
        verify(request).getRequestDispatcher("/WEB-INF/views/Login.jsp");
    }
}