import java.io.*;
import java.util.Calendar;
public class Main {

	public static void main(String[] args) {
	    BufferedReader br = null;
		String file_name = "src/data.csv";
		Caliculator caliculator = new Caliculator();
		
//		�L���擾��������(yyyy/MM/dd)
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
			caliculator.PrintInFile("���:" + strDesignatedDay);
			caliculator.PrintFile("No," + "�]�ƈ���," + "�L���擾�\��");
			
			int t = calDesignatedDay.get(Calendar.MONTH) + 2;
			if (t > 12) {
				t = 1;
			}
			for (int i = 0;i < 23; i++) {
				caliculator.PrintFile("," + t + "��");
				t += 1;
				if (t > 12) {
					t = 1;
				}
			}
			caliculator.PrintInFile("," + t + "��");
			
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
				
				
//				��������L���擾���O���t(th)�̍쐬
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
//							�w�肵��������ɂ���L���擾�f�[�^�͉E�[�ɁA�w�肵�������2�N�ȏ�O�ɂ���L���擾�f�[�^�͍��[�ɕ��̐��ŕ\�������B
						}
						sequence[times] -= 1;	
					}
				}
//				�L���擾�\���X�V�����錎�ɂ͐V���Ɏ擾�������𐳂̐��ŕ\������悤�ɂ���			
				Calendar calDesignatedDay3 = caliculator.ParseStrToCalendar(strDesignatedDay);
				calDesignatedDay3.add(Calendar.MONTH, -24);
				
				for (int times = 0; times < 24 ;times++) {
					calDesignatedDay3.add(Calendar.MONTH, 1);
					Calendar calData2 = caliculator.ParseStrToCalendar(data[2]);
					calData2.add(Calendar.MONTH, 6);
					
					if(calData2.get(Calendar.MONTH) == calDesignatedDay3.get(Calendar.MONTH) && calData2.compareTo(calDesignatedDay3) < 0 ) {
						sequence[times] += 10;
						calData2.add(Calendar.YEAR, 1);
//						�L���擾�\���������i10~20���j�̌���->���̐��ŕ\��
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
				
				
				
//				�t�@�C���ւ̍ŏI�o��
				caliculator.PrintFile(data[0] + "," + data[1] + "," + number_holiday +  "��");
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

