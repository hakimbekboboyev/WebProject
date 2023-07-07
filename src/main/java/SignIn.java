import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("SignIn.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Database database = new Database();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Users users = database.signIn(username, password);

        if(users!=null && users.isAdmin()){
            writer.write("<h1>Assalomu aleykum "+users.getFirstname()+" admin paneliga xush kelibsiz</h1>");

            List<Users> list = database.listUser();
            //writer.write("<h1>" + users1.toString() + "</h1>");

            String ruyxat="<table border=1>\n"+
                    "<tr>\n"+
                    "<th>Id</th>\n"+
                    "<th>Firstname</th>\n"+
                    "<th>Lastname</th>\n"+
                    "<th>Username</th>\n"+
                    "<th>Password</th>\n"+
                    "<th>isAdmin</th>\n"+
                    "<th>Delete</th>\n"+
                    "</tr>\n";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isActive()) {
                    ruyxat += " " +
                            "<tr>\n" +
                            "<td>" + list.get(i).getId() + "</td>\n" +
                            "<td>" + list.get(i).getFirstname() + "</td>\n" +
                            "<td>" + list.get(i).getLastname() + "</td>\n" +
                            "<td>" + list.get(i).getUsername() + "</td>\n" +
                            "<td>" + list.get(i).getPassword() + "</td>\n" +
                            "<td>" + "<input type=\"checkbox\" checked/>" + "</td>\n" +
                            "<td>" +
                            "<button type=\"submit\"> Delete</button>"+ "</td>\n" +
                            "</tr>\n";
                }
            }
            ruyxat+=" </table>";
            writer.write(ruyxat);

        }if(users!=null && users.isAdmin()==false){
            writer.write("<h1>Assalomu aleykum "+users.getFirstname()+" xush kelibsiz</h1>");
        }
        if(users == null){
            writer.write("<h1>Bunday foydalanuvchi mavjud emas!</h1>");
        }
    }
}
