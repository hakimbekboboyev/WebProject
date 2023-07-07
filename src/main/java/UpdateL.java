import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("updateL.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Database database = new Database();


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Users users = database.signIn(username, password);
        if (users!=null){
            resp.setIntHeader("id",users.getId());
            resp.sendRedirect("update.html");

        }else {
            writer.write("<h1>Bunday foydalanuvchi mavjud emas!!</h1>");
        }
    }
}
