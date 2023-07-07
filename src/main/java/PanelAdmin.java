import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PanelAdmin extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Users users = new Users();
        /*Database database = new Database();*/
        writer.write("<h1>Assalomu aleykum "+users.getFirstname()+" Admin panelga xush kelibsiz</h1>");

        /*List<Users> users1 = database.listUser();
        System.out.println(users1.toString());
        writer.write("<h1>users1.toString()</h1>");*/
    }
}
