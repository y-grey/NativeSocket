package com.apress.echo;

import android.os.Bundle;
import android.widget.EditText;

/**
 * Echo client.
 * 
 * @author yph
 */
public class EchoClientActivity extends AbstractEchoActivity {	
	/** IP address. */
	private EditText ipEdit;

	/** Message edit. */
	private EditText messageEdit;

	/**
	 * Constructor.
	 */
	public EchoClientActivity() {
		super(R.layout.activity_echo_client);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ipEdit = (EditText) findViewById(R.id.ip_edit);
		messageEdit = (EditText) findViewById(R.id.message_edit);
	}

	protected void onStartButtonClicked() {
		final String ip = ipEdit.getText().toString();
		final Integer port = getPort();
		final String message = messageEdit.getText().toString();

		if ((0 != ip.length()) && (port != null) && (0 != message.length())) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						 nativeStartTcpClient(ip, port, message);
//						nativeStartUdpClient(ip, port, message);
					} catch (Throwable e) {
						logMessage(e.getMessage());
					}
				}
			}).start();
		}
	}

	/**
	 * Starts the TCP client with the given server IP address and port number,
	 * and sends the given message.
	 * 
	 * @param ip
	 *            IP address.
	 * @param port
	 *            port number.
	 * @param message
	 *            message text.
	 * @throws Exception
	 */
	private native void nativeStartTcpClient(String ip, int port, String message)
			throws Exception;

	/**
	 * Starts the UDP client with the given server IP address and port number.
	 * 
	 * @param ip
	 *            IP address.
	 * @param port
	 *            port number.
	 * @param message
	 *            message text.
	 * @throws Exception
	 */
	private native void nativeStartUdpClient(String ip, int port, String message)
			throws Exception;

}
