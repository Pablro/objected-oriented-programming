package breakout;

/**
 * Each instance of this class represents a paddle defined by its central position and size.
 * 
 * @immutable
 * 
 * Abstract state invariants
 * 
 * @invar | getPosition() !=null
 * @invar | getSize() !=null
 * @invar | getPosition().getX()>=0 && getPosition().getY()>=0 && getPosition().getX()<=30000 && getPosition().getY()<=50000
	
 * 
 */
public class PaddleState {
	// TODO: implement
	//DONE-check it
	/**
	 * Representation state invariants
	 * 
	 * @invar | position != null
	 * @invar | size != null
	 * @invar | position.getX()>=0 && position.getY()>=0 && position.getX()<=30000 && position.getY()<=50000
	 * 
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
	 * Returns a paddle object defined by its central position and size.
	 * 
	 * @pre | position != null
	 * @pre | size != null
	 * @pre | size.getX() > 0 && size.getY() > 0
	 * @pre | position.getX()>=0 && position.getY()>=0 && position.getX()<=30000 && position.getY()<=50000
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
	 * @pre | position != null
     * @pre | position.getX()>=0 && position.getY()>=0 && position.getX()<=30000 && position.getY()<=50000
	 * @post | result != null
	 * @post | result.getPosition().equals(position)
	 */
	public PaddleState getNewPosition(Point position) {
		return PaddleState.valueOf(new Point(position.getX(),position.getY()), size) ;
	};
}
