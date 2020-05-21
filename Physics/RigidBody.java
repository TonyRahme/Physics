package Physics;
public class RigidBody{
	private Vector2f position;
	private Vector2f origin;
	private Vector2i cornerPoint;
	private Vector2f scale;
	private Vector2i size;
	private Vector2f velocity;
	private Vector2f force;
	private Vector2f direction;
	private float absVelocity;
	private static final Vector2f gravity = new Vector2f(0.0f,9.81f);
	private float absForce;
	private boolean rigidSwitch;
	private double mass;
	private double angle;
	private double radian;
	public Collider GetCollider;
	public int timePass;
	public RigidBody()
	{
		this.setPosition(0.0f,0.0f);
		this.setSize(0,0);
		this.setOrigin(0.0f, 0.0f);
		this.setScale(1.0f,1.0f);
		this.setAbsVelocity(0.0f);
		this.setAbsForce(0.0f);
		this.setDirection(1.0f,0.0f);
		this.setMass(1.0f);
		this.setRotation(0.0f);
		this.timePass = 0;
		this.setRigidInfluence(true);
		this.GetCollider = new Collider(this);
	}
	public void setMass(double Mass)
	{
		this.mass = Mass;
	}
	public double getMass()
	{
		return this.mass;
	}
	public void setSize(int width, int height)
	{
		if(this.size == null)
			this.size = new Vector2i(width,height);
		this.size.x = width;
		this.size.y = height;
		this.setCornerPoint();
	}
	public void setSize(Vector2i dim)
	{
		this.setSize(dim.x, dim.y);
	}
	public Vector2i getSize()
	{
		return this.size;
	}
	public void setPosition(float X, float Y)
	{
		if(this.position == null)
			this.position = new Vector2f(X,Y);
		this.position.x = X;
		this.position.y = Y;
		this.setCornerPoint();
	}
	
	public void setPosition(Vector2f position)
	{
		this.setPosition(position.x,position.y);
	}
	private void setCornerPoint()
	{
		if(this.position != null && this.origin != null && this.size != null)
		{
			if(this.cornerPoint == null)this.cornerPoint = new Vector2i(0,0);
			this.cornerPoint.x = (int)(this.getPosition().x - this.getOrigin().x - this.getSize().x/2);
			this.cornerPoint.y = (int)(this.getPosition().y - this.getOrigin().y - this.getSize().y/2);
		}
	}
	public Vector2i getCornerPoint()
	{
		return this.cornerPoint;
	}
	public void setRotation(double setAngle)
	{
		this.angle = setAngle % 360;
		this.radian = Math.PI * angle / 180;
	}
	public void setScale(float factorX, float factorY)
	{
		if(this.scale == null)
			this.scale = new Vector2f(factorX,factorY);	
		this.scale.x = factorX;
		this.scale.y = factorY;
		this.setSize(this.size.x,this.size.y);
		this.setPosition(this.position.x*this.scale.x, this.position.y*this.scale.y);
		this.setOrigin(this.origin.x*this.scale.x, this.origin.y*this.scale.y);
	}
	public void setScale(Vector2f scale)
	{
		this.setScale(scale.x,scale.y);	
	}
	public void setOrigin(float offsetX, float offsetY)
	{
		if(this.origin == null)
			this.origin = new Vector2f(offsetX, offsetY);
		this.origin.x = offsetX - this.getSize().x/2 ;
		this.origin.y = offsetY - this.getSize().y/2 ;
		this.setCornerPoint();
	}
	public void setOrigin(Vector2f localPos)
	{
		this.setOrigin(localPos.x,localPos.y);
	}
	public void setDirection(float faceX, float faceY)
	{
		if(this.direction == null)
			this.direction = new Vector2f(faceX,faceY);
		this.direction.x = faceX;
		this.direction.y = faceY;
	}
	public void setDirection(Vector2f newDirection)
	{
		this.setDirection(newDirection.x, newDirection.y);
	}
	public void setVelocity(float speedX, float speedY)
	{
		if(this.velocity == null)
			this.velocity = new Vector2f(speedX,speedY);
		this.velocity.x = speedX;
		this.velocity.y = speedY;
		this.absVelocity = this.getAbsVelocity();
	}
	public void setVelocity(Vector2f speed)
	{
		this.setVelocity(speed.x,speed.y);
	}
	public void setAbsVelocity(float speed)
	{
		this.setVelocity((float)(speed * Math.cos(radian)),(float)(speed * Math.sin(radian)));
	}
	public void setForce(float forceX, float forceY)
	{
		if(this.force == null)
			this.force = new Vector2f(forceX, forceY);
		this.force.x = forceX;
		this.force.y = forceY;
		this.absForce = this.getAbsForce();
	}
	public void setForce(Vector2f F)
	{
		this.setForce(F.x,F.y);
	}
	public void setAbsForce(float Force)
	{
		this.setForce((float)(Force * Math.cos(radian)),(float)(Force * Math.sin(radian)));
	}
	public void setRigidInfluence(boolean b)
	{
		this.rigidSwitch = b;
	}
	public Vector2f getPosition()
	{
		return this.position;
	}
	public Vector2f getScale()
	{
		return this.scale;
	}
	public Vector2f getOrigin()
	{
		return this.origin;
	}
	public Vector2f getDirection()
	{
		return this.direction;
	}
	public Vector2f getCardVelocity()
	{
		return this.velocity;
	}
	public float getAbsVelocity()
	{
		return (float)Math.hypot(this.velocity.x, this.velocity.y);
	}
	public float getAbsForce()
	{
		return (float)Math.hypot(this.force.x, this.force.y);
	}
	public Vector2f getGravity()
	{
		return this.gravity;
	}
	public boolean isRigidEnabled()
	{
		return this.rigidSwitch;
	}
	public double getAngle()
	{
		return this.angle;
	}
	public double getRadian()
	{
		return this.radian;
	}
	public void move(float offsetX, float offsetY)
	{
		setPosition(this.position.x + offsetX, this.position.y + offsetY);
	}
	public void move(Vector2f offset)
	{
		this.setPosition(offset = (Vector2f) this.position.add(offset));
	}
	public void rotate(double setAngle)
	{
		setRotation(this.angle + setAngle);
	}
	public void scale(float factorX, float factorY)
	{
		this.setScale(this.scale.x + factorX, this.scale.y + factorY);
	}
	public void scale(Vector2f factor)
	{
		this.scale.x = factor.x;
		this.scale.y = factor.y;
	}
	public void Update(float delay, float friction, RigidBody other, float push)
	{
		if(this.isRigidEnabled())
		{
			float deltaT = delay/1000;
			if(this.velocity.x > friction)	this.velocity.x -= friction*Math.cos(this.radian);
			else if(this.velocity.x < -friction) this.velocity.x -= friction*Math.cos(this.radian);
			else this.velocity.x = 0;
			this.velocity.y -= this.gravity.y;
			this.radian = Math.acos((double)(this.velocity.x/this.getAbsVelocity()));
			float dx = this.velocity.x * deltaT;
			float dy = 0.5f * this.gravity.y * (float)(Math.pow(deltaT, 2)) - this.velocity.y * deltaT;
			this.move(dx,dy);
			if(other != null)
			if(other.GetCollider.CheckCollision(this, push) && other.isRigidEnabled())
			{
				//this.OnCollision();
				this.timePass = 0;
				friction = 0;
			}
		}
	}
	public void OnCollision()
	{
		if(this.direction.x < 0.0f)
		{
			//collision on the left
			this.setAbsVelocity(0.0f);
		}
		else if (this.direction.x > 0.0f)
		{
			//collision of the right
			this.setAbsVelocity(0.0f);
		}
		if (this.direction.y < 0.0f)
		{
			//collision on the bottom
			this.setAbsVelocity(0.0f);
		}
		else if (this.direction.y > 0.0f)
		{
			//collision on the top
			this.setAbsVelocity(0.0f);
		}
	}
}