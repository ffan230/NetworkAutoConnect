package fan.core;

import java.io.IOException;

public class Run {

	public static void main(String[] args) {
		try {
			Tools p = new Tools();
			while(true){
				if(p.ping()){
					System.exit(1);
				}else{
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
