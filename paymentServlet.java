/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PAVISHNA
 */
public class paymentServlet extends HttpServlet {
    private PreparedStatement statement;

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
String p_name = request.getParameter("p_name");
int total_pay =0;int room_pay=0;int scan_pay=0;
//stmt.executeUpdate("select * from scan join room join patient_details where p_name='"+p_name+"';");
//if(rs.next()){
//       out.println("<h3>"+rs.getString("p_name")+"   -  "+rs.getInt("scan_pay") +"   -  "+rs.getInt("room_pay") + "</h3><br>");
//}
statement = conn.prepareStatement("SELECT * FROM scan join room join patient_details WHERE p_name=?");
statement.setString(1, p_name);
ResultSet rs = statement.executeQuery();
while(rs.next()){
p_name = rs.getString("p_name");
room_pay = rs.getInt("room_pay");
scan_pay = rs.getInt("scan_pay");
        }
total_pay = room_pay + scan_pay;
out.println("<html><h1></h1><br/><br/>"+"<h1>TOTAL_PAYMENT = "+total_pay
        +"</h1><h2><a href=\"hms.html\">Back to Home Page</a></h2>"+ "</html>");
} catch (IOException  |SQLException | ClassNotFoundException ex)
{
Logger.getLogger(paymentServlet.class.getName()).log(Level.SEVERE, null, ex);
}
}
}
