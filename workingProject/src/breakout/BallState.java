package breakout;

public class BallState {
	// TODO: implement
	//DONE-check it
	private Point center1;
	private int diameter1;
	private Vector velocity1;
	BallState (Point center,int diameter,Vector velocity){
		this.center1=center;
		this.diameter1=diameter;
		this.velocity1=velocity;
		
	};
	
	public Point getCenter() {
		return center1;
	}
	
	public Vector getVelocity() {
		return velocity1;
	}
	public Vector getSize() {
		Vector size1= new Vector(this.diameter1/2,this.diameter1/2);
		return size1;
	};
	public void setPosition(Point position) {
		this.center1=position;
	};
	public void setVelocity(Vector velocity) {
		this.velocity1=velocity;
	}
}
