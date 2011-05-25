package pwr.tin.tip.sw.pd.tp.theme;

public class JMSTheme {
	public final static String msgBrokerUrl = "tcp://localhost:61616";
	public final static String CU_WF_RequestQueue = "queue/CU-WF/request";
	public final static String CU_WF_ResponseQueue = "queue/CU-WF/response";
	public final static String CU_EU_RequestQueue = "queue/CU-EU/request";
	public final static String CU_EU_ResponseQueue = "queue/CU-EU/response";
	
	public final static String scenerioTemplate = 	"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"+
													"	<scenerio>"+ 
													"	<id>1</id>"+ 
													"	<name>Test</name>"+ 
													"	<description>Test Desc</description>"+ 
													"	<start>"+ 
													"		<next>1</next>"+ 
													"		<next>2</next>"+ 
													"	</start>"+ 
													"	<components>"+ 
													"		<algorithm>"+ 
													"			<id>1</id>"+ 
													"			<sessionId>1</sessionId>"+ 
													"			<sourceFilePath>source/file/path</sourceFilePath>"+ 
													"			<resultFilePath>result/file/path</resultFilePath>"+
													"		</algorithm>"+ 
													"		<algorithm>"+ 
													"			<id>2</id>"+ 
													"			<sessionId>1</sessionId>"+ 
													"			<sourceFilePath>source/file/path</sourceFilePath>"+ 
													"			<resultFilePath>result/file/path</resultFilePath>"+  
													"		</algorithm>"+ 
													"		<algorithm>"+ 
													"			<id>3</id>"+ 
													"			<sessionId>1</sessionId>"+ 
													"			<sourceFilePath>source/file/path</sourceFilePath>"+ 
													"			<resultFilePath>result/file/path</resultFilePath>"+ 
													"			<prev>1</prev>"+
													"			<prev>2</prev>"+
													"		</algorithm>"+ 
													"	</components>"+ 
													"	<end>"+ 
													"		<prev>1</prev>"+ 
													"		<prev>2</prev>"+ 
													"	</end>"+ 
													"</scenerio>";
	
	public final static String algorithmResponseTemplate = "<algorithmResponse>" +
														   "	<id>1</id>" +
														   "	<sessionId>1</sessionId>" +
														   "	<status>PROCESSED</status>" +
														   "</algorithmResponse>";
}
