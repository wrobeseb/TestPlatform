package pwr.tin.tip.sw.pd.tp.utils;

public class Delayer {
	private static Object obj = new Object();
	
	public static void stop(long time) {
		synchronized (obj) { try { obj.wait(time); } catch (InterruptedException e) {} }
	}
}
