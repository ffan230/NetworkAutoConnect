package fan.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tools {

	final static String broadbandName = "";//������� ���磺��������ӡ�
	final static String account = "";//����˺� ���磺��1700500xxxxx��
	final static String passWord = "";//�������
	final static String webSite = "";//����ping����ַ�������ͨ��ͨ
	
	public void Connect() throws IOException {

		Process pro = Runtime.getRuntime().exec("Rasdial" + " " + broadbandName + " " + account + " " + passWord);
		InputStreamReader in = new InputStreamReader(pro.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(in);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("������" + " " + broadbandName)) {
				System.out.println(broadbandName + "���ӳɹ���");
			} else if (line.contains("���Ѿ����ӵ�" + " " + broadbandName)) {
				System.out.println("�Ѿ����ӵ�" + broadbandName);
			} else if (line.contains("Զ�̷��ʴ��� 813")) {
				System.out.println("�Ѿ����ӵ���һ�������");
			}
		}
	}

	public void shutDown() throws IOException{
		Process pro = Runtime.getRuntime().exec("rasphone -h "+broadbandName);
	}
	
	public boolean ping() throws IOException {
		
		Process pro = Runtime.getRuntime().exec("ping"+" "+webSite);
		InputStreamReader in = new InputStreamReader(pro.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(in);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if(line.contains("Ping �����Ҳ�������")){
				shutDown();
				Connect();
			}else if(line.contains("���� Ping")){
				return true;
			}
		}
		return false;
	}
}
