package habitComponents;

import database.DatabaseController;

// Gerardo Velasco Macías 1225061

public class Scoring {
	private DatabaseController databaseController = new DatabaseController();
	String color = "Orange";
	int idGenerator = 1;
	
	public static void main(String [ ] args){
		
	}
	
	public void createHabit(String userID, String title, String difficulty, String goodBad, int score, String data){
		try {
			databaseController.post(idGenerator, userID, title, difficulty, goodBad, score, data);
			idGenerator++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editHabit(int habitID, String userID, String title, String difficulty, String goodBad, int score, String data){
		try {
			databaseController.update(habitID, userID, title, difficulty, goodBad, score, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteHabit(int habitID){
		try {
			databaseController.delete(habitID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showHabitsForUser(String userID){
		//Missing this query
	}
	
	public String score(int habitID, String buttonPressed){
		color = "Orange";
		// necesito una clase por cada 1 de los difficulties y colores (8)
		
		return color;
	}
}
