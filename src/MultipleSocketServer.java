import java.net.*;
import java.io.*;
import java.util.*;

import Client.PeerInfo;
import Tracker.PeerRegistration;

public class MultipleSocketServer implements Runnable {

	private Socket connection;
	private PeerRegistration pg;
	
	public static void main(String[] args) {
		int port = 19999;
		int count = 0;
		try {
			ServerSocket socket1 = new ServerSocket(port);
			while (true) {
				Socket connection = socket1.accept();
				Runnable runnable = new MultipleSocketServer(connection,
						++count);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (Exception e) {
		}
	}

	MultipleSocketServer(Socket s, int i) {
		this.connection = s;
	}

	public void run() {
		try {
			  InputStream in = connection.getInputStream();
			  OutputStream out = connection.getOutputStream();
			  BufferedReader br = new BufferedReader(new InputStreamReader(in, "US-ASCII"));
			  out = new BufferedOutputStream(out);
			  PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "US-ASCII"));
			  
			  String request = br.readLine();
			  
			  Query q = new Query(request);
			  q.handleQuery();
			  
			  if (q.getType() == Types.PEER_REG) {
				  PeerInfo pi = new PeerInfo();
				  pi.setIpAddress(q.getIP());
				  pg.addPeer(pi);
				  pw.println("Peer " + q.getIP() + " has successfully register itself.");
				  pw.flush();
			  } else {
				  pw.println("All peers:");
				  pw.flush();
				  for (PeerInfo res : pg.getPeerSet()) {
					  pw.println("Peer:- " + res.getIpAddress());
					  pw.flush();
				  }
			  }
			  
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
			}
		}
	}

}
