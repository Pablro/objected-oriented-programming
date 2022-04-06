package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaddleStateTest {

	@Test
	void testInvalidArgumentsValueOf() {
		int HEIGHT = 30000;
		int WIDTH = 50000;
		int BLOCK_LINES = 9;
		int BLOCK_COLUMNS = 10;
		
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = new Point(0,0).plus(size);
		//Valid Argument
		PaddleState myPaddle1= PaddleState.valueOf(center,size);
		//Invalid Argument
		//PaddleState myPaddle2= PaddleState.valueOf(null,size);
		//PaddleState myPaddle3= PaddleState.valueOf(center,null);
		//PaddleState myPaddle4= PaddleState.valueOf(center,new Vector(-10,-10));
		//PaddleState myPaddle5= PaddleState.valueOf(new Point(-100,-100),size);
		//PaddleState myPaddle6= PaddleState.valueOf(new Point(100000,100000),size);
	}
	@Test
	void testUpdatingValues() {
		int HEIGHT = 30000;
		int WIDTH = 50000;
		int BLOCK_LINES = 9;
		int BLOCK_COLUMNS = 10;
		//Correct Arguments
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		PaddleState myPaddle10= PaddleState.valueOf(new Point(5000,5000),size);
		assertEquals(new Point(5000,5000),myPaddle10.getPosition());
		myPaddle10=myPaddle10.getNewPosition(new Point(3000,3000));
		assertEquals(new Point(3000,3000),myPaddle10.getPosition());
		//Incorrect Arguments
		//myPaddle10.getNewPosition(null);
		//myPaddle10.getNewPosition(new Point(-100,-100));
		//myPaddle10.getNewPosition(new Point(100000,100000));

		
		
	}

}
