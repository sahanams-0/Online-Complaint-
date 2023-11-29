package complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class complaintform extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public complaintform() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Dateofcomplaint=request.getParameter("dcom");
		String name=request.getParameter("fname");
		String Email=request.getParameter("email");
		String Address=request.getParameter("addss");
		String DateofreportedIncident=request.getParameter("dri");
		String Typeofcomplaint=request.getParameter("tc");
		String Incidentlocation=request.getParameter("il");
		String Complaintdetails=request.getParameter("cd");
		PrintWriter pw=response.getWriter();
		pw.println(Dateofcomplaint+"\n"+name+"\n"+Email+"\n"+Address+"\n"+DateofreportedIncident+"\n"+Typeofcomplaint+"\n"+Incidentlocation+"\n"+Complaintdetails+"\n"+"\n\n");
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/complaintrecords","root","Y1012Jqkhkp");
			pw.println("Complaint Registered Successfully");
			String insert="insert into info(dateofcomplaint,fname,email,address,dateofreport,typeofcomplaint,incidentloca,complaintdetail) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pre=con.prepareStatement(insert);
			pre.setString(1, Dateofcomplaint);
			pre.setString(2, name);
			pre.setString(3, Email);
			pre.setString(4, Address);
			pre.setString(5, DateofreportedIncident);
			pre.setString(6, Typeofcomplaint);
			pre.setString(7, Incidentlocation);
			pre.setString(8, Complaintdetails);
			pre.executeUpdate();
			pw.println("Thank You");
		}
		catch(SQLException e) {
			pw.println(e);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
