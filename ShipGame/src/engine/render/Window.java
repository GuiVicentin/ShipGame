package engine.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import engine.GameLogic;
import engine.input.Keyboard;

public class Window extends JFrame {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Dimenções e titulo da tela
	 */
	private static final String TITLE = "SpaceShip Game";
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	/**
	 * Tela para desenhar graficos
	 */
	private Canvas canvas;
	
	/**
	 * BufferStrategy para double buffer
	 */
	private BufferStrategy bs;
	
	/**
	 * Logica que contem método para desenhar
	 */
	private GameLogic logic;
	
	/**
	 * Inputs da tela
	 */
	private Keyboard keyboard;
	
	/**
	 * Construtor 
	 */
	public Window(GameLogic logic) {
		
		// definindo logica para chamar metodo draw
		this.logic = logic;
		
		// definindo metodo de fechamento
		setTitle(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// criando e configurando canvas
		Dimension size = new Dimension(WIDTH - 10, HEIGHT - 10);
		canvas = new Canvas();
		canvas.setMinimumSize(size);
		canvas.setMaximumSize(size);
		canvas.setPreferredSize(size);
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.BLACK);
		
		// adicionando canvas a janela
		getContentPane().add(canvas);
		pack();
		
		// configurando janela
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		// criando bufferStrategy
		canvas.createBufferStrategy(2);
		bs =  canvas.getBufferStrategy();
		
		// adicionando entrada pelo teclado
		keyboard = new Keyboard();
		canvas.addKeyListener(keyboard);
		canvas.setFocusable(true);
		canvas.requestFocus();
	}
	
	/**
	 * Metodo para atualizar a janela 
	 */
	public void update() {
		do {
			do {
				// objeto para desenhar
				Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
				
				// limpando tela
				g2d.clearRect(0, 0, WIDTH, HEIGHT);
				
				// chamando função que desenha jogo
				logic.draw(g2d);
				
				//liberando memoria
				g2d.dispose();
				
			} while(bs.contentsRestored());
			
			// troca de buffer
			bs.show();
			
		} while(bs.contentsLost());
	}
	
	/**
	 * 
	 * @return
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}
}
