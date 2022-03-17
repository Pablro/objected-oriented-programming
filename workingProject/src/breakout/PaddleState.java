package breakout;

public class PaddleState {
	// TODO: implement
	//DONE-check it
	private Point position1;
	private Vector size1;
	public PaddleState (Point position, Vector size){
		this.position1=position;
		this.size1=size;
	};
	public Point  getPosition() {
		return position1;
	};
	public Vector getSize() {
		return size1;
	}
	public void setPosition(Point position) {
		this.position1=position;
	};
}
