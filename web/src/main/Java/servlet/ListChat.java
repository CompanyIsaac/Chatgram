package servlet;

import dbassets.daos.ChatDAO;
import dbassets.daos.ChatDAOImpl;
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

@WebServlet("/listchat")
public class ListChat extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchedChatName = request.getParameter("searchedChatName");
        String searchedChatCategory = request.getParameter("searchedChatCategory");

        ChatDAO chatdao = null;
        try {
            chatdao = new ChatDAOImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Chat> chats = new ArrayList<>();

        assert chatdao != null;
        if(searchedChatName==null) {
            if(searchedChatCategory==null) {
                chats.addAll(chatdao.getAll());
            }
            else{
                chats.addAll(chatdao.getChatByCategory(searchedChatCategory));
            }
        }else{
            chats.addAll(chatdao.getChatByName(searchedChatName));
        }

        out.println("<table class=\"table w-50 p-3 mx-auto\">");
        out.println("<tr><th scope=\"col\">Name</th><th scope=\"col\">Rules</th><th scope=\"col\">Category</th></tr>");
        for(Chat chat : chats){
            String name = chat.getChatName();
            String rules = chat.getRules();
            String category = chat.getCategory();
            int id = chat.getId();
            out.println("<tr><td>" + name + "</td><td>" + rules + "</td><td>" + category + "</td><td></td><td><form action=\"listmessage\" method=\"post\"><input type=\"hidden\" id=\"chatId\" name=\"chatId\" value=\""+id+"\"/><button class=\"btn btn-primary\">Join</button></form></td></tr>");
        }
        out.println("</table>");

        RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
        rd.include(request, response);
    }
}