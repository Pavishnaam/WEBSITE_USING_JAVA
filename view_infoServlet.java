/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
public class view_infoServlet extends HttpServlet {

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
// out.println("Inside Servlet....\n");
final String JDBC_DRIVER="com.mysql.jdbc.Driver";
final String DB_URL="jdbc:mysql://localhost:3306/hms?useTimeBehavior=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
final String USER="root";
final String PASS="987654321";
//String name,id,blood,age;
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
System.out.println("DB connected....");
response.setContentType("text/html");
PrintWriter out = response.getWriter();
//Statement stmt=conn.createStatement();
String name ="",id="",blood="",age ="";
String p_name = request.getParameter("p_name");

PreparedStatement statement = conn.prepareStatement("SELECT * FROM patient_details WHERE p_name=?");
statement.setString(1, p_name);
ResultSet rs = statement.executeQuery();
while(rs.next()){
name = rs.getString("p_name");
id = rs.getString("p_id");
blood = rs.getString("p_blood");
age = rs.getString("p_age");
   }

out.println("<html><h1>Details of "+name+"</h1><br/><br/>"
        +"Id = "+id+"<br/><br/>Blood_Group = "+blood+"<br/><br/>age = "+age+"<br/><br/><h2><a href=\"hms.html\">Back to Home Page</a></h2>"+ "</html>");
} catch (IOException  |SQLException | ClassNotFoundException ex)
{
Logger.getLogger(view_infoServlet.class.getName()).log(Level.SEVERE, null, ex);
}
}
}
