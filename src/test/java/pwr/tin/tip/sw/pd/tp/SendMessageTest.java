package pwr.tin.tip.sw.pd.tp;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import pwr.tin.tip.sw.pd.tp.theme.JMSTheme;
import pwr.tin.tip.sw.pd.tp.utils.JMSSender;
import pwr.tin.tip.sw.pd.tp.utils.XmlUtils;

public class SendMessageTest {

	private final JMSSender wf = JMSSender.sendTo(JMSTheme.msgBrokerUrl, JMSTheme.WF_CU_RequestQueue);
	private final JMSSender eu = JMSSender.sendTo(JMSTheme.msgBrokerUrl, JMSTheme.CU_EU_ResponseQueue);
	
	@Test
	public void sendTest() throws JMSException {
		//for (int i = 0; i < 100; i++) {
			int i = 1;
			String msg = XmlUtils.changeValue(JMSTheme.scenerioTemplate, String.valueOf(i), "/scenerio/id");
			msg = XmlUtils.changeValues(msg, String.valueOf(i), "/scenerio/components/algorithm/sessionId");
			wf.sendMessage(msg);
		//}
	}
}
