package breakout;

public class BallState {
	// TODO: implement
	//DONE-check it
	private Point center;
	private Vector velocity;
	private Vector size;
	public BallState (Point center,int diameter,Vector velocity){
		this.center=center;
		this.velocity=velocity;
		this.size=new Vector(diameter/2,diameter/2);
		
	};
	
	public Point getCenter() {
		return center;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	public Vector getSize() {
		return size;
	};
	public void setPosition(Point position) {
		this.center=position;
	};
	public void setVelocity(Vector velocity) {
		this.velocity=velocity;
	}
	public void setSize(Vector size) {
		this.size=size;
	}

}
