package nagarciah.test.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class BioHTTPClient {
	public static void main(String[] args) {
		Socket smtpSocket = null;
		DataOutputStream os = null;
		DataInputStream is = null;

		try {
			smtpSocket = new Socket("10.101.85.61", 8081);
			os = new DataOutputStream(smtpSocket.getOutputStream());
			is = new DataInputStream(smtpSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname");
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to: hostname");
		}

		if (smtpSocket != null && os != null && is != null) {
			try {
				os.writeBytes("GET /\n");

				String responseLine;
				while ((responseLine = is.readLine()) != null) {
					System.out.println(responseLine);
					if (responseLine.indexOf("</html>") != -1) {
						break;
					}
				}

				os.close();
				is.close();
				smtpSocket.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: ");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("IOException:  ");
				e.printStackTrace();
			}
		}
	}
}