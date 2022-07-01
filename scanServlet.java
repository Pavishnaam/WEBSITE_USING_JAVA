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
public class scanServlet extends HttpServlet {

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
String p_id = request.getParameter("p_id");
String scan_type = request.getParameter("scan_type");

int scan_pay =0;
if(scan_type.equals("MRI"))
{
    scan_pay+=1500;
}
else if (scan_type.equals("CT")){
    scan_pay+=2000;
}
else{
   scan_pay+=2400;
}
stmt.executeUpdate("insert into scan values('"+p_id+"' ,'"+scan_type+"',"+scan_pay+");");
out.println("<html><h1>Value added successfully....</h1><br/><br/>"+"<h1>SCAN_PAYMENT = "+scan_pay
        +"</h1><h2><a href=\"hms.html\">Back to Home Page</a></h2>"+ "</html>");
} catch (IOException  |SQLException | ClassNotFoundException ex)
{
Logger.getLogger(scanServlet.class.getName()).log(Level.SEVERE, null, ex);
}
}
}
