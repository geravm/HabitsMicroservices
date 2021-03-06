package database;

import java.sql.*;  
import java.util.ArrayList;

// Gerardo Velasco Macías 1225061
// DB on MySQL


public class DatabaseController {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//Pruebas.
		String title1 = "EatAt2pm";
		String userID1 = "danjim2214";
		int id1 = 1;
		String difficulty1 = "Easy";
		String goodBad1 = "Good";
		int score1 = 30;
		String data1 = "NoData";
		
		String title2 = "Comprar Hoverboards";
		String userID2 = "rudylepe5";
		int id2 = 2;
		String difficulty2 = "Medium";
		String goodBad2 = "Both";
		int score2 = 40;
		String data2 = "All the Data";
		
		createTable();
		post(id1, userID1, title1, difficulty1, goodBad1, score1, data1);
		System.out.println(get(1));
		//delete(1+"");
		//System.out.println(get(1));
		//post(id, userID, title, difficulty, goodBad, score, data);
		post(id2, userID2, title2, difficulty2, goodBad2, score2, data2);
		System.out.println(get(2));
		//truncateTable();
		goodBad1 = "Both";
		update(id1, userID1, title1, difficulty1, goodBad1, score1, data1);
		System.out.println(get(1));


	}
	public static ArrayList<String> get(int id) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT id, userID,title, difficulty,goodBad, score, data FROM theHabits WHERE id = '"+id+"';");
			ResultSet result = statement.executeQuery();
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()){
				array.add(result.getString("id"));
				array.add(result.getString("userID"));
				array.add(result.getString("title"));
				array.add(result.getString("difficulty"));
				array.add(result.getString("goodBad"));
				array.add(result.getString("score"));
				array.add(result.getString("data"));
			}
			return array;
			} catch (Exception e){
			System.out.println(e);
		} finally{
			System.out.println("Gotten");
		}
		return null;
	}
	
	public static void delete(int id) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("DELETE FROM theHabits WHERE id = '"+id+"';");
			posted.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}finally{
			System.out.println("Deleted");
		}
	}
	
	public static void post(int id, String userID, String title, String difficulty, String goodBad, int score,  String data) throws Exception{

		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("insert into " + "theHabits(id, userID, title, difficulty, goodBad, score, data) "+ "VALUES ('"+id+"','"+userID+"', '"+title+"', '"+difficulty+"', '"+goodBad+"', '"+score+"', '"+data+"')");
			posted.executeUpdate();
		}catch (Exception e){
			System.out.println(e);
		}finally{
			System.out.println("Posted");
		}
	}
	
	public static void update(int id, String userID, String title, String difficulty, String goodBad, int score,  String data) throws Exception{

		try {	
			delete(id);
			
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("insert into " + "theHabits(id, userID, title, difficulty, goodBad, score, data) "+ "VALUES ('"+id+"','"+userID+"', '"+title+"', '"+difficulty+"', '"+goodBad+"', '"+score+"', '"+data+"')");
			posted.executeUpdate();
		}catch (Exception e){
			System.out.println(e);
		}finally{
			System.out.println("Updated");
		}
	}
	
	public static void truncateTable() throws Exception{
		Connection con = getConnection();
		PreparedStatement truncate = con.prepareStatement("TRUNCATE theHabits");
		truncate.executeUpdate();
	}
	
	public static void createTable() throws Exception{
		
		try{
			
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS theHabits(theID int NOT NULL AUTO_INCREMENT, id varchar(255), title varchar(255), difficulty varchar(255), goodBad varchar(255), score int, data varchar(255), PRIMARY KEY(theID))");
			create.executeUpdate();
			
		} catch(Exception e){
			
			System.out.println(e);
		} finally{
			System.out.println("Table created.");
		}
	}
	
	public static Connection getConnection() throws Exception{
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/habits?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; //aquí se pondía una IP del servidor al que me conecté
			String username = "root";
			String password = "password";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			return con;
		} catch(Exception e){
			System.out.println(e);
		}
		
		return null;
	}
}
