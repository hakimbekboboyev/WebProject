import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("delete.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Database database = new Database();
        Users users = new Users();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean b = database.deleteUsername(username, password);
        if(b){
            writer.write("<h1>Muvaffaqqiyatli o'chirildi!!!</h1>");
        }else{
            writer.write("<h1>Bunday foydalanuvchi mavjud emas!!!</h1>");
        }


    }
}
