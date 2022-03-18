package breakout;

// TODO: implement, document
//Some DONE
//miss to implement tick
// I have establish the ball motion
//miss the bounce and other tick checks
public class BreakoutState {
	private BallState[] balls;
	private BlockState[] blocks;
	private Point bottomRight;
	private PaddleState paddle;
	
	
	//Need defensive programming 
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.paddle=paddle;
		this.balls=balls;
		this.bottomRight=bottomRight;
		this.blocks=blocks;
		
	}
	
	public BallState[] getBalls() {
		return balls;
	}

	public BlockState[] getBlocks() {
		return blocks;
	}

	public PaddleState getPaddle() {
		return paddle;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void tick(int paddleDir) {
		for (BallState ball:balls) {
			 Point positionBall=ball.getCenter().plus(ball.getVelocity());
			 ball.setPosition(positionBall);
			 //Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
			 if(positionBall.getX()== (bottomRight.getX()-ball.getSize().getX())||positionBall.getX()== ball.getSize().getX()||positionBall.getY()== ball.getSize().getY()) {
				 //v' = v - (2(v . d)/(d . d)) d
			 }
			 //Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
			 if(positionBall.getX()==(bottomRight.getY()-ball.getSize().getX())) {
				 ball=null;
			 }
		};
		
		
	}

	public void movePaddleRight() {
		//tr stands for TOP-Right
		Point value=this.paddle.getPosition().plus(new Vector(40,0));
		Point tr= this.paddle.getPosition().plus(this.paddle.getSize());
		if(tr.getX()<=50000) {
			this.paddle.setPosition(value);}
	}

	public void movePaddleLeft() {
		// tl stands for TOP-Left
		Point value=this.paddle.getPosition().minus(new Vector(40,0));
		Point tl= this.paddle.getPosition().minus(this.paddle.getSize());
		if(tl.getX()>=0) {
			this.paddle.setPosition(value);}
	}
	
	public boolean isWon() {
		boolean value=false;
		if (blocks==null) {
			value=true;
		}
		
		return value;
	}

	public boolean isDead() {
		boolean value=false;
		if (balls==null) {
			value=true;
		}
		return value;
	}
}
