package breakout;



// TODO: implement, document

/**
 * 
 * @invar if their are null you have not initialize the BreakoutState correctly|getBalls()!=null
 * @invar if their are null you have not initialize the BreakoutState correctly|getBlocks()!=null
 * @invar if their are null you have not initialize the BreakoutState correctly|getPaddle()!=null
 * @invar if their are null you have not initialize the BreakoutState correctly|getBottomRight()!=null
 * 
 *
 */
public class BreakoutState {
	/**
	 *
	 *@invar |balls!=null
	 *@invar |blocks!=null
	 *@invar |bottomRight!=null
	 *@invar |paddle!=null
	 *@invar paddle field enforcement| getPaddle().getPosition().getY()-getPaddle().getSize().getY()>=0 &&
	 *		| getPaddle().getPosition().getY()+getPaddle().getSize().getY()<=getBottomRight().getY() &&
	 *		| getPaddle().getPosition().getX()-getPaddle().getSize().getX()>=0 &&
	 *		|getPaddle().getPosition().getX()+getPaddle().getSize().getX()<=getBottomRight().getX()
	 *@representationObject
	 */
	private BallState[] balls;
	private BlockState[] blocks;
	private final Point bottomRight;
	private PaddleState paddle;
	private Vector unormalizedD;

	
		/**
		 * 
		 *@pre |balls!=null
		 *@pre |blocks!=null
		 *@pre |bottomRight!=null
		 *@pre	|paddle!=null
		 *@mutates |this
		 *@post |getPaddle().equals(paddle)
		 *@post |getBottomRight().equals(bottomRight)
		 *
		 *
		 *
		 */
	// Defensive programming?
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {

		this.paddle=paddle;
		this.balls=balls;
		this.bottomRight=bottomRight;
		this.blocks=blocks;

	}

	/**
	 * 
	 *
	 * @inspects
	 */
	public BallState[] getBalls() {
		
		return balls.clone();
	}
	/**
	 * 
	 * 
	 * @inspects
	 */
	public BlockState[] getBlocks() {
		
		return blocks.clone();
	}
	/**
	 * 
	 * @inspects
	 */
	public PaddleState getPaddle() {
		PaddleState paddleCopy= PaddleState.valueOf(paddle.getPosition(),paddle.getSize());
		return paddleCopy;
	}
	/**
	 * 
	 * @inspects
	 */
	public Point getBottomRight() {
		return bottomRight;
	}
	/**
	 *@pre |paddleDir==1 || paddleDir==-1 || paddleDir==0 
	 *
	 */
	
	public void tick(int paddleDir) {
		for (int i=0; i<balls.length;i++) {
			balls[i]=bouncePaddle(balls[i],getPaddle(),paddleDir);
			balls[i]=bounceBlock(balls[i],blocks);
			Point positionAfter=balls[i].getCenter().plus(balls[i].getVelocity());
			balls[i]=balls[i].getNewPosition(positionAfter);
			balls[i]=bounceWall(balls[i]);
			
			
		}
	}
		
		
	/**
	 *moves paddle position according to velocity
	 *@post |getPaddle().getPosition().equals(old(getPaddle().getPosition().plus(new Vector(10,0)))) || 
	 *		|getPaddle().getPosition().equals(old(getPaddle().getPosition()))
	 */
	public void movePaddleRight() {
		//tr stands for TOP-Right
		Point value=getPaddle().getPosition().plus(new Vector(10,0));
		Point tr= getPaddle().getPosition().plus(getPaddle().getSize());
		if(tr.getX()<=50000) {
			paddle=paddle.getNewPosition(value);
			//getPaddle().setPosition(value);
			}
	}
	/**
	 * moves paddle position according to velocity
	 *@post |getPaddle().getPosition().equals(old(getPaddle().getPosition().minus(new Vector(10,0)))) || 
	 *		|getPaddle().getPosition().equals(old(getPaddle().getPosition()))
	 */
	public void movePaddleLeft() {
		Point value=getPaddle().getPosition().minus(new Vector(10,0));
		Point tl= getPaddle().getPosition().minus(getPaddle().getSize());
		if(tl.getX()>=0) {
			paddle=paddle.getNewPosition(value);
			//getPaddle().setPosition(value);
			}
	}
	/**
	 * 
	 * @post |result==true || result==false
	 */
	public boolean isWon() {
		int emptyblocks=0;
		boolean value=false;
		for (BlockState block:getBlocks()) {
			if (block.getBlockBR().equals(new Point(-250,-250))) {
				if(block.getBlockTL().equals(new Point(-250,-250))) {
					emptyblocks++;
				}
			}
		}
		if (emptyblocks==getBlocks().length) {
			value=true;
		}
		
		
		return value;
	}
	/**
	 *
	 *@post |result==true || result==false	 */
	public boolean isDead() {
		int emptyballs=0;
		boolean value=false;
		for (BallState ball:getBalls()) {
			if(ball.getCenter().equals(new Point(-250,-250))) {
				if(ball.getSize().equals(new Vector(0,0))) {
					emptyballs++;
				}
			};
		}
		if(emptyballs==getBalls().length) {
			value=true;
		}

		
		return value;
	}
	/**
	 *
	 *@pre|paddleDir==1 || paddleDir==-1 || paddleDir==0 
	 *@pre|ball!=null
	 *@pre|paddle!=null
	 *@post|ball.getVelocity()!=null
	 * 
	 */
	private BallState bouncePaddle(BallState ball,PaddleState paddle, int paddleDir)

	{
		//Collision coordinates: for defining a range of possible bouncing points
		int ballys=ballCoordinates(ball)[1];
		int ballxs=ballCoordinates(ball)[3];
		int ballxi= ballCoordinates(ball)[2]; 
		int padyi=paddleCoordinates(paddle)[0];
		int padys=paddleCoordinates(paddle)[1];
		int padxs=paddleCoordinates(paddle)[2];
		int padxi=paddleCoordinates(paddle)[3];
		//the unit change (+7) is randomly select for increasing the speed of the game
		//because the for loops can make the graphics go terrible slow (I am sorry for this)
		//better idea to detect collision are welcome
		 for (int bposition=ballxi;bposition<=ballxs;bposition+=7) {
			 for (int padposition=padxi;padposition<=padxs;padposition+=7) {
				 if(bposition==padposition) {
					 if (ballys>=padyi &&ballys<padys) {
						 //here goes the reflection formula to set the new velocity
						 Vector d= findingD(ball,bposition,ballys);
							/*To avoid weird effects, it is important that a ball only bounces on an object when the direction from the ball (vector d) to that
							 *  object is at a sharp angle from the ball's current velocity (vector v).
							 *  You can check this easily by verifying that the dot product of the two vectors (d . v) is positive.
							 */
						 if(d.product(ball.getVelocity())>0) {
							 Vector withoutpaddlevector=ball.getVelocity().mirrorOver(d);
							 //Additionally, the ball must speed up by one fifth of the current velocity of the paddle.
							 ball=ball.getNewVelocity(withoutpaddlevector.plus(new Vector(paddle.getPosition().getX(),paddle.getPosition().getY()).scaled(paddleDir*1/5)));}
					 }
					 
				 }
			 }
		 }
		return ball;
	}
	/**
	 *
	 * @pre|ball!=null
	 * @pre|blocks!=null
	 * @post |ball.getVelocity() != null
	 *
	 * 
	 */
	private BallState bounceBlock(BallState ball,BlockState[] blocks) {
		for (int i =0;i<blocks.length;i++) {
			//Collision coordinates: for defining a range of possible bouncing points
			BlockState block=blocks[i];
			int ballyi=ballCoordinates(ball)[0];
			int ballys=ballCoordinates(ball)[1];
			int ballxi=ballCoordinates(ball)[2];
			int ballxs=ballCoordinates(ball)[3];
			int blockys=blockCoordinates(block)[0];
			int blockyi=blockCoordinates(block)[1];
			int blockxs=blockCoordinates(block)[2];
			int blockxi=blockCoordinates(block)[3];
			//Face up-down of the block collision
			//the unit change (+10) is randomly select for increasing the speed of the game
			//because the for loops can make the graphics go terribly slow.
			
			for(int ballposition=ballxi;ballposition<=ballxs;ballposition+=5) {
				for(int blockposition=blockxi;blockposition<=blockxs;blockposition+=5) {
					if (ballposition==blockposition) {
						if(ballyi<=blockys && ballyi>blockyi && ballys>blockys) {
									Vector d= findingD(ball,ballposition,ballyi);
									/*To avoid weird effects, it is important that a ball only bounces on an object when the direction from the ball (vector d) to that
									 *  object is at a sharp angle from the ball's current velocity (vector v).
									 *  You can check this easily by verifying that the dot product of the two vectors (d . v) is positive.
									 */
									if(d.product(ball.getVelocity())>0) {
										ball=ball.getNewVelocity(ball.getVelocity().mirrorOver(d));
										//Based in the gui coordinates conversion. the point here is to set it to 0,0									
										blocks[i]=blocks[i].setBlockTLBR(new Point(-250,-250), new Point(-250,-250));
										}

						}
						if(ballys>=blockyi && ballys<blockys && ballyi<blockyi) {
								Vector d= findingD(ball,ballposition,ballys);
								/*To avoid weird effects, it is important that a ball only bounces on an object when the direction from the ball (vector d) to that
								 *  object is at a sharp angle from the ball's current velocity (vector v).
								 *  You can check this easily by verifying that the dot product of the two vectors (d . v) is positive.
								 */
								if(d.product(ball.getVelocity())>0) {
									ball=ball.getNewVelocity(ball.getVelocity().mirrorOver(d));
									//Based in the gui coordinates conversion. the point here is to set it to 0,0
									blocks[i]=blocks[i].setBlockTLBR(new Point(-250,-250), new Point(-250,-250));
									}
								}
								
							}

						}
			}
			
			//Face Left_right of the block collision
			//the unit change (+10) is randomly select for increasing the speed of the game
			//because the for loops can make the graphics go terribly slow
			//same principle for this case the sensitivity and the program performance work well at this unit change in the for loops
			for(int ballposition=ballyi;ballposition<=ballys;ballposition+=7) {
				for(int blockposition=blockyi;blockposition<=blockys;blockposition+=8) {
					
					if (ballposition==blockposition) {
						if(ballxi<=blockxs && ballxi>blockxi && ballxs>blockxs) {
							Vector d= findingD(ball,ballxi,ballposition);
							/*To avoid weird effects, it is important that a ball only bounces on an object when the direction from the ball (vector d) to that
							 *  object is at a sharp angle from the ball's current velocity (vector v).
							 *  You can check this easily by verifying that the dot product of the two vectors (d . v) is positive.
							 */
							if(d.product(ball.getVelocity())>0) {
								ball=ball.getNewVelocity(ball.getVelocity().mirrorOver(d));
								//Based in the gui coordinates conversion. the point here is to set it to 0,0
								//block.setBlockTLBR(new Point(-250,-250), new Point(-250,-250));
									}

						}
						if(ballxs>=blockxi && ballxs<blockxs && ballxi<blockxi) {
							Vector d= findingD(ball,ballxs,ballposition);
							/*To avoid weird effects, it is important that a ball only bounces on an object when the direction from the ball (vector d) to that
							 *  object is at a sharp angle from the ball's current velocity (vector v).
							 *  You can check this easily by verifying that the dot product of the two vectors (d . v) is positive.
							 */
							if(d.product(ball.getVelocity())>0) {
							ball=ball.getNewVelocity(ball.getVelocity().mirrorOver(d));
							//Based in the gui coordinates conversion. the point here is to set it to 0,0
							//block.setBlockTLBR(new Point(-250,-250), new Point(-250,-250));
								}
							}
								
					}

				}
			}

			
		}
		return ball;
	}
	/**
	 *
	 * @pre |ball!=null
	 *@post |ball.getVelocity()!=null
	 */

	private BallState bounceWall(BallState ball) {	
		//Collision coordinates: for defining a range of possible bouncing points
		int ballyi=ballCoordinates(ball)[0];
		int ballys=ballCoordinates(ball)[1];
		int ballxi=ballCoordinates(ball)[2];
		int ballxs=ballCoordinates(ball)[3];
		int wallxi=wallCoordinates()[0];
		int wallxs=wallCoordinates()[1];
		int wallyi=wallCoordinates()[2];
		int wallys=wallCoordinates()[3];
		for(int ballposition=ballxi;ballposition<=ballxs;ballposition+=5) {
			for(int blockposition=wallxi;blockposition<=wallxs;blockposition+=5) {
				if (ballposition==blockposition) {
					if(ballyi<=wallys && ballyi>wallyi && ballys>wallys) {
								ball=ball.getNewPosition(new Point(-250,-250));
								ball=ball.getNewSize(new Vector(0,0));
								//look at gui coordinates conversion. the point here is to set it to 0,0


					}
					if(ballys>=wallyi && ballys<wallys && ballyi<wallyi) {
							
						//Based in the gui coordinates conversion. the point here is to set it to 0,0
						Vector d= findingD(ball,ballposition,ballys);
						ball=ball.getNewVelocity(ball.getVelocity().mirrorOver(d));
							
						}

					}
			}
		}
		for(int ballposition=ballyi;ballposition<=ballys;ballposition+=7) {
			for(int blockposition=wallyi;blockposition<=wallys;blockposition+=8) {
				
				if (ballposition==blockposition) {
					if(ballxi<=wallxs && ballxi>wallxi && ballxs>wallxs) {
						Vector d= findingD(ball,ballxi,ballposition);
						ball=ball.getNewVelocity(ball.getVelocity().mirrorOver(d));
						//Based in the gui coordinates conversion. the point here is to set it to 0,0


					}
					if(ballxs>=wallxi && ballxs<wallxs && ballxi<wallxi) {
						Vector d= findingD(ball,ballxs,ballposition);
						ball=ball.getNewVelocity(ball.getVelocity().mirrorOver(d));
						//Based in the gui coordinates conversion. the point here is to set it to 0,0
						}
							
				}

			}
		}
		
	return ball;
	}
	/*
	 * 	 * These are collision coordinates taken from TL, TR, BL, BR of each rectange.
	 * They are used to establish a set of points in between where a collision can happen
	 */
	/**

	 * 
	 * @pre Enforce balls in the field| (ball.getCenter().getY()-ball.getSize().getY()>=0 &&
	 *		| ball.getCenter().getY()+ball.getSize().getY()<=getBottomRight().getY() &&
	 *		| ball.getCenter().getX()-ball.getSize().getX()>=0 &&
	 *		|ball.getCenter().getX()+ball.getSize().getX()<=getBottomRight().getX()) ||
	 *		|ball.getCenter().equals(new Point(-250,-250))
	 * @pre |ball!=null
	 *@creates |result
	 *@post |result!=null
	 *
	 */
	private int[] ballCoordinates(BallState ball) {
		int[] ballcoordinates=new int[4];
		//yi
		ballcoordinates[0]=ball.getCenter().getY()-ball.getSize().getY();
		//ys
		ballcoordinates[1]=ball.getCenter().getY()+ball.getSize().getY();
		//xi
		ballcoordinates[2]=ball.getCenter().getX()-ball.getSize().getX();
		//xs
		ballcoordinates[3]=ball.getCenter().getX()+ball.getSize().getX();
		return ballcoordinates;
	}
	/*
	 * 	These are collision coordinates taken from TL, TR, BL, BR of each rectange.
	 * They are used to establish a set of points in between where a collision can happen
	 */
	/**

	 * @creates |result
	 * @post |result!=null
	 */
	private int[] wallCoordinates() {
		int[] wallcoordinates=new int[4];
		//xi
		wallcoordinates[0]=0;
		//xs
		wallcoordinates[1]=getBottomRight().getX();
		//yi
		wallcoordinates[2]=0;
		//ys
		wallcoordinates[3]=getBottomRight().getY();
		return wallcoordinates;
	}
	/*
	 * These are collision coordinates taken from TL, TR, BL, BR of each rectange.
	 * They are used to establish a set of points in between where a collision can happen
	 */
	/**
	 * @pre Enforce blocks in the field| (block.getBlockTL().getY()>=0 &&
	 *		| block.getBlockBR().getY()<=getBottomRight().getY() &&
	 *		| block.getBlockTL().getX()>=0 &&
	 *		|block.getBlockBR().getX()<=getBottomRight().getX()) ||
	 *		|(block.getBlockBR().equals(new Point(-250,-250)) &&
	 *		|block.getBlockTL().equals(new Point(-250,-250)))
	 * @pre block!=null
	 * @creates |result
	 * @post |result!=null
	 * 
	 */
	private int[] blockCoordinates(BlockState block) {
		int [] blockcoordinates=new int[4];
		//ys
		blockcoordinates[0]=block.getBlockBR().getY();
		//yi
		blockcoordinates[1]=block.getBlockTL().getY();
		//xs
		blockcoordinates[2]=block.getBlockBR().getX();
		//xi
		blockcoordinates[3]=block.getBlockTL().getX();
		return blockcoordinates;
	}
	/*
	 * These are collision coordinates taken from TL, TR, BL, BR of each rectange.
	 * They are used to establish a set of points in between where a collision can happen
	 */
	/**
	 *@pre |paddle!=null
	 *@creates |result
	 *@post| result!=null
	 */
	private int[] paddleCoordinates(PaddleState paddle) {
		int[] paddlecoordinates=new int[4];
		//yi
		paddlecoordinates[0]=paddle.getPosition().getY()-paddle.getSize().getY();
		//ys
		paddlecoordinates[1]=paddle.getPosition().getY()+paddle.getSize().getY();
		//xs
		paddlecoordinates[2]=paddle.getPosition().getX()+paddle.getSize().getX();
		//xi
		paddlecoordinates[3]=paddle.getPosition().getX()-paddle.getSize().getX();
		return paddlecoordinates;
	}
	
	/**
	 * 
	 * @pre |ball!=null
	 * @creates |result
	 * @mutates |this.unormalizedD
	 * @post |getUnormalizedD().equals(new Vector(crossx-ball.getCenter().getX(),crossy-ball.getCenter().getY()))
	 */
	private void Unormalized(BallState ball, int crossx, int crossy) {
		Vector unormalized=new Vector(crossx-ball.getCenter().getX(),crossy-ball.getCenter().getY());
		this.unormalizedD=unormalized;
		
	}
	/**
	 * @inspects
	 * 
	 */
	private  Vector getUnormalizedD() {
		return this.unormalizedD;
	}
	/*
	 * The direction d is given by the line between the center of the ball 
	 * and the point on the ball's surface where it hits the obstacle
	 * If d is normalized, i.e. ||d|| = sqrt(d . d) = 1, then dividing by (d . d) is of course not necessary.
	 * Method MirrorOver from Vector class considers d as normalized vector.
	 * You may use the method `Vector.mirrorOver` which implements this computation already.
	 */
	/**	
	 * @pre |ball!=null
	 * @post normalize|result.equals(getUnormalizedD().scaledDiv((int)Math.sqrt(Math.pow(getUnormalizedD().getX(),2 )+Math.pow(getUnormalizedD().getY(), 2))))
	 */
	private Vector findingD(BallState ball,int crossx, int crossy) {
		Unormalized(ball,crossx,crossy);
		Vector unormalized=getUnormalizedD();
		 Vector normalized=unormalized.scaledDiv((int)Math.sqrt(Math.pow(unormalized.getX(),2 )+Math.pow(unormalized.getY(), 2)));
		return normalized;
	}
	

}
