package audio;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.sound.sampled.*;

public class audio extends JFrame {

	boolean stopaudioCapture = false;
	ByteArrayOutputStream byteOutputStream;
	AudioFormat adFormat;
	TargetDataLine targetDataLine;
	AudioInputStream InputStream;
	SourceDataLine sourceLine;
	Graphics g;
	public String ip;
	public int port1;
	public int port2;
	DatagramSocket serverSocket;

	public audio(String i, String p1, String p2) {
		final JButton capture = new JButton("Capture");
		final JButton stop = new JButton("Stop");
		final JButton play = new JButton("Playback");
		port1 = Integer.parseInt(p1);
		port2 = Integer.parseInt(p2);
		ip = i;

		capture.setEnabled(true);
		stop.setEnabled(false);
		play.setEnabled(false);

		capture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capture.setEnabled(false);
				stop.setEnabled(true);
				play.setEnabled(false);
				captureAudio();
			}
		});
		getContentPane().add(capture);

    stop.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            capture.setEnabled(true);
            stop.setEnabled(false);
            play.setEnabled(true);
            stopaudioCapture = true;
            targetDataLine.close();
            serverSocket.close();
            return;
        }
    });
    getContentPane().add(stop);

    play.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playAudio();
        }
    });
    getContentPane().add(play);

    getContentPane().setLayout(new FlowLayout());
    setTitle("Capture/Playback Demo");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(400, 100);
    getContentPane().setBackground(Color.white);
    setVisible(true);

    g = (Graphics) this.getGraphics();
}

private void captureAudio() {
    try {
        adFormat = getAudioFormat();
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, adFormat);
        targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        targetDataLine.open(adFormat);
        targetDataLine.start();

        Thread captureThread = new Thread(new CaptureThread());
        captureThread.start();
    } catch (Exception e) {
        StackTraceElement stackEle[] = e.getStackTrace();
        for (StackTraceElement val : stackEle) {
            System.out.println(val);
        }
        System.exit(0);
    }
}

private void playAudio() {
    try {
        byte audioData[] = byteOutputStream.toByteArray();
        InputStream byteInputStream = new ByteArrayInputStream(audioData);
        AudioFormat adFormat = getAudioFormat();
        InputStream = new AudioInputStream(byteInputStream, adFormat, audioData.length / adFormat.getFrameSize());
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, adFormat);
        sourceLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceLine.open(adFormat);
        sourceLine.start();
        Thread playThread = new Thread(new PlayThread());
        playThread.start();
    } catch (Exception e) {
        System.out.println(e);
        System.exit(0);
    }
}

private AudioFormat getAudioFormat() {
    float sampleRate = 16000.0F;
    int sampleInbits = 16;
    int channels = 1;
    boolean signed = true;
    boolean bigEndian = false;
    return new AudioFormat(sampleRate, sampleInbits, channels, signed, bigEndian);
}

class CaptureThread extends Thread {

    byte tempBuffer[] = new byte[10000];

    public void run() {

        byteOutputStream = new ByteArrayOutputStream();
        stopaudioCapture = false;
        try {
            DatagramSocket clientSocket = new DatagramSocket(7000);
            InetAddress IPAddress = InetAddress.getByName(ip); //"140.112.18.219"
            while (!stopaudioCapture) {
                int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                if (cnt > 0) {
                    DatagramPacket sendPacket = new DatagramPacket(tempBuffer, tempBuffer.length, IPAddress, port1);//port1
                    clientSocket.send(sendPacket);
                    byteOutputStream.write(tempBuffer, 0, cnt);
                    System.out.println("send!");
                }
            }
            byteOutputStream.close();
            clientSocket.close();
            return;
        } catch (Exception e) {
            System.out.println("CaptureThread::run()" + e);
            System.exit(0);
        }
    }
}

class PlayThread extends Thread {

    byte tempBuffer[] = new byte[10000];

    public void run() {
        try {
            int cnt;
            while ((cnt = InputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                if (cnt > 0) {
                   sourceLine.write(tempBuffer, 0, cnt);
                   System.out.println("get?");
                }
            }
            return;
            //                sourceLine.drain();
            //             sourceLine.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}

public void runVOIP() {
    try {
        serverSocket = new DatagramSocket(port2);//port2
        byte[] receiveData = new byte[10000];
        while (!stopaudioCapture) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            System.out.println("RECEIVED: " + receivePacket.getAddress().getHostAddress() + " " + receivePacket.getPort());
            try {
                byte audioData[] = receivePacket.getData();
                InputStream byteInputStream = new ByteArrayInputStream(audioData);
                AudioFormat adFormat = getAudioFormat();
                InputStream = new AudioInputStream(byteInputStream, adFormat, audioData.length / adFormat.getFrameSize());
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, adFormat);
                sourceLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceLine.open(adFormat);
                sourceLine.start();
                Thread playThread = new Thread(new PlayThread());
                playThread.start();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }            
        }
        serverSocket.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}