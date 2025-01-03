//TASK-8

package com.itsc.OnlineBookstore;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp);
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null) {
            path = req.getServletPath(); // Use servlet path as a fallback
        }

        switch (path) {
            case "/registerBook":
                new BookRegistrationServlet().doPost(req, resp);
                break;
            case "/displayBooks":
                new DisplayBooksServlet().doGet(req, resp);
                break;
            case "/deleteBook":
                new DeleteBookServlet().doPost(req, resp);
                break;
            case "/searchBook":
                new SearchBooksServlet().doGet(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        }
    }
}
