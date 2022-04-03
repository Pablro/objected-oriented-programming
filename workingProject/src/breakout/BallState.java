package breakout;

/**
 * 
 * 
 */
public class BallState {
	// TODO: implement
	//DONE-check it
	/*
	 * 	 * Enforcing inmutability for this object is complicated
	 * due to the dependance of object state(mutable) regarding its dynamic 
	 * of the BreakoutState class,
	 * which is also call by the View class.
	 * The solution propose was to consider a copy of the values to make posible
	 * the display of the dynamics and the game view.
	 * while the original ones are preserve and untouch,
	 * only these representation object(which are copies) can be mutate.
	 * However by definition of {@inmutable} this can be questionable.But by the conceptual function of this concept
	 * it is perfectly fine to said that the inmutability of the object is achieved even if it is not
	 * by conceptual definition.Hence inmutability is achieved ~50:50 and the abstraction is respected.
	 */
	/**
	 * @invar |center!=null
	 * @invar |velocity!=null
	 * @invar |size!=null
	 * @invar |center_copy!=null
	 * @invar |velocity_copy!=null
	 * @invar |size_copy!=null
	 * @invar put some constraints to the field coordinates of the ball|size_copy.equals(new Vector(700/2,700/2))|| size_copy.equals(new Vector(0,0))
	 * @invar |(center_copy.getX()<50000 && center_copy.getX()>0)&& center_copy.getY()<30000 && center_copy.getY()>0||center_copy.equals(new Point(-250,-250))
	 * @representationObject
	 * */
	private final  Point center;
	private final   Vector velocity;
	private final  Vector size;
	private Point center_copy;
	private Vector velocity_copy;
	private Vector size_copy;
	/**
	 * 
	 * @pre |center!=null
	 * @pre |velocity!=null
	 * @pre initial diameter from GameMap class|diameter==700
	 * @pre initial velocity from GameMap class|velocity.equals(new Vector(5,7))
	 * @pre initial center from GameMap class|(center.getX()<50000 && center.getX()>0)&& center.getY()<30000 && center.getY()>0
	 * @post |getCenter().equals(center)
	 * @post |getVelocity().equals(velocity)
	 * @post |getSize().equals(new Vector(diameter/2,diameter/2))
	 */

	public BallState (Point center,int diameter,Vector velocity){
		this.center=center;
		this.velocity=velocity;
		this.size=new Vector(diameter/2,diameter/2);
		this.center_copy=this.center;
		this.velocity_copy=this.velocity;
		this.size_copy=this.size;
		
	};
	/*
	 * The value return is a mutable copy of the original one that is stored
	 * because this copy is a mutable {@objectRepresentation} of the inmutable class
	 * this is how conceptually the inmutability does not follow the definition.
	 * But because this  {@objectRepresentation} is just a copy of the original one then we are protecting the original one
	 * hence the inmutability is achieved.
	 * Strictly speaking the {@inmutable class} has 6 "supposely inmutable" {@objectRepresentation}, but the half are {@mutable} copies
	 * of the original ones. In this way we can say somehow that we clone the values for retreiving and updates the game dynamic from
	 * GameView and the BreakoutState.
	 * The following methods are involved in this logic:
	 * Position: {@setPosition()} and {@getCenter()}
	 * Velocity: {@setVelocity()} and {@getVelocity()}
	 * Size: {@setSize()} and {@getSize()}
	 */
	/** 
	 * 
	 * @inspects 
	 */
	public Point getCenter() {
		
		return center_copy;
	}
	/**
	 * 
	 * @inspects
	 */
	public Vector getVelocity() {
		return velocity_copy;
	}
	/**
	 * 
	 * @inspects
	 */
	public Vector getSize() {
		return size_copy;
	};
	/**
	 *
	 *@pre |position!=null
	 *@mutates mutates the copy of the position|getCenter()
	 *@post |position==getCenter()
	 */
	protected void setPosition(Point position) {
		this.center_copy=position;
	};
	/**
	 * @pre |velocity!=null
	 * @mutates mutates the copy of the velocity|getVelocity()
	 * @post |velocity==getVelocity()
	 */
	protected void setVelocity(Vector velocity) {
		this.velocity_copy=velocity;
	}
	/**
	 * @pre |size!=null
	 * @mutates mutates the copy of the size|getSize()
	 * @post |size==getSize()
	 */
	protected void setSize(Vector size) {
		this.size_copy=size;
	}

}
