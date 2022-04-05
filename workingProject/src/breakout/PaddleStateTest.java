package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaddleStateTest {

	@Test
	void test() {
		int HEIGHT = 30000;
		int WIDTH = 50000;
		int BLOCK_LINES = 9;
		int BLOCK_COLUMNS = 10;
		
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = new Point(0,0).plus(size);
		
		PaddleState myPaddle1= PaddleState.valueOf(center,size);
		//PaddleState myPaddle2= PaddleState.valueOf(null,size);
		//PaddleState myPaddle3= PaddleState.valueOf(center,null);
		//PaddleState myPaddle4= PaddleState.valueOf(center,new Vector(-10,-10));
		//PaddleState myPaddle5= PaddleState.valueOf(new Point(-100,-100),size);
		//PaddleState myPaddle6= PaddleState.valueOf(new Point(100000,100000),size);
		
		PaddleState myPaddle10= PaddleState.valueOf(new Point(5000,5000),new Vector(1000,1000));
		assertEquals(new Point(5000,5000),myPaddle10.getPosition());
		assertEquals(new Vector(1000,1000), myPaddle10.getSize());
		
		//myPaddle10.setPosition(null);
		//myPaddle10.setPosition(new Point(-100,-100));
		//myPaddle10.setPosition(new Point(100000,100000));
		myPaddle1.setPosition(new Point(2000,2000));
		assertEquals(new Point(2000,2000),myPaddle1.getPosition());
		
		
	}

}
