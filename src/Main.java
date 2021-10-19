import java.io.*;
import java.util.Calendar;
public class Main {

	public static void main(String[] args) {
		
		BufferedReader br = null;
		String file_name = "src/data.csv";
		Caliculator caliculator = new Caliculator();
		int readingLine = 1;
		
//		基準日を入力(yyyy/MM/dd)
		String strDesignatedDay = "2020/01/01";
		
		
		try {
			File file = new File(file_name);
			br = new BufferedReader(new FileReader(file));
			
			String line;
			String[] data;
			line = br.readLine();
			caliculator.PrintInFile("No," + "従業員名," + "有給取得可能数,");
			
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				readingLine += 1;
				

				Calendar calData = caliculator.ParseStrToCalendar(data[2]);
				Calendar calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
				
				calData.add(Calendar.MONTH, 6);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "10日");
					continue;
				}
				calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "11日");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "12日");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "14日");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "16日");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "18日");
					continue;
				}
				if (calData.compareTo(calDesignatedDay) < 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "20日");
					continue;
				}
				
				System.out.println(readingLine + "行のデータに異常があります");
		
				
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				br.close();
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

}
