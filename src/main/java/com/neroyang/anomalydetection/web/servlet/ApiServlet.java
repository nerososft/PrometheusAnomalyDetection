package com.neroyang.anomalydetection.web.servlet;

import com.neroyang.anomalydetection.web.core.ApiHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/17
 * Time   下午11:07
 */
public class ApiServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;

    ApplicationContext applicationContext;
    private ApiHandler apiHand;

    @Override
    public void init() throws ServletException {
        super.init();
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        apiHand = applicationContext.getBean(ApiHandler.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiHand.handle(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiHand.handle(req, resp);
    }
}
