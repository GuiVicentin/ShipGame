package engine.entities;

import engine.utils.Vector2f;

public class Camera {
	
	private Player player;
	private Vector2f position;
	private float xDelay, yDelay;
	
	public Camera(Player player) {
		this.player = player;
		position = new Vector2f();
		
		xDelay = 0.05f;
		yDelay = 0.05f;
	}
	
	public void update() {
		Vector2f playerPos = player.getPos();
		float targetX = playerPos.x - (1280/2 - 75/2);
		float targetY = playerPos.y - (720/2 - 112/2);
		
		this.position.x = lerp(position.x, targetX, xDelay);
		this.position.y = lerp(position.y, targetY, yDelay);
	}
	
	public Vector2f getPos() {
		return position;
	}
	
	private static float lerp(float v0, float v1, float t) {
		return v0 + t * (v1 - v0);
	}
	
}
