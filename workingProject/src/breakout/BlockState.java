package breakout;

public class BlockState {
	// TODO: implement
	//DONE-check it
	Point blockTL1;
	Point blockBR1;
	Vector size1;
	BlockState (Point blockTL, Point blockBR,Vector size){
		this.blockBR1=blockBR;
		this.blockTL1=blockTL;
		this.size1=size;
	};
	public Point  getPosition() {
		int blockcenterx= (blockBR1.getX()-blockTL1.getX())/2;
		int blockcentery= (blockTL1.getY()-blockBR1.getY())/2;
		Point center=new Point (blockcenterx,blockcentery);
		return center;
	};
	public Vector getSize() {
		return size1;
	}
	public Point getBlockTL() {
		return blockTL1;
	}
	public Point getBlockBR() {
		return blockBR1;
	}
}
