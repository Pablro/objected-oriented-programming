package breakout;

/**
 * 
 * 
 */
public class BallState {
	// TODO: implement
	//DONE-check it
	/**
	 * @invar |center!=null
	 * @invar |velocity!=null
	 * @invar |size!=null
	 //@invar put some constraints to the field coordinates of the ballsize_copy.equals(new Vector(700/2,700/2))|| size_copy.equals(new Vector(0,0))
	 //@invar (center_copy.getX()<50000 && center_copy.getX()>0)&& center_copy.getY()<30000 && center_copy.getY()>0||center_copy.equals(new Point(-250,-250))
	 * @representationObject
	 * */
	private final  Point center;
	private final   Vector velocity;
	private final  Vector size;
	/**
	 * 
	 * @pre |center!=null
	 * @pre |velocity!=null
	 * @pre initial diameter from GameMap class|(size.equals(new Vector(700/2,700/2)))||size.equals(new Vector(0,0))
	 * @pre initial center from GameMap class|(center.getX()<50000 && center.getX()>0&& center.getY()<30000 && center.getY()>0) || center.equals(new Point(-250,-250))
	 * @post |getCenter().equals(center)
	 * @post |getVelocity().equals(velocity)
	 * @post |getSize().equals(size)
	 */

	private BallState (Point center,Vector size,Vector velocity){
		this.center=center;
		this.velocity=velocity;
		this.size=size;
	
		
	};
	/**
	 * 
	 * @throws |diameter!=700
	 * @throws |center==null
	 * @throws |velocity==null
	 * @throws|(center.getX()>=50000 && center.getX()<=0)&& center.getY()>=30000 && center.getY()<=0
	 * @throws |!(velocity.equals(new Vector(5,7)))
	 * @creates |result
	 * @post |result!=null
	 * @post |result.getCenter().equals(center)
	 * @post |result.getSize().equals(new Vector(diameter/2,diameter/2))
	 * @post |result.getVelocity().equals(velocity)
	 */
	public static BallState valueOfA(Point center, int diameter,Vector velocity) {
		if(center==null | velocity==null) {
			throw new NullPointerException("Invalid argument");
		}
		if(diameter!=700) {
			throw new AssertionError("Invalid argument");
		}
		if((center.getX()>=50000 && center.getX()<=0)&& center.getY()>=30000 && center.getY()<=0) {
			throw new AssertionError("Invalid argument");
		}
		if(!(velocity.equals(new Vector(5,7)))) {
			throw new AssertionError("Invalid argument");
		}
		Vector size= new Vector(diameter/2,diameter/2);
		return new BallState(center,size,velocity);
		
	}
	/**
	 * 
	 * @throws |!(size.equals(new Vector(700/2,700/2)))||!(size.equals(new Vector(0,0)))
	 * @throws |position==null
	 * @throws |velocity==null
	 * @throws|(position.getX()>=50000 && position.getX()<=0)&& position.getY()>=30000 && position.getY()<=0
	 * @creates |result
	 * @post |result!=null
	 * @post |result.getCenter().equals(position)
	 * @post |result.getSize().equals(size)
	 * @post |result.getVelocity().equals(velocity)
	 */
	public static BallState valueOfB(Point position,Vector size,Vector velocity) {
		if(position==null | velocity==null) {
			throw new NullPointerException("Invalid argument");
		}

		if((position.getX()>=50000 && position.getX()<=0)&& position.getY()>=30000 && position.getY()<=0) {
			throw new AssertionError("Invalid argument");
			
		}

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
	 * @creates|result
	 */
	public BallState getNewVelocity(Vector velocity) {;
		return BallState.valueOfB(getCenter(), getSize(), velocity);
	
	}

	/**
	 * @creates|result
	 */
	public BallState getNewPosition(Point position) {
		return BallState.valueOfB(position,getSize(),getVelocity());
	}
	/**
	 * @creates|result
	 */
	public BallState getNewSize(Vector size) {
		return BallState.valueOfB(getCenter(),size,getVelocity());
	}
}
