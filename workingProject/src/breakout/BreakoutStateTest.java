package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BreakoutStateTest {
	/*
	 * This part contains defensive programming and use the submissionTestSuite.class for the IllegalArgumentExceptions.
	 */
	public static final String Map1 = """
##########
##########
##########
##########
    o 

    =

""";
	public static final String Map2 = """
##########
#####o####
##########
##########
     

    =

""";

	@Test
	/*
	 * the alteration of the velocity results in a playable condition which means that will not make the program crash
	 * but it will introduce a displacement of the previous condition (bugs)
	 */
	void test_insignificant_velocity_alteration() {
		BreakoutState state1= GameMap.createStateFromDescription(Map1);
		Point firstPosition=state1.getBalls()[0].getCenter();
		//unregulate change
		BallState stateWithNewVelocity=state1.getBalls()[0].getNewVelocity(new Vector(8,10));
		// regulate change | suppose initial scenario of the ball (initial velocity) and it does not bounce anything (same velocity)
		BallState stateWithCorrectVelocity=state1.getBalls()[0].getNewVelocity(new Vector(5,7));
		
		/* If the velocity introduce is reasonbale and detectable but do not correspond
		 * the logical update of the dynamic of the game then this introduce a bug*/ 
		//BallState tickupdate1= state1.getBalls()[0].getNewPosition(firstPosition.plus(stateWithNewVelocity.getVelocity()));
		//BallState tickupdate2= state1.getBalls()[0].getNewPosition(firstPosition.plus(stateWithCorrectVelocity.getVelocity()));
		//assertEquals(tickupdate1.getCenter(),tickupdate2.getCenter());
		
		
	}
	/*
	 * the alteration of the velocity results in a significant and detectable condition;
	 * It can be observe that the limits of a playable coordinate are being evaluated,
	 * if the precondition that detects the ball in the field are break,it will result in an Error.
	 */
	@Test
	void test_significant_velocity_alteration(){
		/*
		 * As we can not expose the code, the following lines of code will be suffice for the proof that BreakoutState class regulates velocity by regulating the position by which is affected
		 * after bounce an object or setting a new unreasonable velocity with getNewVelocity. 
		 */
		BreakoutState state1= GameMap.createStateFromDescription(Map1);
		Point firstPosition=state1.getBalls()[0].getCenter();
		BallState stateWithNewVelocity=state1.getBalls()[0].getNewVelocity(new Vector(60000,300));
		/* we can see that this statement will crash the program as it does not comply with the precondition of position. 
		Meaning that updating it with an outrageous velocity will be control by the BreakoutState by the position effect.*/ 
		//BallState tickupdate= state1.getBalls()[0].getNewPosition(firstPosition.plus(stateWithNewVelocity.getVelocity()));
		
	}
	@Test
	void testing_objects_breakability() {
		BreakoutState exposingObjects= GameMap.createStateFromDescription(Map1);
		//No exposure leakage
		//assertEquals(exposingObjects.getBalls()[0]=null, exposingObjects.getBalls()[0]);
		//assertEquals(exposingObjects.getBlocks()[2]=null, exposingObjects.getBlocks()[2]);

	}
	@Test
	void test_movingpaddle() {
		BreakoutState movingPaddle= GameMap.createStateFromDescription(Map1);
		for (int i=0; i<301;i++) {
			Point paddlebefore=movingPaddle.getPaddle().getPosition();
			movingPaddle.movePaddleRight();
			// now we move from right to left to check previous state
			assertEquals(paddlebefore,movingPaddle.getPaddle().getPosition().minus(new Vector(10,0)));
			//wrong unit difference
			//assertEquals(paddlebefore,movingPaddle.getPaddle().getPosition().minus(new Vector(40,0)));
			//wrong direction (we are moving to the right but the following code assert the oposite movement of the paddle)
			//assertEquals(paddlebefore,movingPaddle.getPaddle().getPosition().plus(new Vector(10,0)));
			movingPaddle.tick(1);}

	}
	/*
	 * If we run the simulation with code coverage. It can be observe that in 50,000 iterations
	 * the ball bounce only blocks. With 50000 iterations the code approximately has a 50% coverage.
	 */
	@Test 
	void test_game_coverage() {
		//BreakoutState gameCodeCoverage= GameMap.createStateFromDescription(Map2);
		//for(int i=1;i<50000;i++) {
			//gameCodeCoverage.tick(1);
		//}
	}

}

