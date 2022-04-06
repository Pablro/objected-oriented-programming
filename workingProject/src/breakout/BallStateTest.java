package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BallStateTest {
	
	
 
	@Test
	void testInvalidArguments() {
		assertThrows(AssertionError.class,() -> BallState.valueOfA(new Point(0,0),700,new Vector(5,7)));
		assertThrows(AssertionError.class,() -> BallState.valueOfA(new Point(30,70),300,new Vector(5,7)));
		assertThrows(AssertionError.class,() -> BallState.valueOfA(new Point(30,70),700,new Vector(12,7)));
		assertThrows(NullPointerException.class,() ->BallState.valueOfA(new Point(30,70),700,null));
		assertThrows(NullPointerException.class,() -> BallState.valueOfA(null,700,new Vector(5,7)));
	}
	@Test
	/*
	 * Note that there is not test for velocity. No way to limit this limit from the BallState.
	 * But if the velocity is modified significantly it can be observed in the BreakoutStateTest how the
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
