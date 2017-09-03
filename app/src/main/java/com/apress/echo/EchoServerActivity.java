package com.apress.echo;


import android.os.Bundle;
import android.widget.TextView;

/**
 * Echo server.
 * 
 * @author yph
 */
public class EchoServerActivity extends AbstractEchoActivity {
	/**
	 * Constructor.
	 */
	public EchoServerActivity() {
		super(R.layout.activity_echo_server);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView ipText = (TextView)findViewById(R.id.ipText);
		ipText.setText(NetUtil.getIPAddress(this));
	}
	protected void onStartButtonClicked() {
		final Integer port = getPort();
		if (port != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						 nativeStartTcpServer(port);
//						nativeStartUdpServer(port);
					} catch (Exception e) {
						logMessage(e.getMessage());
					}
				}
			}).start();
		}
	}

	/**
	 * Starts the TCP server on the given port.
	 * 
	 * @param port
	 *            port number.
	 * @throws Exception
	 */
	private native void nativeStartTcpServer(int port) throws Exception;

	/**
	 * Starts the UDP server on the given port.
	 * 
	 * @param port
	 *            port number.
	 * @throws Exception
	 */
	private native void nativeStartUdpServer(int port) throws Exception;

}
