package Physics;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Texture extends JPanel {
	
	public Vector2i bkgd = null;
	public int player;
	public boolean changePlayer = false;
	public String windSpeed, score1 = "0", score2 = "0";
	public RigidBody ground, ball1, cannon1, ball2, cannon2, playerBall, playerCannon;
	public Polygon cannon1P, cannon2P;
	public Vector2f cannon1LegPoints, cannon2LegPoints;
	public float friction;
	public float push;
	public double radian = 0;
	public Timer timer;
	public Texture(int width, int height)
	{
		
		this.bkgd = new Vector2i(width,height);
		
		this.ground = new RigidBody();
		this.ground.setSize(bkgd.x,200);
		this.ground.setPosition(600,bkgd.y-100);
		this.ground.setOrigin(this.ground.getSize().x/2,100);
		this.ground.setRigidInfluence(false);
		
		this.cannon1 = new RigidBody();
		this.cannon1.setSize(100, 30);
		this.cannon1.setOrigin(30, 20);
		this.cannon1.setPosition((float)(50+Math.random()*200),this.ground.getCornerPoint().y-this.cannon1.getSize().y/2-10); //10 because the rotation of cannon2 (180deg) inverts the origin of the drawing
		this.cannon1LegPoints = new Vector2f((cannon1.getPosition().x +cannon1.getOrigin().x), (int)(cannon1.getPosition().y + cannon1.getOrigin().y));
		cannon1P = constructPolygon(cannon1);
		
		this.cannon2 = new RigidBody();
		this.cannon2.setSize(100, 30);
		this.cannon2.setOrigin(30, 10);
		this.cannon2.setPosition((float)(1150-Math.random()*200),this.ground.getCornerPoint().y-this.cannon2.getSize().y/2);
		this.cannon2.setRotation(180);
		this.cannon2LegPoints = new Vector2f((cannon2.getPosition().x +cannon2.getOrigin().x), (int)(cannon2.getPosition().y + cannon2.getOrigin().y));
		cannon2P = constructPolygon(cannon2);
		
		this.ball1 = null;
		this.ball2 = null;
				
		timer = new Timer(1000/30, new TimerListener());
		timer.start();
		friction = (float) (Math.random()*2.0f + Math.random()*20.0f);
		friction =  (float) (Math.round(friction*100)/100.0d);
		windSpeed = String.valueOf(friction);
		push = 20.0f;
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//cannon balls
		
		//blue sky
		Graphics2D g2d = (Graphics2D) g;
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint( panelWidth / 2 , 0 , new Color(126,192,238) , panelWidth / 2 , panelHeight , new Color(126,192,238).brighter() );
        
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
		
        //cannons
        gp = new GradientPaint(50,550,Color.decode("#ff463f"), 50, 600, Color.decode("#d11812"));
        g2d.setPaint(gp);
		if(this.cannon1 != null)
		{
			g2d.fillPolygon(cannon1P);
			g.setColor(Color.decode("#e0e035"));
			g.fillPolygon(new int[]{(int) cannon1LegPoints.x,(int) (cannon1LegPoints.x-40),(int) (cannon1LegPoints.x+20)}, new int[]{(int)cannon1LegPoints.y,(int)ground.getCornerPoint().y,(int)ground.getCornerPoint().y}, 3);
		}
		if(this.cannon2 != null)
		{
			g2d.setPaint(gp);
			g2d.fillPolygon(cannon2P);
			g.setColor(Color.decode("#e0e035"));
			g.fillPolygon(new int[]{(int) cannon2LegPoints.x,(int) (cannon2LegPoints.x-20),(int) (cannon2LegPoints.x+40)}, new int[]{(int)cannon2LegPoints.y,(int)ground.getCornerPoint().y,(int)ground.getCornerPoint().y}, 3);
		}
		//ground

        gp = new GradientPaint((float)this.ground.getPosition().x,(float)this.ground.getCornerPoint().y , new Color(44, 176, 55), (float)this.ground.getPosition().x,(float)this.ground.getCornerPoint().y+this.ground.getSize().y,new Color(44, 176, 55).brighter());
        g2d.setPaint(gp);
        g2d.fillRect(this.ground.getCornerPoint().x,this.ground.getCornerPoint().y, this.ground.getSize().x, this.ground.getSize().y);
        
		g.setColor(Color.GRAY);
		//ball
		if(this.playerBall != null)
			g.fillOval(this.playerBall.getCornerPoint().x,this.playerBall.getCornerPoint().y, this.playerBall.getSize().x, this.playerBall.getSize().y);
		
		//WindSpeed
		g.setColor(Color.WHITE);
		Font a = new Font("Courier New", Font.BOLD, 24);
		g.setFont(a);
		g.drawString("Wind Speed: "+windSpeed, getWidth()-250, 50);
		
		//Scores
		a = new Font("Courier New", Font.BOLD, 18);
		g.setFont(a);
		g.drawString("Player1: "+score1+"pts", 0, getHeight()-50);
		g.drawString("Player2: "+score2+"pts", getWidth()-200, getHeight()-50);
	}
	public Polygon constructPolygon(RigidBody body)
	{
		Polygon poly = new Polygon();
		Vector2i globalOrigin = new Vector2i((int)(body.getPosition().x +body.getOrigin().x), (int)(body.getPosition().y + body.getOrigin().y));
		Vector2i deltaD = new Vector2i((int)body.getPosition().x - body.getCornerPoint().x,(int) body.getPosition().y-body.getCornerPoint().y);
		
		double hypot = Math.hypot((double)deltaD.x, (double)deltaD.y);
		double radOC = Math.acos(deltaD.x/hypot);
		double deltaRad = Math.PI - radOC + body.getRadian();
		int polyX = globalOrigin.x + (int)(hypot*Math.cos(deltaRad)), polyY = globalOrigin.y + (int)(hypot*Math.sin(-deltaRad));
		
		poly.addPoint(polyX, polyY);
		poly.addPoint(polyX + (int)(body.getSize().x*Math.cos(body.getRadian())), polyY + (int)(body.getSize().x*Math.sin(-body.getRadian())));
		poly.addPoint(poly.xpoints[1] + (int)(body.getSize().y*Math.sin(Math.PI-body.getRadian())),poly.ypoints[1] + (int)(body.getSize().y*Math.cos(body.getRadian())));
		poly.addPoint( polyX + (int)(body.getSize().y*Math.sin(body.getRadian())), polyY + (int)(body.getSize().y*Math.cos(body.getRadian())));

		return poly;
	}
	public void ballRigid()
	{
		switch(player)
		{
		case 1:
			this.playerCannon = this.cannon2;
			break;
		case 2:
			this.playerCannon = this.cannon1;
		}
		boolean ballCannonColl = false;
		boolean ballGroundColl = false;
		
		if(playerBall != null)
		{
			this.playerBall.Update(timer.getDelay(), friction, this.ground, push);
			if(!this.playerBall.GetCollider.CheckCollision(this.playerCannon, push))
				this.playerBall.Update(timer.getDelay(), friction, this.playerCannon, push);
			else ballCannonColl = true;
			this.playerBall.Update(timer.getDelay(), friction, this.playerCannon, push);
			if(this.playerBall.GetCollider.CheckCollision(this.ground, push))
				ballGroundColl = true;
			if(ballCannonColl == true)
				{
					this.playerBall = null;
					if(this.playerCannon == this.cannon1){
						this.cannon1 = null;
					}
					else {
						this.cannon2 = null;
					}
					this.playerCannon = null;
				}
			else if(ballGroundColl == true || this.playerBall.getPosition().x>this.getWidth() || this.playerBall.getPosition().x<0)
				this.playerBall = null;
			
		}
		else {
			timer.stop();
			friction = (float) (Math.random()*2.0f + Math.random()*20.0f);
			windSpeed = String.valueOf(Math.abs(friction));
			
		}
	}
	class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			ballRigid();
			repaint();
		}
	}
}