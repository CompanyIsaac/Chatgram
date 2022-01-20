package servlet;

import dbassets.daos.UserDAO;
import dbassets.daos.UserDAOImpl;
import dbassets.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO userdao = null;
        User user = new User();
        try {
            userdao = new UserDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email=request.getParameter("email");
        String password=request.getParameter("password");

        user.setEmail(email);
        user.setPw(password);

        assert userdao != null;
        if(userdao.checkUser(user)){
            HttpSession session = request.getSession();
            session.setAttribute("userId", userdao.getIdByEmail(user));
            session.setAttribute("userName", userdao.getIdByEmail(user));
            RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
            rd.forward(request, response);

        }else{
            out.print("<p class=\"lead\" style=\"text-align: center;\">Invalid username or password!</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);

        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
}
