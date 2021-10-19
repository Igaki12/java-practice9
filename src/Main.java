import java.io.*;
import java.util.Calendar;
public class Main {

	public static void main(String[] args) {
		
		BufferedReader br = null;
		String file_name = "src/data.csv";
		Caliculator caliculator = new Caliculator();
		int readingLine = 1;
		
//		��������(yyyy/MM/dd)
		String strDesignatedDay = "2020/01/01";
		
		
		try {
			File file = new File(file_name);
			br = new BufferedReader(new FileReader(file));
			
			String line;
			String[] data;
			line = br.readLine();
			caliculator.PrintInFile("No," + "�]�ƈ���," + "�L���擾�\��,");
			
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				readingLine += 1;
				

				Calendar calData = caliculator.ParseStrToCalendar(data[2]);
				Calendar calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
				
				calData.add(Calendar.MONTH, 6);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "10��");
					continue;
				}
				calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "11��");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "12��");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "14��");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "16��");
					continue;
				}calData.add(Calendar.YEAR, 1);
				if (calData.compareTo(calDesignatedDay) >= 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "18��");
					continue;
				}
				if (calData.compareTo(calDesignatedDay) < 0) {
					caliculator.PrintInFile(data[0] + "," + data[1] + "," + "20��");
					continue;
				}
				
				System.out.println(readingLine + "�s�̃f�[�^�Ɉُ킪����܂�");
		
				
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
