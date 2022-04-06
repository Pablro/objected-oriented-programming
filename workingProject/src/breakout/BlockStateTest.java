package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlockStateTest {

	@Test
	void test() {
		int HEIGHT = 30000;
		int WIDTH = 50000;
		int BLOCK_LINES = 9;
		int BLOCK_COLUMNS = 10;
		/*
		 * Check this it is not logical to me
		 */
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS-70,HEIGHT/BLOCK_LINES-70);
		Point blockTL = new Point (20,20);
		Point blockBR = blockTL.plus(size);
		System.out.println(blockBR);
		System.out.println(blockTL);
		
		BlockState myBlock1 =BlockState.valueOf(blockTL,blockBR,size);
		//BlockState myBlock2 =BlockState.valueOf(new Point(-20,-30),blockBR,size);
		//BlockState myBlock3 =BlockState.valueOf(blockTL,new Point (-70,-100),size);
		//BlockState myBlock4 =BlockState.valueOf(null,blockBR,size);
		//BlockState myBlock5 =BlockState.valueOf(blockTL,null,size);
		//BlockState myBlock6 =BlockState.valueOf(blockTL,blockBR,null);
		//BlockState myBlock7 =BlockState.valueOf(new Point (60000,60000),blockBR,size);
		//BlockState myBlock8 =BlockState.valueOf(blockTL,new Point (60000,60000),size);
		//BlockState myBlock9 =BlockState.valueOf(blockTL,blockBR,new Vector (-30,-30));
		
		//myBlock1.setBlockTL(null);
		//myBlock1.setBlockBR(null);
		//myBlock1.setBlockBR(new Point (-250,-250));
		//myBlock1.setBlockTL(new Point (-250,-250));
		//myBlock1.setBlockBR(new Point (10000,-100));
		//myBlock1.setBlockBR(new Point (100000,200));
		//myBlock1.setBlockTL(new Point (10000,-100));
		//myBlock1.setBlockTL(new Point (100000,200));
		
		/*
		BlockState myBlock10 = BlockState.valueOf(new Point(100,100),new Point(2100,2100),new Vector (2000,2000));
		assertEquals(new Point(1100,1100),myBlock10.getPosition());
		assertEquals(new Vector(2000,2000),myBlock10.getSize());
		assertEquals(new Point(100,100),myBlock10.getBlockTL());
		assertEquals(new Point(2100,2100),myBlock10.getBlockBR());
		
		myBlock10=myBlock10.setBlockTLBR(new Point(-250,-250), new Point(-250,-250));
		assertEquals(new Point(-250,-250),myBlock10.getBlockBR());
		assertEquals(new Point(-250,-250),myBlock10.getBlockTL());
		assertEquals(new Vector(0,0),myBlock10.getSize());
		myBlock10=myBlock10.setBlockTLBR(new Point(200,200), new Point(2200,2200));
		assertEquals(new Point(200,200),myBlock10.getBlockTL());
		assertEquals(new Point(2200,2200),myBlock10.getBlockBR());
		assertEquals(new Vector(2000,2000),myBlock10.getSize());
		*/
		BlockState illegal= BlockState.valueOf(null, null, null);
	}

}
