package nagarciah.test.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaHTTPClient {

	public static void main(String[] args) throws InterruptedException {
		try {
			System.in.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<1000; i++) {
			
		
	    NioSocketConnector connector = new NioSocketConnector();
	    connector.setConnectTimeoutMillis(10000);

        connector.getFilterChain().addLast("codec",
	            new ProtocolCodecFilter(/*new ObjectSerializationCodecFactory()*/
	            		new TextLineCodecFactory( Charset.forName( "UTF-8" ))));

	    //connector.getFilterChain().addLast("logger", new LoggingFilter());
	    connector.setHandler(new ClientHTTPSessionHandler());
	    IoSession session;

	    
	    for (;;) {
	        try {
	            ConnectFuture future = connector.connect(new InetSocketAddress("10.101.85.61", 8081));
	            future.awaitUninterruptibly();
	            session = future.getSession();
	            break;
	        } catch (RuntimeIoException e) {
	            System.err.println("Failed to connect.");
	            e.printStackTrace();
	            Thread.sleep(5000);
	        }
	    }

	    // wait until the summation is done
	    session.getCloseFuture().awaitUninterruptibly();
	}
	}	
}
