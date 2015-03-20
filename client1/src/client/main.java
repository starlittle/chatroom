package client;

import javax.swing.UIManager;

public class main {
	
	private static Client _ClientObject;
	
	public static void main(String args[]) {
		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		}
//		catch (Exception e) {
			
//		}
        _ClientObject = new Client();
        _ClientObject.connect();
    }

}
