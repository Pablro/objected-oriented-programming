package breakout;


//@invar | getSize().getX() == getBlockBR().getX() - getBlockTL().getX() || getSize().getX() == 0
//@invar | getSize().getY() == getBlockBR().getY() - getBlockTL().getY() || getSize().getY() == 0
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
 * 
 */
public class BlockState {
	// TODO: implement
	//DONE-check it
	// * @invar | size_copy.getX() == blockBR_copy.getX() - blockTL_copy.getX() ||size_copy.getX() == 0
     //* @invar | size_copy.getY() == blockBR_copy.getY() - blockTL_copy.getY() ||size_copy.getY() == 0
	 //* @invar | size.getX() == blockBR.getX() - blockTL.getX() || size.getX() == 0
	 //* @invar | size.getY() == blockBR.getY() - blockTL.getY() || size.getY() == 0
	/**
	 * Representation state invariants
	 * 
	 * @invar | size != null
	 * @invar | size_copy != null
	 * @invar | blockTL != null
	 * @invar | blockTL_copy != null
	 * @invar | blockBR != null
	 * @invar | blockBR_copy != null
	 * @invar | size.getX() >= 0 && size.getY() >= 0
	 * @invar | size_copy.getX() >= 0 && size_copy.getY() >= 0
	 * @invar | ((blockBR.getX()<50000 && blockBR.getY()<30000)&&(blockTL.getX()<50000 && blockTL.getY()<30000)&& (blockTL.getY()>0 && blockTL.getX()>0)&& (blockBR.getY()>0 && blockBR.getX()>0))||(blockTL.equals(new Point(-250,-250))&&blockBR.equals(new Point(-250,-250)))
	 * @invar | ((blockBR_copy.getX()<50000 && blockBR_copy.getY()<30000)&&(blockTL_copy.getX()<50000 && blockTL_copy.getY()<30000)&& (blockTL_copy.getY()>0 && blockTL_copy.getX()>0)&& (blockBR_copy.getY()>0 && blockBR_copy.getX()>0))||(blockTL_copy.equals(new Point(-250,-250))&&blockBR_copy.equals(new Point(-250,-250)))
	 * 
	 * @representationObject
	 */
	private final Point blockTL;
	private final Point blockBR;
	private final Vector size;
	private Point blockTL_copy;
	private Point blockBR_copy;
	private Vector size_copy;
	
	private BlockState (Point blockTL, Point blockBR,Vector size){
		this.blockBR=blockBR;
		this.blockTL=blockTL;
		this.size=size;

		this.blockBR_copy=this.blockBR;
		this.blockTL_copy=this.blockTL;
		this.size_copy=this.size;
		
	};
	
	
	// * @pre | size.getX() == blockBR.getX() - blockTL.getX() || size.getX() == 0
	// * @pre | size.getY() == blockBR.getY() - blockTL.getY() || size.getY() == 0

	//Factory method
	/**
	 * Returns a block object defined by a top-left point and a bottom-right point.The width and the height of the block is the block's size.
	 * 
	 * @pre | blockTL != null
	 * @pre | blockBR != null
	 * @pre | size != null
	 * @pre | size.getX() >= 0 && size.getY() >= 0
	 * @pre |((blockBR.getX()<50000 && blockBR.getY()<30000)&&(blockTL.getX()<50000 && blockTL.getY()<30000)&& (blockTL.getY()>0 && blockTL.getX()>0)&& (blockBR.getY()>0 && blockBR.getX()>0))||(blockTL.equals(new Point(-250,-250))&&blockBR.equals(new Point(-250,-250)))
	 * @post | result != null
	 * @post | result.getBlockTL().equals(blockTL) && result.getBlockBR().equals(blockBR) && result.getSize().equals(size)
	 * 
	 */
	public static BlockState valueOf(Point blockTL, Point blockBR,Vector size) {
		Point blockTL_copy=new Point(blockTL.getX(),blockTL.getY());
		Point blockBR_copy=new Point(blockBR.getX(),blockBR.getY());
		Vector size_copy=new Vector(size.getX(),size.getY());
		return new BlockState(blockTL_copy, blockBR_copy, size_copy);
	}
	
	
	
	/**
	 * 
	 * @inspects | this
	 */
	public Point  getPosition() {
		int blockcenterx= (blockBR_copy.getX()+blockTL_copy.getX())/2;
		int blockcentery= (blockTL_copy.getY()+blockBR_copy.getY())/2;
		Point center=new Point (blockcenterx,blockcentery);
		return center;
	};
	
	/**
	 * 
	 * @inspects | this
	 */
	public Vector getSize() {
		 Vector size_copy=new Vector(this.size_copy.getX(),this.size_copy.getY());
		 return size_copy;
	}
	/**
	 * 
	 * @inspects | this
	 */
	public Point getBlockTL() {
		Point blockTL_copy=new Point(this.blockTL_copy.getX(),this.blockTL_copy.getY());
		return blockTL_copy;
	}
	/**
	 * 
	 * @inspects | this
	 */
	public Point getBlockBR() {
		Point blockBR_copy=new Point(this.blockBR_copy.getX(),this.blockBR_copy.getY());
		return blockBR_copy;
	}
	
	/**
	 * @pre | TL != null
	 * @pre | (TL.getY()>0 && TL.getX()>0 && TL.getY()<30000 && TL.getX() <50000) || TL.equals(new Point(-250,-250))
	 * @mutates mutates the copy of the top-left point of the block | getBlockTL()
	 * @post | TL.equals(getBlockTL())
	 */
	public void setBlockTL(Point TL) {
		Point TL_copy=new Point(TL.getX(),TL.getY());
		this.blockTL_copy=TL_copy;
	}
	
	/**
	 * @pre | BR != null
	 * @pre | (BR.getY()>0 && BR.getX()>0 && BR.getY()<30000 && BR.getX() <50000) || BR.equals(new Point(-250,-250))
	 * @mutates mutates the copy of the bottom-right point of the block | getBlockBR()
	 * @post | BR.equals(getBlockBR())
	 */
	public void setBlockBR(Point BR) {
		Point BR_copy=new Point(BR.getX(),BR.getY());
		this.blockBR_copy=BR_copy;
	}
}
