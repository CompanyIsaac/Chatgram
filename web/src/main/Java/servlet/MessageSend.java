package servlet;

import dbassets.daos.*;
import dbassets.models.Message;
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

@WebServlet("/messagesend")
public class MessageSend extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MessageDAO messagedao = null;
        Message message = new Message();
        try {
            messagedao = new MessageDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UserDAO userdao = null;
        User user;
        try {
            userdao = new UserDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        assert userdao != null;
        user = userdao.getUserById((int)session.getAttribute("userId"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        message.setText(request.getParameter("message"));
        message.setUserId(user.getId());
        message.setUserName(user.getName());
        message.setChatId((int)session.getAttribute("chatId"));

        assert messagedao != null;
        messagedao.addMessage(message);

        RequestDispatcher rd=request.getRequestDispatcher("listmessage");
        rd.forward(request, response);
    }
}