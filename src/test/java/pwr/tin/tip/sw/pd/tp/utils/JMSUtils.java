package pwr.tin.tip.sw.pd.tp.utils;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public abstract class JMSUtils {

	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	
	protected void setConnection(String brokerUrl) throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
		connection = connectionFactory.createConnection();
		connection.start();
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	protected void setQueue(String queueName) throws JMSException {
		destination = session.createQueue(queueName);
	}
	
	protected MessageProducer getMessageProducer() throws JMSException {
		return session.createProducer(destination);
	}
	
	protected MessageConsumer getMessageConsumer(String selector) throws JMSException {
		return session.createConsumer(destination, selector);
	}
	
	protected MessageConsumer getMessageConsumer() throws JMSException {
		return session.createConsumer(destination);
	}
	
	protected void closeSession() throws JMSException {
		session.close();
	}
	
	protected void closeConnection() throws JMSException {
		connection.close();
	}
}
