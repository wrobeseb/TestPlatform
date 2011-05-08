package pwr.tin.tip.sw.pd.tp.utils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

public class JMSListener extends JMSUtils {
	
	private MessageConsumer messageConsumer;
	
	public static JMSListener getInstance(String msgBrokerUrl, String queue) {
		return new JMSListener(msgBrokerUrl, queue);
	}
	
	public static JMSListener getInstance(String msgBrokerUrl, String queue, Integer messageId) {
		return new JMSListener(msgBrokerUrl, queue, messageId);
	}
	
	private JMSListener(String msgBrokerUrl, String queue) {
		try {
			setConnection(msgBrokerUrl);
			setQueue(queue);
			messageConsumer = getMessageConsumer();
		}
		catch (JMSException ex) {
		}
	}
	
	private JMSListener(String msgBrokerUrl, String queue, Integer messageId) {
		try {
			setConnection(msgBrokerUrl);
			setQueue(queue);
			messageConsumer = getMessageConsumer("sessionId = " + messageId);
		}
		catch (JMSException ex) {	
		} 
	}
	
	public String getMessage() throws JMSException {
		Message message = messageConsumer.receive();
		if (message != null) {
			if (message instanceof TextMessage) {
				return ((TextMessage)message).getText();
			}
		}
		return null;
	}
	
	public void closeListener() {
		try {
			closeSession();
			closeConnection();
		}
		catch (JMSException ex) {
			
		}
	}
}
