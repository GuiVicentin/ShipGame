package engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.entities.Camera;
import engine.entities.Player;
import engine.entities.Stars;
import engine.input.Keyboard;

public class GameLogic {
	
	private GameEngine engine;
	
	private Player player;
	private Camera camera;
	private Stars stars;
	
	/**
	 * Construtor padrão
	 */
	public GameLogic(GameEngine engine) {
		this.engine = engine;
	}
	
	/**
	 * Inicia logica do jogo
	 */
	public void init() {
		
		player = new Player();
		camera = new Camera(player);
		stars = new Stars();
	}
	
	/**
	 * Cuida dos eventos
	 */
	public void handleEvents() {
		
		Keyboard keys = engine.keyboard;
		
		// verifica encerramento do jogo.
		if(keys.keyDown(KeyEvent.VK_ESCAPE)) {
			engine.stop();
		}
		
		// movimentação
		player.update(keys);
		
	}
	
	/**
	 * Atualiza cada ciclo
	 */
	public void update() {
		
		camera.update();
		
	}
	
	/**
	 * Desenha tudo do jogo
	 * @param g2d
	 */
	public void draw(Graphics2D g2d) {
		
		g2d.translate(-camera.getPos().x, -camera.getPos().y);
		
		//desenha estrela
		stars.draw(g2d);
		
		// desenha jogador
		player.draw(g2d);
		
	}
}
