import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Caliculator {
	
	String strDate = "1970-01-01"; 
	
public Calendar ParseStrToCalendar(String strDate) {
	try {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = dateFormat.parse(strDate);
		calendar.setTime(date);
		return calendar;
		
		
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		return null;
	}
	
}

public void PrintInFile(String text) {
	try {
		FileWriter fw = new FileWriter("src/holiday.csv",true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(text);
		pw.close();
	}catch (IOException ex) {
		ex.printStackTrace();
	}
}
}
