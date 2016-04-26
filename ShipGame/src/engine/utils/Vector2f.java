package engine.utils;

public class Vector2f {

	public float x;
	public float y;
	
	public Vector2f() {
		this.x = 0.0f;
		this.y = 0.0f;
	}
	
	public Vector2f(float a, float b) {
		this.x = a;
		this.y = b;
	}
	
	public void add(Vector2f v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public void mul(float escalar) {
		this.x *= escalar;
		this.y *= escalar;
	}
	
	public static Vector2f zero() {
		return new Vector2f();
	}
	
	public static Vector2f one() {
		return new Vector2f(1.0f, 1.0f);
	}
}
