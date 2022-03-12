package breakout;

// TODO: implement, document
public class BreakoutState {

	private PaddleState paddle;
	private BallState[] balls;
	private Point bottomRight;
	private BlockState[] blocks;
	
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
		//TODO implement
	}

	public void movePaddleRight() {
		//TODO implement
	}

	public void movePaddleLeft() {
		//TODO implement
	}
	
	public boolean isWon() {
		//TODO implement
		return false;
	}

	public boolean isDead() {
		//TODO implement
		return false;
	}
}
