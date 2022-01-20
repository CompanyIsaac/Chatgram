package servlet;

import dbassets.daos.MessageDAO;
import dbassets.daos.MessageDAOImpl;
import dbassets.daos.ChatDAO;
import dbassets.daos.ChatDAOImpl;
import dbassets.models.Message;
import dbassets.models.Chat;

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

@WebServlet("/listmessage")
public class ListMessage extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if(request.getParameter("chatId")!=null) {
            session.setAttribute("chatId", Integer.parseInt(request.getParameter("chatId")));
        }
        int chatId = (int)session.getAttribute("chatId");

        MessageDAO messagedao = null;
        try {
            messagedao = new MessageDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert messagedao != null;
        List<Message> messages = new ArrayList<>(messagedao.getMessageByChat(chatId));

        out.println("<table class=\"table w-50 p-3 mx-auto\">");
        out.println("<tr><th>Message</th><th>Sender</th></tr>");
        for(Message message : messages){
            String name = message.getText();
            String sender = message.getUserName();
            out.println("<tr><td>" + name + "</td><td>" + sender + "</td></tr>");
        }
        out.println("</table>");

        RequestDispatcher rd = request.getRequestDispatcher("chat.jsp");
        rd.include(request, response);
    }
}