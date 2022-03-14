package breakout;

// TODO: implement, document
//Some DONE
//miss to implement tick and move left-move right
public class BreakoutState {
	BallState[] balls1;
	BlockState[] blocks1;
	Point bottomRight1;
	PaddleState paddle1;
	
	
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
		
	}

	public void movePaddleRight() {
	}

	public void movePaddleLeft() {
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
