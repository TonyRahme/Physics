package Physics;

public class Vector2i{
	public int x;
	public int y;
	public Vector2i(int X, int Y)
	{
		this.x = X;
		this.y = Y;
	}

	public Vector2i add(Vector2i other) {
		this.x +=  other.x;
		this.y +=  other.y;
		return this;
	}

	public Vector2i add(Object X, Object Y) {
		this.x += (int) X;
		this.y += (int) Y;
		return this;
	}
	
	public Vector2i add(Object value){
		this.x += (int) value;
		this.y += (int) value;
		return this;
	}

	public Vector2i multiply(Vector2i other) {
		this.x *=  other.x;
		this.y *=  other.y;
		return this;
	}

	public Vector2i multiply(Object X, Object Y) {
		this.x *= (int) X;
		this.y *= (int) Y;
		return this;
	}
	
	public Vector2i multiply(Object value){
		this.x *= (int) value;
		this.y *= (int) value;
		return this;
	}

	public Vector2i divide(Vector2i other) {
		this.x /= other.x;
		this.y /= other.y;
		return this;
	}

	public Vector2i divide(Object X, Object Y) {
		this.x /= (int) X;
		this.y /= (int) Y;
		return this;
	}
	
	public Vector2i divide(Object value){
		this.x /= (int) value;
		this.y /= (int) value;
		return this;
	}

	public Vector2i subtract(Vector2i other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector2i subtract(Object X, Object Y) {
		this.x -= (int) X;
		this.y -= (int) Y;
		return this;
	}
	
	public Vector2i subtract(Object value){
		this.x -= (int) value;
		this.y -= (int) value;
		return this;
	}
}
