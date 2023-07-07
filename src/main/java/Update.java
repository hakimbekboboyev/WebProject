import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Update extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("update.html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Database database = new Database();
        Users users = new Users();

        String id = req.getHeader("id");
        System.out.println(id);
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String aPassword = req.getParameter("aPassword");

        if(password.equals(aPassword)){
            users = new Users(firstname,lastname,username,password);
            int i = database.updateUser(users);
            if(i==0){
                writer.write("<h1>Ma'lumotlar yangilanmadi iltimos qaytadan urinib ko'ring!</h1>");
            }else{
                writer.write("<h1>Ma'lumotlar muvaffaqqiyatli yangilandi!</h1>");
            }
        }
    }

    public void doGet(String username) {
    }
}
