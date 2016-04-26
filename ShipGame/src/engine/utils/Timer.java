package engine.utils;

public class Timer {
	
	/**
	 * Tempo que ocorreu ultimo loop
	 */
	private static double lastLoopTime;
	
	/**
	 * 
	 * @return tempo em segundos
	 */
	public static double getTime() {
		return System.nanoTime() / 1000000000.0;
	}
	
	/**
	 * 
	 * @return variação de uma interação em segundos
	 */
	public static double getDeltaTime() {
		double now = getTime();
		double delta = now - lastLoopTime;
		lastLoopTime = now;
		
		return delta;
	}
}
