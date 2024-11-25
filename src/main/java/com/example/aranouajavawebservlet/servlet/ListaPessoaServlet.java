package com.example.aranouajavawebservlet.servlet;

import jakarta.servlet.http.HttpServlet;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;

public class ListaPessoaServlet extends HttpServlet {
    protected void service(HttpServlet request,HttpServlet response) throws SerialException, IOException{
        response.setContentType("text/html");
        response.setCharacterEnconding("UTF-8");
        PrintWriter out = response.getWritter();
    }
}
