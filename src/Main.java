import java.io.*;
import java.util.Calendar;
public class Main {

	public static void main(String[] args) {
	    BufferedReader br = null;
		String file_name = "src/data.csv";
		Caliculator caliculator = new Caliculator();
		
//		有給取得基準日を入力(yyyy/MM/dd)
		String strDesignatedDay = "2021/10/31";
		
		
		try {
			File file = new File(file_name);
			br = new BufferedReader(new FileReader(file));
			
			String line;
			String[] data;
			line = br.readLine();
			caliculator.PrintInFile("2019.11," + "~" + "2021.11");
			caliculator.PrintFile("No," + "従業員名," + "有給取得可能数");
			
			Calendar calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
			int t = calDesignatedDay.get(Calendar.MONTH);
			t += 2;
			for (int i = 0;i <　23; i++) {
				caliculator.PrintFile("," + t + "月");
				t += 1;
				if (t > 12) {
					t = 1;
				}
			}
			caliculator.PrintInFile("," + t + "月");
			
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				

				Calendar calData = caliculator.ParseStrToCalendar(data[2]);
				
				int number_holiday = 0;
				calData.add(Calendar.MONTH, 6);
				number_holiday = 10;
				
				if (calData.compareTo(calDesignatedDay) < 0) {
					calData.add(Calendar.YEAR, 1);
					number_holiday = 11;
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					calData.add(Calendar.YEAR, 1);
					number_holiday = 12;
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					calData.add(Calendar.YEAR, 1);
					number_holiday = 14;
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					calData.add(Calendar.YEAR, 1);
					number_holiday = 16;
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					calData.add(Calendar.YEAR, 1);
					number_holiday = 18;
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
				
					number_holiday = 20;
				
				}
				
				
//				ここから有給取得日グラフの作成
				BufferedReader br2 = null;
				File file2 = new File("src/took_holiday.csv");
				br2  = new BufferedReader(new FileReader(file2));
				String line2;
				String[] data2;
				int[] sequence2 = new int[24];
				
				line2 = br2.readLine(); 
				while ((line2 = br2.readLine()) != null){
					data2 = line2.split(",");
					if (data2[0].equals(data[0])) {
						Calendar calData2 = caliculator.ParseStrToCalendar(data2[2]);
						calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
						calDesignatedDay.add(Calendar.MONTH,-24);
						
						int times = 0;
						calDesignatedDay.add(Calendar.MONTH, 1);
						while (calDesignatedDay.compareTo(calData2) < 0) {
							times += 1;
							calDesignatedDay.add(Calendar.MONTH, 1);
						}
						if (times > 23) {
							times = 23;
//							指定した日より先にある有給取得データは右端に、指定した日より2年以上前にある有給取得データは左端に表示される。
						}
						sequence2[times] += 1;
						
					}
				}
				
				caliculator.PrintFile(data[0] + "," + data[1] + "," + number_holiday +  "日");
				for (int i = 0; i < 23; i++) {
					caliculator.PrintFile("," + sequence2[i]);
				}
				caliculator.PrintInFile("," + sequence2[23]);
				br2.close();
				
				
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally { 
			
			try{
				br.close();	

			}
		    catch (Exception e){
			System.out.println(e.getMessage());
			
		}
		


		

	}

	}
}

