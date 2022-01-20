package servlet;

import dbassets.daos.ChatDAO;
import dbassets.daos.ChatDAOImpl;
import dbassets.daos.UserDAO;
import dbassets.daos.UserDAOImpl;
import dbassets.models.Chat;
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

@WebServlet("/createchat")
public class CreateChat extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ChatDAO chatdao = null;
        Chat chat = new Chat();
        try {
            chatdao = new ChatDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String chatName=request.getParameter("chatName");
        String rules=request.getParameter("rules");
        String category=request.getParameter("category");

        chat.setChatName(chatName);
        chat.setChatAdminId((int)session.getAttribute("userId"));
        chat.setRules(rules);
        chat.setCategory(category);

        assert chatdao != null;
        if(!chatdao.checkChatName(chat)){
            chatdao.addChat(chat);
            out.print("<p class=\"lead\" style=\"text-align: center;\">Chat created!</p>");
            RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
            rd.include(request, response);

        }else{
            out.print("<p class=\"lead\" style=\"text-align: center;\">This name is already in use!</p>");
            RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
            rd.include(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
}
