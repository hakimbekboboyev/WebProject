import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("signUp.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = new Database();
        PrintWriter writer = resp.getWriter();
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String aPassword = req.getParameter("aPassword");



        if (firstname!=null && lastname!=null && username!=null && password!=null && aPassword!=null){
            if(database.isUsername(username)){
                System.out.println(password+" "+aPassword);
                if(password.equals(aPassword)){
                    Users users = new Users(firstname,lastname,username,password);
                    database.signUp(users);
                    writer.write("<h1>Muvaffaqqiyatli qo'shildi</h1>");
                    writer.write("<a href='/menu'>back</a>");
                }
                else {
                    writer.write("<h1>Parollar mos kelmayapti qaytadan kiriting!</h1>");
                    writer.write("<a href='/signUp'>back</a>");
                }
            }
            else{
                writer.write("<h1>Username Band!!!</h1>");
                writer.write("<a href='/menu'>back</a>");
            }
        }
    }
}
