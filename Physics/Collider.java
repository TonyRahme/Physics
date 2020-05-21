package Physics;

public class Collider {
	private RigidBody body;
	private RigidBody other;
	public Collider()
	{
		this.body = null;
		this.other = null;
	}
	public Collider(RigidBody Body)
	{
		this.body = Body;
	}
	public Vector2f GetDirection()	{return		this.body.getDirection();}
	boolean CheckCollision(RigidBody Other, float push)
	{
		if(Other != null){	
		this.other = Other;
		Vector2i thisHalfSize = new Vector2i(this.body.getSize().x/2,this.body.getSize().y/2);
		Vector2i otherHalfSize = new Vector2i(this.other.getSize().x/2,this.other.getSize().y/2);
		Vector2f thisPosition = new Vector2f(this.body.getPosition().x - this.body.getOrigin().x,this.body.getPosition().y - this.body.getOrigin().y);
		Vector2f otherPosition = new Vector2f(this.other.getPosition().x - this.other.getOrigin().x,this.other.getPosition().y - this.other.getOrigin().y);

		float deltaX = otherPosition.x - thisPosition.x;
		float deltaY = otherPosition.y - thisPosition.y;

		float intersectX = Math.abs(deltaX) - (otherHalfSize.x + thisHalfSize.x);
		float intersectY = Math.abs(deltaY) - (otherHalfSize.y + thisHalfSize.y);

		if(intersectX < 0.0f && intersectY < 0.0f)
		{
			/*push = Math.min(Math.max(push, 0.0f), 1.0f);

			if(intersectX > intersectY)
			{
				if(deltaX > 0.0f)
				{
					body.move(intersectX * (1.0f - push), 0.0f);
					other.move(-intersectX * push, 0.0f);
					body.setDirection(new Vector2f(1.0f,0.0f));
				}
				else
				{
					body.move(-intersectX * (1.0f - push), 0.0f);
					other.move(intersectX * push, 0.0f);
					body.setDirection(new Vector2f(-1.0f,0.0f));
				}
			}
			else
			{
				if(deltaY > 0.0f)
				{
					body.move(0.0f,intersectY * (1.0f - push));
					other.move(0.0f,-intersectY * push);
					body.setDirection(new Vector2f(0.0f,1.0f));
				}
				else
				{
					body.move(0.0f,-intersectY * (1.0f - push));
					other.move(0.0f,intersectY * push);
					body.setDirection(new Vector2f(0.0f,-1.0f));
				}
			}*/
			return true;
		}
		else return false;
	}
		else return false;
	
	}
}
