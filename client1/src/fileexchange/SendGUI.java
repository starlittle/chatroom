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

	public synchronized File getFile() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("choose file");
        fc.setMultiSelectionEnabled(false);
        
		
		int result = fc.showOpenDialog(new JFrame());
		File sendfile;
		if (result == JFileChooser.APPROVE_OPTION) {
			sendfile = fc.getSelectedFile();
			
			filename = sendfile.getName();
			filesize = (int) sendfile.length();
			
			System.out.println("getfile: " + filename + " " + filesize);		
			return sendfile;
		}
		else
			return null;
	}
	
	public String getFilename() {
		return filename;
	}

	private void initComponents() {
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		//setTitle("send file");
		//setMinimumSize(new java.awt.Dimension(227,117));
	
	}

}