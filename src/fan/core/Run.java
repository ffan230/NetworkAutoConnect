package fan.core;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Run {

	public static void main(String[] args) {
		try {
			Tools p = new Tools();
			while (true) {
				if (p.ping()) {
					System.exit(1);
				} else {
					JOptionPane.showMessageDialog(null, "请检查Tools中宽带信息是否填写！", "错误", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
