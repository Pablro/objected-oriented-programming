package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlockStateTest {

	@Test
	void testValueOf() {
		int HEIGHT = 30000;
		int WIDTH = 50000;
		int BLOCK_LINES = 9;
		int BLOCK_COLUMNS = 10;
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS-70,HEIGHT/BLOCK_LINES-70);
		Point blockTL = new Point (20,20);
		Point blockBR = blockTL.plus(size);

		//Correct Argument
		BlockState myBlock1 =BlockState.valueOf(blockTL,blockBR,size);
		//Incorrect Arguments
		//BlockState myBlock2 =BlockState.valueOf(new Point(-20,-30),blockBR,size);
		//BlockState myBlock3 =BlockState.valueOf(blockTL,new Point (-70,-100),size);
		//BlockState myBlock4 =BlockState.valueOf(null,blockBR,size);
		//BlockState myBlock5 =BlockState.valueOf(blockTL,null,size);
		//BlockState myBlock6 =BlockState.valueOf(blockTL,blockBR,null);
		//BlockState myBlock7 =BlockState.valueOf(new Point (60000,60000),blockBR,size);
		//BlockState myBlock8 =BlockState.valueOf(blockTL,new Point (60000,60000),size);
		//BlockState myBlock9 =BlockState.valueOf(blockTL,blockBR,new Vector (-30,-30));
		//BlockState myBlock10 =BlockState.valueOf(new Point (-250,-250),new Point (-250,-250),size);
		}
	@Test
	void testUpdatingValues() {
		
		int HEIGHT = 30000;
		int WIDTH = 50000;
		int BLOCK_LINES = 9;
		int BLOCK_COLUMNS = 10;
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS-70,HEIGHT/BLOCK_LINES-70);
		//Correct verification and arguments
		BlockState myBlock10 = BlockState.valueOf(new Point(100,100),new Point(2100,2100),size);
		assertEquals(new Point(1100,1100),myBlock10.getPosition());
		assertEquals(size,myBlock10.getSize());
		assertEquals(new Point(100,100),myBlock10.getBlockTL());
		assertEquals(new Point(2100,2100),myBlock10.getBlockBR());
		
	}

}
