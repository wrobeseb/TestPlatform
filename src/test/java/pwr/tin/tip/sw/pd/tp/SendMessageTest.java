package pwr.tin.tip.sw.pd.tp;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import pwr.tin.tip.sw.pd.tp.theme.JMSTheme;
import pwr.tin.tip.sw.pd.tp.utils.JMSSender;

public class SendMessageTest {

	private final JMSSender wf = JMSSender.sendTo(JMSTheme.msgBrokerUrl, JMSTheme.CU_WF_RequestQueue);
	private final JMSSender eu = JMSSender.sendTo(JMSTheme.msgBrokerUrl, JMSTheme.CU_EU_ResponseQueue);
	
	@Test
	public void sendTest() throws JMSException {
		TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText("test");
		textMessage.setIntProperty("cuId", 0);
		
		eu.sendMessage(textMessage);
	}
}
