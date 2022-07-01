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
public class roomServlet extends HttpServlet {

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
String room_type = request.getParameter("room_type");
String bed_type = request.getParameter("bed_type");
String food_type = request.getParameter("food_type");
int room_pay =0;
if(room_type.equals("ac"))
{
    room_pay+=500;
}
else{
    room_pay+=200;
}
if(bed_type.equals("2"))
{
    room_pay+=200;
}
else if(bed_type.equals("3")){
    room_pay+=100;
}
else{
    room_pay+=50;
}
if(food_type.equals("normal"))
{
    room_pay+=100;
}
else{
    room_pay+=200;
}
stmt.executeUpdate("insert into room values('"+p_id+"' ,'"+room_type+"','"+bed_type+"','"+food_type+"',"+room_pay+");");
out.println("<html><h1>Value added successfully....</h1><br/><br/>"+"<h1>ROOM_PAYMENT = "+room_pay
        +"</h1><h2><a href=\"hms.html\">Back to Home Page</a></h2>"+ "</html>");
} catch (IOException  |SQLException | ClassNotFoundException ex)
{
Logger.getLogger(roomServlet.class.getName()).log(Level.SEVERE, null, ex);
}
}
}
