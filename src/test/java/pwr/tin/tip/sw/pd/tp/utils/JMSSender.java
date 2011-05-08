package pwr.tin.tip.sw.pd.tp.utils;

import java.util.Hashtable;

import javax.jms.JMSException;

import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.MessageProducer;

import javax.jms.DeliveryMode;

public class JMSSender extends JMSUtils {

	private MessageProducer messageProducer;
		
	private JMSSender(String msgBrokerUrl, String queue) {
		try {
			setConnection(msgBrokerUrl);
			setQueue(queue);
			messageProducer = getMessageProducer();
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
		}
		catch (JMSException ex) {
			ex.printStackTrace();
		} 
	}
		
		
	public synchronized void sendMessage(String message) throws JMSException {
		TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText(message);
		sendMessage(textMessage);
	}
		
	public synchronized void sendMessage(TextMessage message) throws JMSException {
		messageProducer.send(message);
	}

	public void close() {
		try {
			closeSession();
			closeConnection();
		}
		catch (JMSException ex) {
			
		}
	}
		
	private static Hashtable<String, JMSSender> _instancesHash;
	
	public static synchronized JMSSender sendTo(String msgBrokerUrl, String queue) {
		JMSSender _instance = null;
		if (_instancesHash == null) {
			_instancesHash = new Hashtable<String, JMSSender>();
		}
		if (!_instancesHash.containsKey(queue)) {
			_instance = new JMSSender(msgBrokerUrl, queue);
			_instancesHash.put(queue, _instance);
		}
		else {
			_instance = _instancesHash.get(queue);
		}
		return _instance;
	}
}
