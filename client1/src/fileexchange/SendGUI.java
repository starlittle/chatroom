package fileexchange;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SendGUI extends JFrame {
	
	private String filename;
	private int filesize;
	
	public SendGUI() {
		initComponents();
	}

	public File getFile() {
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(new JFrame());
		File sendfile;
		if (result == JFileChooser.APPROVE_OPTION) {
			sendfile = fc.getSelectedFile();
			
			filename = sendfile.getAbsolutePath();
			filesize = (int) sendfile.length();
			
			System.out.println("getfile: " + filename + " " + filesize);
			
			return sendfile;
		}
		else
			return null;
	}

	private void initComponents() {
	
	}

}