package pwr.tin.tip.sw.pd.tp;

import javax.jms.JMSException;

import org.junit.Test;

import static org.junit.Assert.*;

import pwr.tin.tip.sw.pd.tp.theme.JMSTheme;
import pwr.tin.tip.sw.pd.tp.utils.JMSSender;
import pwr.tin.tip.sw.pd.tp.utils.JMSListener;
import pwr.tin.tip.sw.pd.tp.utils.XmlUtils;

public class CentralUnitTest {

	private final JMSListener esbListener = JMSListener.getInstance(JMSTheme.msgBrokerUrl, JMSTheme.esbInQueue);
	private final JMSListener cuListener = JMSListener.getInstance(JMSTheme.msgBrokerUrl, JMSTheme.cuResponseQueue);
	
	private final JMSSender cuSender = JMSSender.sendTo(JMSTheme.msgBrokerUrl, JMSTheme.cuInQueue);
	private final JMSSender esbSender = JMSSender.sendTo(JMSTheme.msgBrokerUrl, JMSTheme.esbResponseQueue);
	
	/**
	 * Test sprawdzaj±cy mo¿liwo¶ci procesowania jednej wiadomo¶ci zawieraj±cej scenariusz testowy.
	 * 
	 * Wymagane jest aby Jednostka centralna by³a podczas testu w³±czona...
	 * 
	 * @throws JMSException
	 */
	//@Test
	public void processingOneScenerioTest() throws JMSException {
		cuSender.sendMessage(JMSTheme.scenerioTemplate);
		
		for (int i = 0; i < 3; i++) {
			esbSender.sendMessage(createResponse(esbListener.getMessage()));
		}
		
		String response = cuListener.getMessage();
		assertNotNull(response);
	}
	
	@Test
	public void processingManySceneriosTest() throws JMSException {
		for (int i = 0; i < 300; i++) {
			String msg = XmlUtils.changeValue(JMSTheme.scenerioTemplate, String.valueOf(i), "/scenerio/id");
			msg = XmlUtils.changeValues(msg, String.valueOf(i), "/scenerio/components/algorithm/sessionId");
			cuSender.sendMessage(msg);
			for (int j = 0; j < 3; j++) {
				esbSender.sendMessage(createResponse(esbListener.getMessage()));
			}
		}
		
		for (int i = 0; i < 300; i++) {
			String response = cuListener.getMessage();
			assertNotNull(response);
		}
	}
	
	private String createResponse(String messageBody) {
		String response = JMSTheme.algorithmResponseTemplate;
		response = XmlUtils.changeValue(response, XmlUtils.getString(messageBody, "/algorithm/id"), "/algorithmResponse/id");
		response = XmlUtils.changeValue(response, XmlUtils.getString(messageBody, "/algorithm/sessionId"), "/algorithmResponse/sessionId");
		return response;
	}
}
