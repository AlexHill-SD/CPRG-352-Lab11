/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author BritishWaldo
 */
public class PasswordResetServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getParameter("resetUUID") == null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
            return;
        }
        else
        {
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getParameter("resetEmail") != null)
        {
            String baseURL = request.getRequestURL().toString();
            String resetEmail = request.getParameter("resetEmail");
            String webINFPath = getServletContext().getRealPath("/WEB-INF");
            
            new AccountService().sendPasswordResetEmail(resetEmail, webINFPath, baseURL);
            
            request.setAttribute("message", "If the e-mail entered was valid please wait a few moments for password reset e-mail."
                                                + "<br>If you don't see it check the junk folder of your e-mail account.");
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        else if (request.getParameter("resetUUID") != null)
        {
            String resetUUID = request.getParameter("resetUUID");
            String resetPassword = request.getParameter("resetPassword");
            
            boolean resetSuccess = new AccountService().resetPassword(resetUUID, resetPassword);
            
            if (resetSuccess)
            {
                request.setAttribute("message", "Password successfully changed, please log in with your username and new password.");
            
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            else
            {
                request.setAttribute("message", "No corresponding account found, please don't try to bypass the system ;)");
            
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
        }
        else
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }
}
