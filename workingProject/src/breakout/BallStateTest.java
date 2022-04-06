package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BallStateTest {

	
 
	@Test
	void testInvalidArgumentsValueOfA() {
		//Incorrect Arguments
		//BallState ballstate1=BallState.valueOfA(new Point(0,0),700,new Vector(5,7));
		//BallState ballstate2=BallState.valueOfA(new Point(30,70),700,new Vector(12,7));
		//BallState ballstate3=BallState.valueOfA(new Point(30,70),700,null);
		//BallState ballstate4=BallState.valueOfA(null,700,new Vector(5,7));
		//BallState ballstate5=BallState.valueOfA(new Point(70,70),300,new Vector(5,7));
		//Correct Argument
		BallState ballstate6=BallState.valueOfA(new Point(70,70),700,new Vector(5,7));
	}
	@Test
	void testInvalidArgumentsValueOfB() {
		//Incorrect Arguments
		//BallState ballstate1=BallState.valueOfB(new Point(0,0),new Vector(700/2,700/2),new Vector(5,7));
		//BallState ballstate2=BallState.valueOfB(new Point(30,70),new Vector(700/2,700/2),new Vector(12,7));
		//BallState ballstate3=BallState.valueOfB(new Point(30,70),new Vector(700/2,700/2),null);
		//BallState ballstate4=BallState.valueOfB(null,new Vector(700/2,700/2),new Vector(5,7));
		//BallState ballstate5=BallState.valueOfB(new Point(70,70),new Vector(300/2,300/2),new Vector(5,7));
		
		//Correct Argument
		BallState ballstate6=BallState.valueOfB(new Point(70,70),new Vector(700/2,700/2),new Vector(5,7));
		
	}
	@Test
	/*
	 * Note that there is not test for velocity. No way to control this limit from the BallState.
	 * But if the velocity is modified significantly, it can be observed in the BreakoutStateTest how the
	 * BreakoutState class controls this modification.
	 */
	void testUpdatingValues() {
		BallState ballState= BallState.valueOfA(new Point(30,70),700,new Vector(5,7));
		ballState=ballState.getNewVelocity(new Vector(3,7));
		assertEquals(new Vector(3,7),ballState.getVelocity());
		ballState=ballState.getNewSize(new Vector(0,0));
		assertEquals(new Vector(0,0),ballState.getSize());
		ballState=ballState.getNewPosition(new Point(80,100));
		assertEquals(new Point(80,100),ballState.getCenter());
		ballState=ballState.getNewPosition(new Point(-250,-250));
		assertEquals(new Point(-250,-250),ballState.getCenter());
	}

}
