/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class doctorupdateServlet_cardio extends HttpServlet {

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
String id = request.getParameter("id");
String name = request.getParameter("name");
String email = request.getParameter("email");
String number = request.getParameter("number");
String gender = request.getParameter("gender");
String blood= request.getParameter("blood");
stmt.executeUpdate("update doctor_details_cardio set name='"+name+"',email='"+email+"',number='"+number+"' ,gender='"+gender+"',blood='"+blood+"' where id='"+id+"';");
out.println("<html><h1>Value updated successfully....</h1><br/><br/>"
        +"<h2><a href=\"hms.html\">Back to Home Page</a></h2>"+ "</html>");
} catch (IOException  |SQLException | ClassNotFoundException ex)
{
Logger.getLogger(doctorupdateServlet_cardio.class.getName()).log(Level.SEVERE, null, ex);
}
}
}
