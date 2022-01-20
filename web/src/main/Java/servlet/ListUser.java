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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listuser")
public class ListUser extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchedUserName = request.getParameter("searchedUserName");
        String searchedUserInterest = request.getParameter("searchedUserInterest");

        UserDAO userdao = null;
        try {
            userdao = new UserDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<User> users = new ArrayList<>();

        assert userdao != null;
        if(searchedUserName==null) {
            if(searchedUserInterest==null) {
                users.addAll(userdao.getAll());
            }
            else{
                users.addAll(userdao.getUserByInterest(searchedUserInterest));
            }
        }else{
            users.addAll(userdao.getUserByName(searchedUserName));
        }

        out.println("<table class=\"table w-50 p-3 mx-auto d-flex justify-content-center\">");
        out.println("<tr></tr><tr><th scope=\"col\">Name</th><th scope=\"col\">Interested in</th></tr>");
        for(User user : users){
            String name = user.getName();
            String interested = user.getInterested();
            out.println("<tr></tr><tr><td>" + name + "</td><td>" + interested + "</td><td></td><td></td><td></td></tr>");
        }
        out.println("</table>");

        RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
        rd.include(request, response);
    }
}
