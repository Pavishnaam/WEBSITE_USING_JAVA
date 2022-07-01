import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginServlet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
// out.println("Inside Servlet....\n");
final String JDBC_DRIVER="com.mysql.jdbc.Driver";
final String DB_URL="jdbc:mysql://localhost:3306/hms?useTimeBehavior=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
final String USER="root";
final String PASS="987654321";
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
System.out.println("DB connected....");
response.setContentType("text/html");
PrintWriter out = response.getWriter();
Statement stmt=conn.createStatement();
String title;
String username = request.getParameter("username");
String password = request.getParameter("password");
ResultSet rs = stmt.executeQuery("select * from login where username='"+username+"' and password='"+password+"';");
if(rs.next())
{
response.sendRedirect("hms.html");
//RequestDispatcher req = request.getRequestDispatcher("success.jsp");
//req.include(request, response);
  
}
else
{
    response.sendRedirect("login.html");
//RequestDispatcher req = request.getRequestDispatcher("failure.jsp");
//req.forward(request, response);

}
} catch (IOException  |SQLException | ClassNotFoundException ex)
{
Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
}
}
}