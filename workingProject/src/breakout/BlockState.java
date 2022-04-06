package breakout;



/**
 * 
 * Each instance of this class represents a block defined by a top-left point and a bottom-right point.The width and the height of the block is the block's size.
 * 
 * @immutable
 * 
 * Abstract state invariants
 * 
 * @invar | getSize() != null
 * @invar | getBlockTL() != null
 * @invar | getBlockBR() != null
 * @invar | getSize().getX() >= 0 && getSize().getY() >= 0
 * @invar | ((getBlockBR().getX()<50000 && getBlockBR().getY()<30000)&&(getBlockTL().getX()<50000 && getBlockTL().getY()<30000)&& (getBlockTL().getY()>0 && getBlockTL().getX()>0)&& (getBlockBR().getY()>0 && getBlockBR().getX()>0))||(getBlockTL().equals(new Point(-250,-250))&&getBlockBR().equals(new Point(-250,-250)))
 * @invar | getSize().getX() == getBlockBR().getX() - getBlockTL().getX() 
 * @invar | getSize().getY() == getBlockBR().getY() - getBlockTL().getY() 
 */
public class BlockState {
	// TODO: implement
	//DONE-check it

	
	/**
	 * Representation state invariants
	 * 
	 * @invar | size != null
	 * @invar | blockTL != null
	 * @invar | blockBR != null
	 * @invar | size.getX() >= 0 && size.getY() >= 0
	 * @invar | ((blockBR.getX()<50000 && blockBR.getY()<30000)&&(blockTL.getX()<50000 && blockTL.getY()<30000)&& (blockTL.getY()>0 && blockTL.getX()>0)&& (blockBR.getY()>0 && blockBR.getX()>0))&&((blockTL.equals(new Point(-250,-250)))&&(blockBR.equals(new Point(-250,-250))))
	 * @invar | size.getX() == blockBR.getX() - blockTL.getX() 
	 * @invar | size.getY() == blockBR.getY() - blockTL.getY() 
	 * 
	 * @representationObject
	 */
	private final Point blockTL;
	private final Point blockBR;
	private final Vector size;

	
	private BlockState (Point blockTL, Point blockBR,Vector size){
		this.blockBR=blockBR;
		this.blockTL=blockTL;
		this.size=size;

	};
	
	
	 

	//Factory method
	/**
	 * Returns a block object defined by a top-left point and a bottom-right point.The width and the height of the block is the block's size.
	 * 
	 * @throws | blockTL == null
	 * @throws | blockBR == null
	 * @throws | size == null
	 * @throws | size.getX() < 0 && size.getY() < 0
	 * @throws |((blockBR.getX()>=50000 || blockBR.getY()>=30000)||(blockTL.getX()>=50000 || blockTL.getY()>=30000)|| (blockTL.getY()<=0 || blockTL.getX()<=0)|| (blockBR.getY()<=0 || blockBR.getX()<=0))&&(!(blockTL.equals(new Point(-250,-250)))&&!(blockBR.equals(new Point(-250,-250))))
	 * @throws | size.getX() != blockBR.getX() - blockTL.getX() 
	 * @throws | size.getY() != blockBR.getY() - blockTL.getY()
	 * @post | result != null
	 * @post | result.getBlockTL().equals(blockTL) && result.getBlockBR().equals(blockBR) && result.getSize().equals(size)
	 * 
	 */
	public static BlockState valueOf(Point blockTL, Point blockBR,Vector size) {
		if(blockTL == null ||blockBR == null ||size == null) {
			throw new NullPointerException("Invalid argument");
		}
		if(size.getX() < 0 && size.getY() < 0) {
			throw new AssertionError("Invalid argument");
		}
		if(((blockBR.getX()>=50000 || blockBR.getY()>=30000)||(blockTL.getX()>=50000 || blockTL.getY()>=30000)|| (blockTL.getY()<=0 || blockTL.getX()<=0)|| (blockBR.getY()<=0 || blockBR.getX()<=0))&&(!(blockTL.equals(new Point(-250,-250)))&&!(blockBR.equals(new Point(-250,-250))))) {
			throw new AssertionError("Invalid argument");
		}
		if( size.getX() != blockBR.getX() - blockTL.getX()) {
			throw new AssertionError("Invalid argument");
		}
		if(size.getY() != blockBR.getY() - blockTL.getY()) {
			throw new AssertionError("Invalid argument");
		}
		Point blockTLCopy=new Point(blockTL.getX(),blockTL.getY());
		Point blockBRCopy=new Point(blockBR.getX(),blockBR.getY());
		Vector sizeCopy=new Vector(size.getX(),size.getY());
		return new BlockState(blockTLCopy, blockBRCopy, sizeCopy);
	}
	
	
	
	/**
	 * 
	 * @inspects | this
	 */
	public Point  getPosition() {
		int blockcenterx= (blockBR.getX()+blockTL.getX())/2;
		int blockcentery= (blockTL.getY()+blockBR.getY())/2;
		Point center=new Point (blockcenterx,blockcentery);
		return center;
	};
	
	/**
	 * 
	 * @inspects | this
	 */
	public Vector getSize() {
		 return size;
	}
	/**
	 * 
	 * @inspects | this
	 */
	public Point getBlockTL() {
		return blockTL;
	}
	/**
	 * 
	 * @inspects | this
	 */
	public Point getBlockBR() {

		return blockBR;
	}
	/**
	 * conditions are evaluate in valueOf
	 * @creates |result
	 * 
	 * 
	 */
	public BlockState setBlockTLBR(Point TL, Point BR) {
		Point TLCopy=new Point(TL.getX(),TL.getY());
		Point BRCopy=new Point(BR.getX(),BR.getY());
		return BlockState.valueOf(TLCopy, BRCopy, getSize());
	}
	
	

}
