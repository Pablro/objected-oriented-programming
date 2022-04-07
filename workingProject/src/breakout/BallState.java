package breakout;

/**
 * 
 * @inmutable
 */
public class BallState {
	// TODO: implement
	/**
	 * @invar |center!=null
	 * @invar |velocity!=null
	 * @invar |size!=null
	 * @invar put some constraints to the field coordinates of the ball|size.equals(new Vector(700/2,700/2))
	 * @invar |(center.getX()<50000 && center.getX()>0)&& center.getY()<30000 && center.getY()>0
	 * @representationObject
	 * */
	private final  Point center;
	private final   Vector velocity;
	private final  Vector size;
	/**
	 * 
	 * @pre |center!=null
	 * @pre |velocity!=null
	 * @pre |size!=null
	 * @pre initial diameter from GameMap class|(size.equals(new Vector(700/2,700/2)))
	 * @pre initial center from GameMap class|(center.getX()<50000 && center.getX()>0&& center.getY()<30000 && center.getY()>0) 
	 * @mutates just once for initializing|this
	 * @post |getCenter().equals(center)
	 * @post |getVelocity().equals(velocity)
	 * @post |getSize().equals(size)
	 */

	private BallState (Point center,Vector size,Vector velocity){
		this.center=center;
		this.velocity=velocity;
		this.size=size;
	
		
	};
	//Factory Method
	/**
	 * Returns a ball with an initial velocity,size and center
	 * @pre |diameter==700
	 * @pre |center!=null
	 * @pre |velocity!=null
	 * @pre|(center.getX()<50000 && center.getX()>0)&& center.getY()<30000 && center.getY()>0
	 * @pre |(velocity.equals(new Vector(5,7)))
	 * @creates |result
	 * @post |result!=null
	 * @post |result.getCenter().equals(center)
	 * @post |result.getSize().equals(new Vector(diameter/2,diameter/2))
	 * @post |result.getVelocity().equals(velocity)
	 */
	public static BallState valueOfA(Point center, int diameter,Vector velocity) {

		Vector size= new Vector(diameter/2,diameter/2);
		return new BallState(center,size,velocity);
		
	}
	//Factory Method
	/**
	 * Returns a ball with a given bounce velocity, modifiable size(in case of low wall) and dynamic position. 
	 * @pre |(size.equals(new Vector(700/2,700/2)))
	 * @pre |position!=null
	 * @pre |velocity!=null
	 * @pre|(position.getX()<50000 && position.getX()>0)&& position.getY()<30000 && position.getY()>0 
	 * @creates |result
	 * @post |result!=null
	 * @post |result.getCenter().equals(position)
	 * @post |result.getSize().equals(size)
	 * @post |result.getVelocity().equals(velocity)
	 */
	public static BallState valueOfB(Point position,Vector size,Vector velocity) {

		return new BallState(position,size,velocity);
		
	}
	/**
	 * 
	 * @inspects
	 */
	public Point getCenter() {
		
		return center;
	}
	/**
	 * 
	 * @inspects
	 */
	public Vector getVelocity() {
		return velocity;
	}
	/**
	 * 
	 * @inspects
	 */
	public Vector getSize() {
		return size;
	};
	/**
	 * @pre |velocity!=null
	 * @post |result.getVelocity().equals(velocity)
	 */
	public BallState getNewVelocity(Vector velocity) {;
		return BallState.valueOfB(getCenter(), getSize(), velocity);
	
	}

	/**
	 * @pre |position!=null
	 * @pre |(position.getX()<50000 && position.getX()>0)&& position.getY()<30000 && position.getY()>0  
	 * @post |result.getCenter().equals(position)
	 */
	public BallState getNewPosition(Point position) {
		return BallState.valueOfB(position,getSize(),getVelocity());
	}
}
