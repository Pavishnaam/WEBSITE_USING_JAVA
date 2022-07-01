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

public class changepasswordServlet extends HttpServlet
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
String username = request.getParameter("username");
String password = request.getParameter("old_password");
String new_password = request.getParameter("new_password");
stmt.executeUpdate("update login set password='"+new_password+"' where username='"+username+"';");
out.println("<html><h1>Password updated successfully....</h1><br/><br/>"
        +"<h2><a href=\"hms.html\">Back to Home Page</a></h2>"+ "</html>");
} catch (IOException  |SQLException | ClassNotFoundException ex)
{
Logger.getLogger(changepasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
}
}
}