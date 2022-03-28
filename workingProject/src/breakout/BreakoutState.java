package breakout;



// TODO: implement, document
//Some DONE
//miss to implement tick
// I have establish the ball motion
//miss the bounce and other tick checks
//mis to verify physics of the paddle (1/5 of the velocity etc)
public class BreakoutState {
	private BallState[] balls;
	private BlockState[] blocks;
	private Point bottomRight;
	private PaddleState paddle;
	
	//Need defensive programming
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.paddle=paddle;
		this.balls=balls;
		this.bottomRight=bottomRight;
		this.blocks=blocks;
		
	}
	
	public BallState[] getBalls() {
		
		return balls;
	}

	public BlockState[] getBlocks() {
		
		return blocks;
	}

	public PaddleState getPaddle() {
		return paddle;
	}

	public Point getBottomRight() {
		return bottomRight;
	}
//implement the bounce blocks with removing blocks
//still need to check logic
	public void tick(int paddleDir) {
		for (BallState ball:getBalls()) {
			bouncePaddle(ball,getPaddle());
			bounceBlock(ball,getBlocks());

			//Move all balls one step forward according to their current velocity
			Point positionBefore=ball.getCenter(); 
			Point positionAfter=ball.getCenter().plus(ball.getVelocity());
			ball.setPosition(positionAfter);
			
			bounceWall(ball,positionBefore, positionAfter);
			
		}
	}
		
		

	public void movePaddleRight() {
		//tr stands for TOP-Right
		//randomly selected 40, for increasing speed of paddle movement
		Point value=this.paddle.getPosition().plus(new Vector(40,0));
		Point tr= this.paddle.getPosition().plus(this.paddle.getSize());
		if(tr.getX()<=50000) {
			this.paddle.setPosition(value);}
	}

	public void movePaddleLeft() {
		// tl stands for TOP-Left
		//randomly selected 40, for increasing speed of paddle movement
		Point value=this.paddle.getPosition().minus(new Vector(40,0));
		Point tl= this.paddle.getPosition().minus(this.paddle.getSize());
		if(tl.getX()>=0) {
			this.paddle.setPosition(value);}
	}
	
	public boolean isWon() {
		boolean value=false;
		if (blocks==null) {
			value=true;
		}
		
		return value;
	}

	public boolean isDead() {
		boolean value=false;
		if (balls==null) {
			value=true;
		}
		return value;
	}
	private void bouncePaddle(BallState ball,PaddleState paddle)

	{
		// if coordinate y of the ball (it has to intersect this coordinate with the paddle y coordinate) 
		int bally=ball.getCenter().getY()+ball.getSize().getY();
		//ballxs; is the superior limit coordinate of x that ranges the ball lower face that bounce with the paddle. 
		int ballxs=ball.getCenter().getX()+ball.getSize().getX();
		//ballxs; is the inferior limit coordinate of x that ranges the ball lower face that bounce with the paddle. 
		//?ballxi?
		int ballxi= ball.getCenter().getX()-ball.getSize().getX();
		//paddle y coordinate 
		int pady=paddle.getPosition().getY()-paddle.getSize().getY();
		//ballxs; is the superior limit coordinate of x that ranges the paddle upper face that bounce with the ball 
		//?padxs?
		int padxs=paddle.getPosition().getX()+paddle.getSize().getX();
		//ballxs; is the inferior limit coordinate of x that ranges the paddle upper face that bounce with the ball  
		//?padxi?
		int padxi=paddle.getPosition().getX()-paddle.getSize().getX();
		//the unit change (+7) is randomly select for increasing the speed of the game
		//because the for loops can make the graphics go terrible slow (I am sorry for this)
		//better idea to detect collision are welcome
		 for (int bposition=ballxi;bposition<=ballxs;bposition+=7) {
			 for (int padposition=padxi;padposition<=padxs;padposition+=7) {
				 if(bposition==padposition) {
					 if (bally>=pady) {
						 //here goes the reflection formula to set the new velocity
						 Vector d= findingD(ball,bposition,bally);
						 ball.setVelocity(ball.getVelocity().mirrorOver(d));
					 }
					 
				 }
			 }
		 }
	}
	private void bounceBlock(BallState ball,BlockState[] blocks) {
		for (BlockState block:blocks) {
			//yi|ys are inferior and superior borders of y coordinate
			//xi|xs are inferior and superior borders for x coordinate
			int ballyi=ball.getCenter().getY()+ball.getSize().getY();
			int ballys=ball.getCenter().getY()-ball.getSize().getY();
			int ballxi=ball.getCenter().getX()-ball.getSize().getX();
			int ballxs=ball.getCenter().getX()+ball.getSize().getX();
			int blockyi=block.getBlockBR().getY();
			int blockys=block.getBlockTL().getY();
			int blockxs=block.getBlockBR().getX();
			int blockxi=block.getBlockTL().getX();
			//Face up-down of the block collision
			//the unit change (+10) is randomly select for increasing the speed of the game
			//because the for loops can make the graphics go terrible slow (I am sorry for this)
			//better idea to detect collision are welcome
			for(int ballposition=ballxi;ballposition<=ballxs;ballposition+=10) {
				for(int blockposition=blockxi;blockposition<=blockxs;blockposition+=10) {
					if (ballposition==blockposition) {
						if(ballyi>=blockys && ballyi<blockyi) {
							Vector d= findingD(ball,ballposition,ballyi);
							ball.setVelocity(ball.getVelocity().mirrorOver(d));
							//look at gui coordinates conversion. the point here is to set it to 0,0
							// we can not delete the object, we cause the program to crash
							block.setBlockBR(new Point(-250,-250));
							block.setBlockTL(new Point(-250,-250));
						}
						if(ballys<=blockyi && ballys>blockys) {
							Vector d= findingD(ball,ballposition,ballys);
							ball.setVelocity(ball.getVelocity().mirrorOver(d));
							//look at gui coordinates conversion. the point here is to set it to 0,0
							// we can not delete the object, we cause the program to crash
							block.setBlockBR(new Point(-250,-250));
							block.setBlockTL(new Point(-250,-250));
						}
					}
				}
			}
			//Face Left_right of the block collision
			//the unit change (+10) is randomly select for increasing the speed of the game
			//because the for loops can make the graphics go terrible slow (I am sorry for this)
			//better idea to detect collision are welcome
			for(int ballposition=ballyi;ballposition<=ballys;ballposition=+10) {
				for(int blockposition=blockyi;blockposition<=blockys;blockposition=+10) {
					if(ballposition==blockposition) {
						if(ballxi<=blockxs && ballxi>blockxi) {
							Vector d= findingD(ball,ballxi,ballposition);
							ball.setVelocity(ball.getVelocity().mirrorOver(d));
							//look at gui coordinates conversion. the point here is to set it to 0,0
							// we can not delete the object, we cause the program to crash
							block.setBlockBR(new Point(-250,-250));
							block.setBlockTL(new Point(-250,-250));
							
						}
						if(ballxs>=blockxi && ballxs<blockxs) {
							Vector d= findingD(ball,ballxs,ballposition);
							ball.setVelocity(ball.getVelocity().mirrorOver(d));
							block.setBlockBR(new Point(-250,-250));
							block.setBlockTL(new Point(-250,-250));
							
						}
					}
				}
			}
			
			}
		
	}
	
	private void bounceWall(BallState ball,Point positionBefore, Point positionAfter) {			
		//A rectangle range generated by the ball status one step moved before and moved after 
		//The four vertices of the ball moved before
		Point TLBefore = new Point (positionBefore.getX()-ball.getSize().getX(),positionBefore.getY()-ball.getSize().getY());
		Point BRBefore = new Point(positionBefore.getX()+ball.getSize().getX(),positionBefore.getY()+ball.getSize().getY());
		Point TRBefore = new Point (positionBefore.getX()+ball.getSize().getX(),positionBefore.getY()-ball.getSize().getY());
		Point BLBefore = new Point (positionBefore.getX()-ball.getSize().getX(),positionBefore.getY()+ball.getSize().getY());
		//The four vertices of the ball moved after
		Point TLAfter = new Point (positionAfter.getX()-ball.getSize().getX(),positionAfter.getY()-ball.getSize().getY());
		Point BRAfter = new Point (positionAfter.getX()+ball.getSize().getX(),positionAfter.getY()+ball.getSize().getY());
		Point TRAfter = new Point (positionAfter.getX()+ball.getSize().getX(),positionAfter.getY()-ball.getSize().getY());
		Point BLAfter = new Point (positionAfter.getX()-ball.getSize().getX(),positionAfter.getY()+ball.getSize().getY());
		//TL and BR of the rectangle range of moving ball 
		Point rangeTL=new Point (Math.min(Math.min(Math.min(TLBefore.getX(), BRBefore.getX()),Math.min(TLAfter.getX(), BRAfter.getX())),Math.min(Math.min(TRBefore.getX(), BLBefore.getX()),Math.min(TRAfter.getX(), BLAfter.getX()))),Math.min(Math.min(Math.min(TLBefore.getY(), BRBefore.getY()),Math.min(TLAfter.getY(), BRAfter.getY())),Math.min(Math.min(TRBefore.getY(), BLBefore.getY()),Math.min(TRAfter.getY(), BLAfter.getY()))));
		Point rangeBR=new Point (Math.max(Math.max(Math.max(TLBefore.getX(), BRBefore.getX()),Math.max(TLAfter.getX(), BRAfter.getX())),Math.max(Math.max(TRBefore.getX(), BLBefore.getX()),Math.max(TRAfter.getX(), BLAfter.getX()))),Math.max(Math.max(Math.max(TLBefore.getY(), BRBefore.getY()),Math.max(TLAfter.getY(), BRAfter.getY())),Math.max(Math.max(TRBefore.getY(), BLBefore.getY()),Math.max(TRAfter.getY(), BLAfter.getY()))));
		
		
		//Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
		//The rectangle range exceeds the game map
		if(positionAfter.getX()>positionBefore.getX()&&rangeBR.getX()>=bottomRight.getX()) {
			//case: when the ball moves toward right, check if it exceeds the right wall
			Vector d= findingD(positionBefore,positionAfter);
			ball.setVelocity(ball.getVelocity().mirrorOver(d));
		}else if (positionAfter.getX()<positionBefore.getX()&&rangeTL.getX()<=0) {
			//case: when the ball moves toward left, check if it exceeds the left wall
			Vector d= findingD(positionBefore,positionAfter);
			ball.setVelocity(ball.getVelocity().mirrorOver(d));
		}else if (positionAfter.getY()<positionBefore.getY()&&rangeTL.getY()<=0) {
			//case: when the ball moves up, check if it exceeds the top wall
			Vector d= findingD(positionBefore,positionAfter);
			ball.setVelocity(ball.getVelocity().mirrorOver(d));
		}
		//Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
		else if(positionAfter.getY()>positionBefore.getY()&&rangeBR.getY()>=bottomRight.getY()) {
			//case: when the ball moves down, check if it exceeds the bottom wall
			ball=null;
		}

	}
	
	
		
	private Vector findingD(BallState ball,int crossx, int crossy) {
		/*
		 * The direction d is given by the line between the center of the ball 
		 * and the point on the ball's surface where it hits the obstacle
		 * 
		 *  If d is normalized, i.e. ||d|| = sqrt(d . d) = 1, then dividing by (d . d) is of course not necessary.
  		*	You may use the method `Vector.mirrorOver` which implements this computation already.
  		*	Method MirrorOver from Vector class considers d as normalized vector.
		 */
		Vector unormalized= new Vector(crossx-ball.getCenter().getX(),crossy-ball.getCenter().getY());
		 Vector normalized=unormalized.scaledDiv((int)Math.sqrt(Math.pow(unormalized.getX(),2 )+Math.pow(unormalized.getY(), 2)));
		return normalized;
	}
	
	//Overload the method findingD for bounceWall method
	private Vector findingD(Point positionBefore,Point positionAfter) {
		/*
		 * The direction d is given by the line between the center of the ball 
		 * and the point on the ball's surface where it hits the obstacle
		 * 
		 *  If d is normalized, i.e. ||d|| = sqrt(d . d) = 1, then dividing by (d . d) is of course not necessary.
  		*	You may use the method `Vector.mirrorOver` which implements this computation already.
  		*	Method MirrorOver from Vector class considers d as normalized vector.
		 */
		Vector unormalized= new Vector(positionAfter.getX()-positionBefore.getX(),positionAfter.getY()-positionBefore.getY());
		 Vector normalized=unormalized.scaledDiv((int)Math.sqrt(Math.pow(unormalized.getX(),2 )+Math.pow(unormalized.getY(), 2)));
		return normalized;
	}
}
