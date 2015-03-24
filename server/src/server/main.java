package server;

import server.server;

import javax.swing.*;
public class main {
	private static server s;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Throwable {
    	UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		s = new server();
    }
}