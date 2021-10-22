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

			
			Calendar calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
			int y = calDesignatedDay.get(Calendar.YEAR) - 2;
			int M = calDesignatedDay.get(Calendar.MONTH) + 2;
			if (M > 12) {
				M = 1;
			}
			caliculator.PrintFile(y + "." + M + ",~,") ;
			caliculator.PrintInFile("基準日:" + strDesignatedDay);
			caliculator.PrintFile("No," + "従業員名," + "有給取得可能数");
			
			int t = calDesignatedDay.get(Calendar.MONTH) + 2;
			if (t > 12) {
				t = 1;
			}
			for (int i = 0;i < 23; i++) {
				caliculator.PrintFile("," + t + "月");
				t += 1;
				if (t > 12) {
					t = 1;
				}
			}
			caliculator.PrintInFile("," + t + "月");
			
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				
				int[] sequence = new int[24];
				Calendar calData = caliculator.ParseStrToCalendar(data[2]);
				
				int number_holiday = 0;
				calData.add(Calendar.MONTH, 6);
				if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 10;
					calData.add(Calendar.YEAR, 1);
				}
				if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 11;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 12 - 10;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 14 - 11;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 16 - 12;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 18 - 14;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 20 - 16;
					calData.add(Calendar.YEAR, 1);
					
				}if (calData.compareTo(calDesignatedDay) < 0) {
					number_holiday += 20 - 18;
					calData.add(Calendar.YEAR, 1);
				}
				
				
//				ここから有給取得日グラフ(th)の作成
				BufferedReader br_th = null;
				File th = new File("src/took_holiday.csv");
				br_th  = new BufferedReader(new FileReader(th));
				String line_th;
				String[] data_th;
				
				
				line_th = br_th.readLine(); 
				while ((line_th = br_th.readLine()) != null){
					data_th = line_th.split(",");
					if (data_th[0].equals(data[0])) {
						Calendar calData_th = caliculator.ParseStrToCalendar(data_th[2]);
						Calendar calDesignatedDay2 = caliculator.ParseStrToCalendar(strDesignatedDay);
						calDesignatedDay2.add(Calendar.MONTH,-24);
						
						int times = 0;
						calDesignatedDay2.add(Calendar.MONTH, 1);
						while (calDesignatedDay2.compareTo(calData_th) < 0) {
							times += 1;
							calDesignatedDay2.add(Calendar.MONTH, 1);
						}
						if (times > 23) {
							times = 23;
//							指定した日より先にある有給取得データは右端に、指定した日より2年以上前にある有給取得データは左端に負の数で表示される。
						}
						sequence[times] -= 1;	
					}
				}
//				有給取得可能数更新が入る月には新たに取得した数を正の数で表示するようにする			
				Calendar calDesignatedDay3 = caliculator.ParseStrToCalendar(strDesignatedDay);
				calDesignatedDay3.add(Calendar.MONTH, -24);
				
				for (int times = 0; times < 24 ;times++) {
					calDesignatedDay3.add(Calendar.MONTH, 1);
					Calendar calData2 = caliculator.ParseStrToCalendar(data[2]);
					calData2.add(Calendar.MONTH, 6);
					
					if(calData2.get(Calendar.MONTH) == calDesignatedDay3.get(Calendar.MONTH) && calData2.compareTo(calDesignatedDay3) < 0 ) {
						sequence[times] += 10;
						calData2.add(Calendar.YEAR, 1);
//						有給取得可能数増加分（10~20日）の決定->正の数で表現
						if (calData2.compareTo(calDesignatedDay3) < 0) {
							sequence[times] += 11 - 10;
							calData2.add(Calendar.YEAR, 1);	
						}if (calData2.compareTo(calDesignatedDay3) < 0) {
							sequence[times] += 12 - 11;
							calData2.add(Calendar.YEAR, 1);
						}if (calData2.compareTo(calDesignatedDay3) < 0) {
							sequence[times] += 14 - 12;
							calData2.add(Calendar.YEAR, 1);
						}if (calData2.compareTo(calDesignatedDay3) < 0) {
							sequence[times] += 16 - 14;
							calData2.add(Calendar.YEAR, 1);
						}if (calData2.compareTo(calDesignatedDay3) < 0) {
							sequence[times] += 18 - 16;
							calData2.add(Calendar.YEAR, 1);
						}if (calData2.compareTo(calDesignatedDay3) < 0) {
							sequence[times] += 20 - 18;
						}
					}
				}
				
				
				
//				ファイルへの最終出力
				caliculator.PrintFile(data[0] + "," + data[1] + "," + number_holiday +  "日");
				for (int i = 0; i < 23; i++) {
					caliculator.PrintFile("," + sequence[i]);
				}
				caliculator.PrintInFile("," + sequence[23]);
				br_th.close();
				
				
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

