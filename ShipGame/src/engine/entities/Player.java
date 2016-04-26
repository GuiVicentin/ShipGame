package engine.entities;
 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import engine.input.Keyboard;
import engine.utils.Timer;
import engine.utils.Vector2f;
 
public class Player {
 
    private Vector2f position;
    private Vector2f velocity;
    private Vector2f acceleration;
    private float faceAngle = 0.0f;
    private float angleAmount = 5.0f;
    private float drag = 1.0f;
    private float accelerationAmount = 1000.0f;
     
    private BufferedImage sprite;
    private AffineTransform transf;
     
    public Player() {
        position = new Vector2f(0, 0);
        velocity = Vector2f.zero();
        acceleration = Vector2f.zero();
         
        transf = new AffineTransform();
         
        try {
            sprite = ImageIO.read(new File("res/player.png"));
        } catch(Exception e) {
            System.out.println("Nao foi possivel carregar");
            e.printStackTrace();
        }
    }
     
    public void update(Keyboard k) {
        acceleration = Vector2f.zero();
         
        double dt = Timer.getDeltaTime();
         
        if(k.keyDown(KeyEvent.VK_LEFT) || k.keyDown(KeyEvent.VK_A)) {
            faceAngle -= angleAmount * dt;
        }
        if(k.keyDown(KeyEvent.VK_RIGHT) || k.keyDown(KeyEvent.VK_D)) {
            faceAngle += angleAmount * dt;
        }
        if(k.keyDown(KeyEvent.VK_UP) || k.keyDown(KeyEvent.VK_W)) {
            acceleration = Vector2f.one();
        }
        if(k.keyDown(KeyEvent.VK_DOWN) || k.keyDown(KeyEvent.VK_S)) {
            acceleration = Vector2f.one();
            acceleration.mul(-1.0f);
        }
         
        acceleration.x *= accelerationAmount * Math.cos(faceAngle);
        acceleration.y *= accelerationAmount * Math.sin(faceAngle);
         
        acceleration.x -= drag * velocity.x;
        acceleration.y -= drag * velocity.y; 
         
        velocity.x += acceleration.x * dt;
        velocity.y += acceleration.y * dt;
         
        position.x += velocity.x * dt + 0.5f * acceleration.x * dt * dt;
        position.y += velocity.y * dt + 0.5f * acceleration.y * dt * dt;
    }
     
    public void draw(Graphics2D g) {
        g.setColor(Color.MAGENTA);
         
        transf.setToIdentity();
        transf.translate(position.x, position.y);
        transf.rotate(faceAngle, 32, 56);
         
        g.drawImage(sprite, transf, null); 
    }
    
    public Vector2f getPos() {
    	return position;
    }
}