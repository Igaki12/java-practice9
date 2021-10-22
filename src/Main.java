import java.io.*;
import java.util.Calendar;
public class Main {

	public static void main(String[] args) {
		Caliculator caliculator = new Caliculator();
		BufferedReader br = null;
		
//		�L���擾��������(yyyy/MM/dd)
		String strDesignatedDay = "2021/10/31";
		
		
		try {
			Calendar calDesignatedDay = caliculator.ParseStrToCalendar(strDesignatedDay);
			int y = calDesignatedDay.get(Calendar.YEAR) - 2;
			int M = calDesignatedDay.get(Calendar.MONTH) + 2;
			if (M > 12) {
				M = 1;
			}
			caliculator.PrintFile(y + "." + M + ",~,") ;
			caliculator.PrintInFile("���:" + strDesignatedDay);
			caliculator.PrintFile("No," + "�]�ƈ���," + "�L���擾�\��");
			for ( int i = 0; i < 23; i++ ) {
				if (i % 12 == 0) {
					caliculator.PrintFile(",�L���X�V��");
				}else {
					caliculator.PrintFile("," + (i%12) + "�P����");
				}
			}
			caliculator.PrintInFile(",11�P����");
			
			
		    
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
				
				
//				��������L���擾���O���t(th)�̍쐬
	    		Calendar daysAfterUpdate = caliculator.ParseStrToCalendar(data[2]);
    			Calendar calDesignatedDay2 = caliculator.ParseStrToCalendar(strDesignatedDay);	
				daysAfterUpdate.add(Calendar.MONTH, 6);
				int years = 0;
				while (daysAfterUpdate.compareTo(calDesignatedDay2) < 0) {
					years += 1;
					daysAfterUpdate.add(Calendar.YEAR, 1);
				}
//				�O���t���[�̌���
				daysAfterUpdate.add(Calendar.YEAR, -2);
				String strDaysAfterUpdate = daysAfterUpdate.get(Calendar.YEAR) + "/" + daysAfterUpdate.get(Calendar.MONTH) + "/" + daysAfterUpdate.get(Calendar.DAY_OF_MONTH);
			
				int index = 1;
				daysAfterUpdate.add(Calendar.MONTH, 1);
				while (daysAfterUpdate.compareTo(calDesignatedDay2) < 0) {
					index += 1;
					daysAfterUpdate.add(Calendar.MONTH, 1);
				}
				int[] sequence = new int[index];
				

				BufferedReader br_th = null;
				File th = new File("src/took_holiday.csv");
				br_th  = new BufferedReader(new FileReader(th));
				String line_th;
				String[] data_th;
				line_th = br_th.readLine(); 
				while ((line_th = br_th.readLine()) != null){
					data_th = line_th.split(",");
					Calendar daysAfterUpdate2 = caliculator.ParseStrToCalendar(strDaysAfterUpdate);

					if (data_th[0].equals(data[0])) {
						Calendar calData_th = caliculator.ParseStrToCalendar(data_th[2]);
						
						int times = 0;
						daysAfterUpdate2.add(Calendar.MONTH, 1);
						while (daysAfterUpdate2.compareTo(calData_th) < 0) {
							times += 1;
							daysAfterUpdate2.add(Calendar.MONTH, 1);
						}
						if (times > index) {
							System.out.println("�w�肵��������̗L���擾�f�[�^������܂�");
							times = index;
//							�w�肵��������ɂ���L���擾�f�[�^�͉E�[�ɁA�ŏI�X�V�����1�N�ȏ�O�ɂ���L���擾�f�[�^�͍��[�ɕ\�������B
						}
						sequence[times] += 1;	
					}
				}
				
//				�t�@�C���ւ̍ŏI�o��
				caliculator.PrintFile(data[0] + "," + data[1] + "," + numHoliday +  "��");
				for (int i = 0; i < (index-1); i++) {
					caliculator.PrintFile("," + sequence[i]);
				}
				caliculator.PrintInFile("," + sequence[index-1]);
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

