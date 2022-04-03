package breakout;

public class PaddleState {
	// TODO: implement
	//DONE-check it
	private Point position;
	private Vector size;
	public PaddleState (Point position, Vector size){
		this.position=position;
		this.size=size;
	};
	public Point  getPosition() {
		return position;
	};
	public Vector getSize() {
		return size;
	}
	public void setPosition(Point position) {
		this.position=position;

	};
}
