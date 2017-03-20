package nagarciah.test.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHTTPSessionHandler implements IoHandler {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ClientSessionHandler.class);

	private boolean finished;

	public boolean isFinished() {
		return finished;
	}


	public void messageReceived(IoSession session, Object message) {
		//LOGGER.warn("Recibido:" + message.toString());
		session.close(false);
		finished = true;
	}

	public void exceptionCaught(IoSession session, Throwable cause) {
		System.err.println("Ocurrió un error:");
		cause.printStackTrace();
		session.close(true);
	}

	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sessionCreated(IoSession session) throws Exception {
		
		
	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void sessionOpened(IoSession session) throws Exception {
		//System.out.println("Sesion creada, enviando mensaje");
		session.write("GET /");
	}
}