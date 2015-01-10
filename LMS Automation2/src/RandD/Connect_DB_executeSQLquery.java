//Java Connect DB and Execute SQL query


package RandD;  

import java.sql.*;
import javax.swing.JOptionPane;


public class Connect_DB_executeSQLquery
{
	public static void main(String[] args) {
		String url = "jdbc:mysql://50.116.0.186:3306/";
		String dbName = "prosoft";
		String driverJDBC = "com.mysql.jdbc.Driver";
		int Mat_id = 0 ;
		
/*				//Get Username from User while runtime.
		System.out.println("Pls enter Database Username in input popup");
		String usrname = JOptionPane.showInputDialog("Pls enter your 'Database Username'");
		String userName = usrname;*/
		String userName = "gyandba";
		
	/*	//Get Password from User while runtime.
		System.out.println("Pls enter Database Password in input popup");
		String pass = JOptionPane.showInputDialog("Pls enter your 'Database Password'");
		String password = pass;*/
		String password = "gyandba007";
		
		System.out.println("");
		//System.out.println("USERID" + "\t" + "ROLEID" + "\t" + "USERNAME");
		
		try {
			Class.forName(driverJDBC).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT `material_id`, `material_name` FROM `materials`" +
					" WHERE `material_name`  " +
					"like '%11 KV L%'");

			//ResultSet res = st.executeQuery("SELECT `material_id`, `material_name` FROM `materials` WHERE `material_id` = 335");
				
			while (res.next()) 
			{
			 Mat_id =res.getInt("material_id");
				String materailName =res.getString("material_name");
				// String usr = res.getString("username");
				String output = materailName.replace(" ","");
				System.out.println(Mat_id + "\t" + output);
			}
			conn.close();
			System.out.println("Mat_id value: " + Mat_id);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Catch block executed");
		}
	}
}