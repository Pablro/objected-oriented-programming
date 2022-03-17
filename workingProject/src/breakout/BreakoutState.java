package breakout;

// TODO: implement, document
//Some DONE
//miss to implement tick
// I have establish the ball motion
//miss the bounce and other tick checks
public class BreakoutState {
	private BallState[] balls1;
	private BlockState[] blocks1;
	private Point bottomRight1;
	private PaddleState paddle1;
	
	
	//Need defensive programming 
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.paddle1=paddle;
		this.balls1=balls;
		this.bottomRight1=bottomRight;
		this.blocks1=blocks;
		
	}
	
	public BallState[] getBalls() {
		return balls1;
	}

	public BlockState[] getBlocks() {
		return blocks1;
	}

	public PaddleState getPaddle() {
		return paddle1;
	}

	public Point getBottomRight() {
		return bottomRight1;
	}

	public void tick(int paddleDir) {
		for (BallState ball:balls1) {
			 Point position1=ball.getCenter().plus(ball.getVelocity());
			 ball.setPosition(position1);



		};
		
		
	}

	public void movePaddleRight() {
		//tr stands for TOP-Right
		Point value=this.paddle1.getPosition().plus(new Vector(40,0));
		Point tr= this.paddle1.getPosition().plus(this.paddle1.getSize());
		if(tr.getX()<=50000) {
			this.paddle1.setPosition(value);}
	}

	public void movePaddleLeft() {
		// tl stands for TOP-Left
		Point value=this.paddle1.getPosition().minus(new Vector(40,0));
		Point tl= this.paddle1.getPosition().minus(this.paddle1.getSize());
		if(tl.getX()>=0) {
			this.paddle1.setPosition(value);}
	}
	
	public boolean isWon() {
		boolean value=false;
		if (blocks1==null) {
			value=true;
		}
		
		return value;
	}

	public boolean isDead() {
		boolean value=false;
		if (balls1==null) {
			value=true;
		}
		return value;
	}
}
