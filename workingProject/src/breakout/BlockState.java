package breakout;

/**
 * @immutable
 */
public class BlockState {
	// TODO: implement

	
	/**
	 * 
	 * @invar | size != null
	 * @invar | blockTL != null
	 * @invar | blockBR != null
	 * @invar |size.equals(new Vector(50000/10-70,30000/9-70))
	 * @invar | ((blockBR.getX()<50000 && blockBR.getY()<30000)&&(blockTL.getX()<50000 && blockTL.getY()<30000)&& (blockTL.getY()>0 && blockTL.getX()>0)&& (blockBR.getY()>0 && blockBR.getX()>0))||(blockTL.equals(new Point(-250,-250))&&blockBR.equals(new Point(-250,-250)))
	 * @representationObject
	 * 
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
	 * @pre | blockTL != null
	 * @pre | blockBR != null
	 * @pre | size != null
	 * @pre |size.equals(new Vector(50000/10-70,30000/9-70))
	 * @pre |((blockBR.getX()<50000 && blockBR.getY()<30000)&&(blockTL.getX()<50000 && blockTL.getY()<30000)&& (blockTL.getY()>0 && blockTL.getX()>0)&& (blockBR.getY()>0 && blockBR.getX()>0))
	 * @creates |result
	 * @post | result != null
	 * @post | result.getBlockTL().equals(blockTL) && result.getBlockBR().equals(blockBR) && result.getSize().equals(size)
	 * @post |size.equals(new Vector(50000/10-70,30000/9-70))
	 */
	public static BlockState valueOf(Point blockTL, Point blockBR,Vector size) {
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
}
