package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BallStateTest {
	
	
 
	@Test
	void test_invalid_arguments() {
		assertThrows(IllegalArgumentException.class,() -> new BallState(new Point(0,0),700,new Vector(5,7)));
		assertThrows(IllegalArgumentException.class,() -> new BallState(new Point(30,70),300,new Vector(5,7)));
		assertThrows(IllegalArgumentException.class,() -> new BallState(new Point(30,70),700,new Vector(12,7)));
		assertThrows(IllegalArgumentException.class,() -> new BallState(new Point(30,70),700,null));
		assertThrows(IllegalArgumentException.class,() -> new BallState(null,700,new Vector(5,7)));
	}
	@Test
	/*
	 * Note that there is not test for velocity. No way to limit this limit from the BallState.
	 * But if the velocity is modified significantly it can be observed in the BreakoutStateTest how the
	 * BreakoutState class controls this modification.
	 */
	void test_setter() {
		BallState ballState= new BallState(new Point(30,70),700,new Vector(5,7));
		assertThrows(IllegalArgumentException.class,() -> ballState.setVelocity(null));
		assertThrows(IllegalArgumentException.class,() -> ballState.setSize(null));
		assertThrows(IllegalArgumentException.class,() -> ballState.setPosition(null));
		assertThrows(IllegalArgumentException.class,() -> ballState.setVelocity(new Vector(-70000,10000)));
		assertThrows(IllegalArgumentException.class,() -> ballState.setSize(new Vector(700,700)));
		assertThrows(IllegalArgumentException.class,() -> ballState.setPosition(new Point(-30000,80000)));
		assertThrows(IllegalArgumentException.class,() -> ballState.setPosition(new Point(-300,-300)));
		
		ballState.setPosition(new Point(-250,-250));
		assertEquals(new Point(-250,-250),ballState.getCenter());
	}

}
