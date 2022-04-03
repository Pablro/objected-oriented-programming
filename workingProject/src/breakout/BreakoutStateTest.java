package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BreakoutStateTest {
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
	//Velocity and Position
	/*
	 * an important statement: coordinates of the ball are not 100% accurate
	 * but has good precision. This is that if the program is run 100 times
	 * with the same conditions, the states regarding their position within the same unit time wont be exactly the same
	 * but the dispersion between these will be relatively small. 
	 */
	@Test
	void test_positionCoordinates_of_GameView() {
		BreakoutState exposingSetters= GameMap.createStateFromDescription(Map1);
		for (int i=0; i<300;i++) {
			exposingSetters.tick(1);
			if(i==299) {
				exposingSetters.getBalls()[0].setVelocity(new Vector(3,-3));
			}
		}
		assertEquals(new Point(24000,17098),exposingSetters.getBalls()[0].getCenter());
		

	}
	@Test
	/*
	 * the alteration result in a playable condition: not make the program crash
	 * but it will introduce a displacement of the previous condition
	 * Could lead to the customer insatisfaction.
	 */
	void test_insignificant_velocity_alteration() {
		BreakoutState exposingSetters= GameMap.createStateFromDescription(Map1);
		for (int i=0; i<301;i++) {
			exposingSetters.tick(1);
			if(i==299) {
				assertEquals(new Point(24000,17098),exposingSetters.getBalls()[0].getCenter());
				exposingSetters.getBalls()[0].setVelocity(new Vector(3,-3));
			}
			if(i==300) {
				assertEquals(new Point(24003,17095),exposingSetters.getBalls()[0].getCenter());
				
			}
		}
		
	}
	/*
	 * the alteration of the velocity results in a significant and detectable condition;
	 * It can be observe that the limits of a playable coordinate are being evaluated,
	 * if they are break the precondition that enforce the ball in the field comes in (in this case).
	 */
	@Test
	void test_significant_velocity_alteration(){
		BreakoutState exposingSetters= GameMap.createStateFromDescription(Map1);
		for (int i=0; i<301;i++) {
			exposingSetters.tick(1);
			if(i==299) {
				//update this value
				assertEquals(new Point(24000,17098),exposingSetters.getBalls()[0].getCenter());
				exposingSetters.getBalls()[0].setVelocity(new Vector(60000,30000));
			}
			if(i==300) {
				assertEquals(new Point(24003,17095),exposingSetters.getBalls()[0].getCenter());
				
			}
		}
		
	}
	@Test
	void testing_objects_breakability() {
		BreakoutState exposingObjects= GameMap.createStateFromDescription(Map1);
		PaddleState paddleTryingToBeModified=exposingObjects.getPaddle();
		
		assertEquals(exposingObjects.getBalls()[0]==null, exposingObjects.getBalls()[0]);
		assertEquals(exposingObjects.getBlocks()[2]=null, exposingObjects.getBlocks()[2]);
		assertEquals(paddleTryingToBeModified=null,exposingObjects.getPaddle());
	}
	@Test
	void test_movingpaddle() {
		BreakoutState movingPaddle= GameMap.createStateFromDescription(Map1);
		for (int i=0; i<301;i++) {
			Point paddlebefore=movingPaddle.getPaddle().getPosition();
			movingPaddle.movePaddleRight();
			assertEquals(paddlebefore,movingPaddle.getPaddle().getPosition());
			assertEquals(paddlebefore,movingPaddle.getPaddle().getPosition().minus(new Vector(40,0)));
			movingPaddle.tick(1);}

	}
	/*
	 * If we run the simulation with code coverage. It can be observe that in 50,000 iterations
	 * the ball bounce only blocks. With 50000 iterations the code approximately has a 50% coverage.
	 */
	@Test 
	void test_game_coverage() {
		BreakoutState gameCodeCoverage= GameMap.createStateFromDescription(Map2);
		for(int i=1;i<50000;i++) {
			gameCodeCoverage.tick(1);
		}
	}
}

