package breakout;

public class BlockState {
	// TODO: implement
	//DONE-check it
	private Point blockTL;
	private Point blockBR;
	private Vector size;
	public BlockState (Point blockTL, Point blockBR,Vector size){
		this.blockBR=blockBR;
		this.blockTL=blockTL;
		this.size=size;
	};
	public Point getPosition() {
		int blockcenterx= (blockBR.getX()-blockTL.getX())/2;
		int blockcentery= (blockTL.getY()-blockBR.getY())/2;
		Point center=new Point (blockcenterx,blockcentery);
		return center;
	};
	public Vector getSize() {
		return size;
	}
	public Point getBlockTL() {
		return blockTL;
	}
	public Point getBlockBR() {
		return blockBR;
	}
}
