package fan.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tools {

	final static String broadbandName = "";//宽带名称 例如：“宽带连接”
	final static String account = "";//宽带账号 例如：“1700500xxxxx”
	final static String passWord = "";//宽带密码
	final static String webSite = "www.baidu.com";//用来ping的网址，检测网通不通
	
	public void Connect() throws IOException {

		Process pro = Runtime.getRuntime().exec("Rasdial" + " " + broadbandName + " " + account + " " + passWord);
		InputStreamReader in = new InputStreamReader(pro.getInputStream(),"GBK");
		BufferedReader bufferedReader = new BufferedReader(in);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("已连接" + " " + broadbandName)) {
				System.out.println(broadbandName + "连接成功！");
			} else if (line.contains("你已经连接到" + " " + broadbandName)) {
				System.out.println("已经连接到" + broadbandName);
			} else if (line.contains("远程访问错误 813")) {
				System.out.println("已经连接到另一个宽带！");
			}
		}
	}

	public void shutDown() throws IOException{
		Process pro = Runtime.getRuntime().exec("rasphone -h "+broadbandName);
	}
	
	public boolean ping() throws IOException {
		
		Process pro = Runtime.getRuntime().exec("ping"+" "+webSite);
		InputStreamReader in = new InputStreamReader(pro.getInputStream(),"GBK");
		BufferedReader bufferedReader = new BufferedReader(in);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if(line.contains("Ping 请求找不到主机")){
				System.out.println("网络不通，正在尝试联网！");
				shutDown();
				Connect();
			}else if(line.contains("正在 Ping")){
				System.out.println("宽带已连接！");
				Connect();
				return true;
			}else if(line.contains("用法: ping")){
				System.out.println("未填写网址！");
				System.exit(1);
			}
		}
		return false;
	}
}
