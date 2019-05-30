package pt.up.fe.pangolin.core.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ThreadedServer extends Thread {

	private ThreadGroup threads = new ThreadGroup("");
	private ServerSocket serverSocket;

	public ThreadedServer (ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	@Override
	public final void run () {
		while (!serverSocket.isClosed()) {
			try {
				Socket s = serverSocket.accept();
				Thread t = new Thread(threads, handle(s));
				t.start();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getPort() {
		return serverSocket.getLocalPort();
	}
	
	protected abstract Runnable handle (Socket s);
}