import java.io.*;
import java.util.Calendar;
public class Main {

	public static void main(String[] args) {
	    BufferedReader br = null;
		String file_name = "src/data.csv";
		Caliculator caliculator = new Caliculator();
		
//		有給取得基準日を入力(yyyy/MM/dd)
		String strDesignatedDay = "2021/10/20";
		
		
		try {
			File file = new File(file_name);
			br = new BufferedReader(new FileReader(file));
			
			String line;
			String[] data;
			line = br.readLine();
			caliculator.PrintInFile("2019.11," + "~" + "2021.11");
			caliculator.PrintInFile("No," + "従業員名," + "有給取得可能数," + "11月," + "12月," + "1月," + "2月," + "3月," + "4月," + "5月," + "6月," + "7月," + "8月," + "9月," + "10月," + "11月," + "12月," + "1月," + "2月," + "3月," + "4月," + "5月," + "6月," + "7月," + "8月," + "9月," + "10月,");
			
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				

				Calendar calData = caliculator.ParseStrToCalendar(data[2]);
				Calendar calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
				
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
				String strFirstDay =  "2019/10/31";
				int[] sequence2 = new int[24];
				
				line2 = br2.readLine(); 
				while ((line2 = br2.readLine()) != null){
					data2 = line2.split(",");
					if (data2[0].equals(data[0])) {
						Calendar calData2 = caliculator.ParseStrToCalendar(data2[2]);
						Calendar calFirstDay = caliculator.ParseStrToCalendar(strFirstDay);
						
						int times = 0;
						calFirstDay.add(Calendar.MONTH, 1);
						while (calFirstDay.compareTo(calData2) < 0) {
							times += 1;
							calFirstDay.add(Calendar.MONTH, 1);
						}
						sequence2[times] += 1;
						if (times > 24) {
							System.out.println("ERROR:timesOver24");
						}
					}
				}
				
				caliculator.PrintInFile(data[0] + "," + data[1] + "," + number_holiday +  "日," + sequence2[0] + "," + sequence2[1] + "," + sequence2[2] + "," + sequence2[3] + "," + sequence2[4] + "," + sequence2[5] + "," + sequence2[6] + "," + sequence2[7] + "," + sequence2[8] + "," + sequence2[9] + "," + sequence2[10] + "," + sequence2[11] + "," + sequence2[12] + "," + sequence2[13] + "," + sequence2[14] + "," + sequence2[15] + "," + sequence2[16] + "," + sequence2[17] + "," + sequence2[18] + "," + sequence2[19] + "," + sequence2[20] + "," + sequence2[21] + "," + sequence2[22] + "," + sequence2[23]);
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

