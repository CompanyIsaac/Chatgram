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

@WebServlet("/registration")
public class Registration extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        UserDAO userdao = null;
        try {
            userdao = new UserDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String interested=request.getParameter("interested");

        user.setName(name);
        user.setEmail(email);
        user.setPw(password);
        user.setInterested(interested);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        assert userdao != null;
        if(userdao.getIdByEmail(user)==-1){
            userdao.addUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("userId", userdao.getIdByEmail(user));
            out.print("<p class=\"lead\" style=\"text-align: center;\">Succesful registration!</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);

        }else{
            out.print("<p class=\"lead\" style=\"text-align: center;\">This email is already in use!</p>");
            RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
            rd.include(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration.jsp");
    }
}

