package breakout;

/**

 * @immutable
 
 */
public class PaddleState {
	// TODO: implement
	/**
	 * 
	 * @invar | position != null
	 * @invar | size != null
	 * @representationObject
	 */
	private final Point position;
	private final Vector size;
	
	private PaddleState (Point position, Vector size){
		this.position=position;
		this.size=size;
		
	};
	
	//Factory method
	/**
	 * Returns a paddle with central position and a predefined size
	 * @pre | position != null
	 * @pre | size != null
	 * @pre |position.getX()>=0 && position.getY()>=0 && position.getX()<=50000 && position.getY()<=30000
	 * @pre |size.equals(new Vector(50000/10/2,30000/9/2))
	 * @creates |result
	 * @post | result != null
	 * @post | result.getPosition().equals(position) && result.getSize().equals(size)
	 * 
	 */
	public static PaddleState valueOf (Point position, Vector size) {
		Point positionCopy=new Point(position.getX(),position.getY());
		Vector sizeCopy=new Vector (size.getX(),size.getY());
		return new PaddleState (positionCopy,sizeCopy);
	}
	/**
	 * @inspects | this
	 * 
	 */
	public Point  getPosition() {
		return new Point(this.position.getX(),this.position.getY());
	};
	/**
	 * @inspects | this
	 * 
	 */
	public Vector getSize() {
		return new Vector(this.size.getX(),this.size.getY());
	}
	
	/**
	 * 
	 * @pre |position!=null
	 * @post |result.getPosition().equals(position)
	 */
	public PaddleState getNewPosition(Point position) {
		return PaddleState.valueOf(new Point(position.getX(),position.getY()), getSize()) ;
	};
}
