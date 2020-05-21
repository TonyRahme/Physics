package Physics;

public class Vector2f{
	public float x;
	public float y;
	public Vector2f(float X, float Y)
	{
		this.x = X;
		this.y = Y;
	}

	public Vector2f add(Vector2f other) {
		this.x +=  other.x;
		this.y +=  other.y;
		return this;
	}

	public Vector2f add(Object X, Object Y) {
		this.x += (float) X;
		this.y += (float) Y;
		return this;
	}
	
	public Vector2f add(Object value){
		this.x += (float) value;
		this.y += (float) value;
		return this;
	}

	public Vector2f multiply(Vector2f other) {
		this.x *=  other.x;
		this.y *=  other.y;
		return this;
	}

	public Vector2f multiply(Object X, Object Y) {
		this.x *= (float) X;
		this.y *= (float) Y;
		return this;
	}
	
	public Vector2f multiply(Object value){
		this.x *= (float) value;
		this.y *= (float) value;
		return this;
	}

	public Vector2f divide(Vector2f other) {
		this.x /= other.x;
		this.y /= other.y;
		return this;
	}

	public Vector2f divide(Object X, Object Y) {
		this.x /= (float) X;
		this.y /= (float) Y;
		return this;
	}
	
	public Vector2f divide(Object value){
		this.x /= (float) value;
		this.y /= (float) value;
		return this;
	}

	public Vector2f subtract(Vector2f other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector2f subtract(Object X, Object Y) {
		this.x -= (float) X;
		this.y -= (float) Y;
		return this;
	}
	
	public Vector2f subtract(Object value){
		this.x -= (float) value;
		this.y -= (float) value;
		return this;
	}
}
