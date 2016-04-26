package engine;

import engine.input.Keyboard;
import engine.render.Window;
import engine.utils.Timer;

public class GameEngine implements Runnable {
	
	/**
	 * Janela para mostrar os graficos
	 */
	private Window window;
	
	/**
	 * Thread para game loop
	 */
	private boolean running;
	private Thread gameLoop;
	
	/**
	 * Input do jogo
	 */
	public Keyboard keyboard;
	
	/**
	 * Logica do jogo
	 */
	private GameLogic gameLogic;
	
	/**
	 * Construtor
	 */
	public GameEngine() {
		gameLogic = new GameLogic(this);
		window = new Window(gameLogic);
		keyboard = window.getKeyboard();
	}
	
	/**
	 * Método para iniciar jogo
	 */
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		gameLoop = new Thread(this, "Game Loop");
		gameLoop.start();
	}
	
	/**
	 * Metodo para terminar jogo
	 */
	public synchronized void stop() {
		if(!running) 
			return;
		
		gameLoop = null;
		System.exit(0);
	}

	@Override
	public void run() {
		
		// inicializando a logica do jogo
		gameLogic.init();
		
		// controle de fps
		int fpsCount = 0;
		double timer = Timer.getTime();
		double targetTime = 1000 / 60.0;
		
		while(running) {
			
			// verificando eventos ativos
			keyboard.poll();
			
			//atualiza logica do jogo, cuida dos eventos
			gameLogic.handleEvents();
			gameLogic.update();
			
			// renderiza o jogo
			window.update();
			fpsCount++;
			
			// quando se passa um segundo
			if(Timer.getTime() - timer >= 1.0) {
				// mostra taxa de atualização
				System.out.println("FPS: " + fpsCount);
				fpsCount = 0;
				timer++;
			}
			
			// regulando a taxa de atualização do jogo
			long sleepTime = (long) (targetTime - Timer.getDeltaTime() * 1000);
			sleep(sleepTime);
		}
		
	}
	
	/**
	 * Pausa Thread por alguns milisegundos
	 */
	private void sleep(long time) {
		if(time <= 0) 
			return;
		
		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
