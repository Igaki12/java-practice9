import java.io.*;
import java.util.Calendar;
public class Main {

	public static void main(String[] args) {
		Caliculator caliculator = new Caliculator();
		BufferedReader br = null;
		BufferedReader br_th = null;
		
//		有給取得基準日を入力(yyyy/MM/dd)
		String strDesignatedDay = "2021/10/31";
		
		
		try {
			Calendar calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
			int y = calDesignatedDay.get(Calendar.YEAR) - 2;
			int M = calDesignatedDay.get(Calendar.MONTH) + 2;
			if (M > 12) {
				M = 1;
			}
			caliculator.PrintFile(y + "." + M + ",~,") ;
			caliculator.PrintInFile("基準日:" + strDesignatedDay);
			caliculator.PrintFile("No," + "従業員名," + "有給取得可能数");
			for ( int i = 0; i < 23; i++ ) {
				if (i % 12 == 0) {
					caliculator.PrintFile(",有給更新月");
				}else {
					caliculator.PrintFile("," + (i%12) + "ケ月目");
				}
			}
			caliculator.PrintInFile(",11ケ月目");
			
			
		    
			String file_name = "src/data.csv";
			File file = new File(file_name);
			br = new BufferedReader(new FileReader(file));
			
			String line;
			String[] data;
			
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				

				Calendar calData = caliculator.ParseStrToCalendar(data[2]);
				
				int numHoliday = 0;
				calData.add(Calendar.MONTH, 6);
				if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 10;
					calData.add(Calendar.YEAR, 1);
				}
				if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 11;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 12 - 10;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 14 - 11;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 16 - 12;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 18 - 14;
					calData.add(Calendar.YEAR, 1);
				
				}if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 20 - 16;
					calData.add(Calendar.YEAR, 1);
					
				}if (calData.compareTo(calDesignatedDay) < 0) {
					numHoliday += 20 - 18;
					calData.add(Calendar.YEAR, 1);
				}
				
				
//				ここから有給取得日グラフ(th)の作成
	    		Calendar calLastUpdateDay = caliculator.ParseStrToCalendar(data[2]);
    			Calendar calDesignatedDay2 = caliculator.ParseStrToCalendar(strDesignatedDay);	
				calLastUpdateDay.add(Calendar.MONTH, 6);
				int years = 0;
				while (calLastUpdateDay.compareTo(calDesignatedDay2) < 0) {
					years += 1;
					calLastUpdateDay.add(Calendar.YEAR, 1);
				}
//				グラフ左端の月日
				if (years < 2) {
					calLastUpdateDay.add(Calendar.YEAR, -years);
				}
				else {
					calLastUpdateDay.add(Calendar.YEAR, -2);
				}
					
				String strLastUpdateDay = calLastUpdateDay.get(Calendar.YEAR) + "/" + calLastUpdateDay.get(Calendar.MONTH) + "/" + calLastUpdateDay.get(Calendar.DAY_OF_MONTH);
				
				if (calLastUpdateDay.compareTo(calDesignatedDay2) > 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + numHoliday +  "日" + ",有給取得できません");
					continue;
				}
				int index = 1;
				calLastUpdateDay.add(Calendar.MONTH, 1);
				while (calLastUpdateDay.compareTo(calDesignatedDay2) < 0) {
					index += 1;
					calLastUpdateDay.add(Calendar.MONTH, 1);
				}
				int[] sequence = new int[index];
				

				
				File th = new File("src/took_holiday.csv");
				br_th  = new BufferedReader(new FileReader(th));
				String line_th;
				String[] data_th;
				line_th = br_th.readLine(); 
				while ((line_th = br_th.readLine()) != null){
					data_th = line_th.split(",");
					Calendar calLastUpdateDay2 = caliculator.ParseStrToCalendar(strLastUpdateDay);

					if (data_th[0].equals(data[0])) {
						Calendar calData_th = caliculator.ParseStrToCalendar(data_th[2]);
						
						
						if (calLastUpdateDay2.compareTo(calData_th) > 0) {
							continue;
						}
						int times = 0;
						calLastUpdateDay2.add(Calendar.MONTH, 1);
						while (calLastUpdateDay2.compareTo(calData_th) < 0) {
							times += 1;
							calLastUpdateDay2.add(Calendar.MONTH, 1);
						}
						if (times > index) {
							System.out.println("指定した日より先の有給取得データがあります");
							continue;
//							指定した日より先にあったり、最終更新日より1年以上前にあるため表に入らない有給取得状況データは無効として扱われる
						}
						sequence[times] += 1;	
					}
				}
				
//				ファイルへの最終出力
				caliculator.PrintFile(data[0] + "," + data[1] + "," + numHoliday +  "日");
				for (int i = 0; i < (index-1); i++) {
					caliculator.PrintFile("," + sequence[i]);
				}
				caliculator.PrintInFile("," + sequence[index-1]);
				
				
				
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally { 
			
			try{
				br.close();	
				br_th.close();

			}
		    catch (Exception e){
			System.out.println(e.getMessage());
			
		}
		


		

	}

	}
}

