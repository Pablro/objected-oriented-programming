package breakout;

public class BallState {
	// TODO: implement
	//DONE-check it
	private Point center;
	private int diameter;
	private Vector velocity;
	BallState (Point center,int diameter,Vector velocity){
		this.center=center;
		this.diameter=diameter;
		this.velocity=velocity;
		
	};
	
	public Point getCenter() {
		return center;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	public Vector getSize() {
		Vector size= new Vector(this.diameter/2,this.diameter/2);
		return size;
	};
	public void setPosition(Point position) {
		this.center=position;
	};
	public void setVelocity(Vector velocity) {
		this.velocity=velocity;
	}
}
