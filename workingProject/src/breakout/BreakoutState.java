package breakout;

// TODO: implement, document
import java.util.ArrayList;

public class BreakoutState {
	/*
	 *We have fail to implement the enforcement of the balls and blocks @invar in the Breakoutstate, these are find in the respective classes
	 */
	/**
	 *
	 *@invar |balls!=null
	 *@invar |blocks!=null
	 *@invar |bottomRight!=null
	 *@invar |paddle!=null
	 *@invar Paddle enforcement in the gamefield| paddle.getPosition().getX()>=0 && paddle.getPosition().getY()>=0 && paddle.getPosition().getX()<=50000 && paddle.getPosition().getY()<=30000
	 *@representationObject
	 */
	private ArrayList <BallState> balls=new ArrayList <BallState>();
	private ArrayList <BlockState> blocks= new ArrayList <BlockState>();
	private final Point bottomRight;
	private PaddleState paddle;
	private Vector unormalizedD;

	
		/**
		 * 
		 *@throws|balls==null
		 *@throws |blocks==null
		 *@throws |bottomRight==null
		 *@throws |paddle!=null
		 *@mutates |this
		 *@post |getBottomRight().equals(bottomRight)
		 *@post |getPaddle().equals(paddle)
		 */
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		if (balls==null||blocks==null||bottomRight==null||paddle==null) {
			throw new IllegalArgumentException("invalid argument");
		}

		this.paddle=paddle;
		for(BallState ball:balls) {
			this.balls.add(ball);
		}
		
		this.bottomRight=bottomRight;
		for(BlockState block:blocks) {
			this.blocks.add(block);
		}

	}

	/**
	 * 
	 *
	 * @inspects
	 */
	public BallState[] getBalls() {
		BallState[] balls=this.balls.toArray(new BallState[this.balls.size()]);
		return balls.clone();
	}
	/**
	 * 
	 * 
	 * @inspects
	 */
	public BlockState[] getBlocks() {
		BlockState[] blocks=this.blocks.toArray(new BlockState[this.blocks.size()]);
		return blocks.clone();
	}
	/**
	 * 
	 * @inspects
	 */
	public PaddleState getPaddle() {
		return paddle;
	}
	/**
	 * 
	 * @inspects
	 */
	public Point getBottomRight() {
		return bottomRight;
	}
	/**
	 * @mutates |this
	 *@pre |paddleDir==1 || paddleDir==-1 || paddleDir==0 
	 *
	 */
	
	public void tick(int paddleDir) {
		for (int i=0; i<balls.size();i++) {
			bouncePaddle(balls.get(i),i,getPaddle(),paddleDir);
			bounceBlock(balls.get(i),i);
			Point positionAfter=balls.get(i).getCenter().plus(balls.get(i).getVelocity());
			balls.set(i,balls.get(i).getNewPosition(positionAfter));
			bounceWall(balls.get(i),i);
			
			
		}
	}
		
		
	/**
	 * @mutates|this
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

			}
	}
	/**
	 * @mutates |this
	 * moves paddle position according to velocity
	 *@post |getPaddle().getPosition().equals(old(getPaddle().getPosition().minus(new Vector(10,0)))) || 
	 *		|getPaddle().getPosition().equals(old(getPaddle().getPosition()))
	 */
	public void movePaddleLeft() {
		Point value=getPaddle().getPosition().minus(new Vector(10,0));
		Point tl= getPaddle().getPosition().minus(getPaddle().getSize());
		if(tl.getX()>=0) {
			paddle=paddle.getNewPosition(value);

			}
	}
	/**
	 * @inspects |this
	 * @post |result==true || result==false
	 */
	public boolean isWon() {
		boolean value=false;
		if(blocks.size()==0) {
			value=true;
		}
		
		
		return value;
	}
	/**
	 *@inspects |this
	 *@post |result==true || result==false	 */
	public boolean isDead() {
		boolean value=false;
		if(balls.size()==0) {
			value=true;
		}

		
		return value;
	}
	/**
	 *@mutates |this
	 *@pre|paddleDir==1 || paddleDir==-1 || paddleDir==0 
	 *@pre|ball!=null
	 *@pre|paddle!=null
	 *@post|ball.getVelocity()!=null
	 * 
	 */
	private void bouncePaddle(BallState ball,int ballindex,PaddleState paddle, int paddleDir)

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
		//because the for loops can make the graphics go terrible slow.
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
							 balls.set(ballindex,ball.getNewVelocity(withoutpaddlevector.plus(new Vector(paddle.getPosition().getX(),paddle.getPosition().getY()).scaled(paddleDir*1/5))));}
					 }
					 
				 }
			 }
		 }
	}
	/**
	 *
	 * @pre|ball!=null
	 * @pre|blocks!=null
	 * @post |ball.getVelocity() != null
	 *
	 * 
	 */
	private void bounceBlock(BallState ball,int ballindex) {
		for (int i =0;i<blocks.size();i++) {
			//Collision coordinates: for defining a range of possible bouncing points
			BlockState block=blocks.get(i);
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
										balls.set(ballindex, balls.get(ballindex).getNewVelocity(ball.getVelocity().mirrorOver(d)));;
										//Based in the gui coordinates conversion. the point here is to set it to 0,0									
										blocks.remove(i);
										}

						}
						if(ballys>=blockyi && ballys<blockys && ballyi<blockyi) {
								Vector d= findingD(ball,ballposition,ballys);
								/*To avoid weird effects, it is important that a ball only bounces on an object when the direction from the ball (vector d) to that
								 *  object is at a sharp angle from the ball's current velocity (vector v).
								 *  You can check this easily by verifying that the dot product of the two vectors (d . v) is positive.
								 */
								if(d.product(ball.getVelocity())>0) {
									balls.set(ballindex, balls.get(ballindex).getNewVelocity(ball.getVelocity().mirrorOver(d)));;
									//Based in the gui coordinates conversion.The objective is to set it (0,0)
									blocks.remove(i);
									}
								}
								
							}

						}
			}
			
			//Face Left_right of the block collision
			//the unit change (+10) is randomly select for increasing the speed of the game
			//because the for loops can make the graphics go terribly slow
			//same principle, for this case the sensitivity and the program performance work well at this unit change in the for loops
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
								balls.set(ballindex, balls.get(ballindex).getNewVelocity(ball.getVelocity().mirrorOver(d)));;
								//Based in the gui coordinates conversion.The objective is to set it (0,0)
								blocks.remove(i);
									}

						}
						if(ballxs>=blockxi && ballxs<blockxs && ballxi<blockxi) {
							Vector d= findingD(ball,ballxs,ballposition);
							/*To avoid weird effects, it is important that a ball only bounces on an object when the direction from the ball (vector d) to that
							 *  object is at a sharp angle from the ball's current velocity (vector v).
							 *  You can check this easily by verifying that the dot product of the two vectors (d . v) is positive.
							 */
							if(d.product(ball.getVelocity())>0) {
								balls.set(ballindex, balls.get(ballindex).getNewVelocity(ball.getVelocity().mirrorOver(d)));;
							//Based in the gui coordinates conversion.The objective is to set it (0,0)
							blocks.remove(i);
								}
							}
								
					}

				}
			}

			
		}
	}
	/**
	 *
	 * @pre |ball!=null
	 * @pre |ballindex>=0 && ballindex<balls.size()
	 *@post |ball.getVelocity()!=null
	 */

	private void bounceWall(BallState ball,int ballindex) {	
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
								balls.remove(ballindex);
								//Based in the gui coordinates conversion.The objective is to set it (0,0)


					}
					if(ballys>=wallyi && ballys<wallys && ballyi<wallyi) {
							
						//Based in the gui coordinates conversion.The objective is to set it (0,0)
						Vector d= findingD(ball,ballposition,ballys);
						balls.set(ballindex, balls.get(ballindex).getNewVelocity(ball.getVelocity().mirrorOver(d)));;
							
						}

					}
			}
		}
		for(int ballposition=ballyi;ballposition<=ballys;ballposition+=7) {
			for(int blockposition=wallyi;blockposition<=wallys;blockposition+=8) {
				
				if (ballposition==blockposition) {
					if(ballxi<=wallxs && ballxi>wallxi && ballxs>wallxs) {
						Vector d= findingD(ball,ballxi,ballposition);
						balls.set(ballindex, balls.get(ballindex).getNewVelocity(ball.getVelocity().mirrorOver(d)));
						//Based in the gui coordinates conversion.The objective is to set it (0,0)


					}
					if(ballxs>=wallxi && ballxs<wallxs && ballxi<wallxi) {
						Vector d= findingD(ball,ballxs,ballposition);
						balls.set(ballindex, balls.get(ballindex).getNewVelocity(ball.getVelocity().mirrorOver(d)));
						//Based in the gui coordinates conversion.The objective is to set it (0,0)
						}
							
				}

			}
		}
	}
	/*
	 * 	 * These are the collision coordinates taken from TL, TR, BL, BR of each rectangle.
	 * They are used to establish a set of points where in between a collision can happen.
	 */
	/**

	 * 
	 * @pre Enforce balls in the field not as {@invar}| (ball.getCenter().getY()-ball.getSize().getY()>=0 &&
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
	 * 	These are collision coordinates taken from TL, TR, BL, BR of each rectangle.
	 * They are used to establish a set of points where in between a collision can happen
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
	 * These are collision coordinates taken from TL, TR, BL, BR of each rectangle.
	 * They are used to establish a set of points where in between a collision can happen
	 */
	/**
	 * @pre Enforce blocks in the field not as {@invar}| (block.getBlockTL().getY()>=0 &&
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
	 * These are collision coordinates taken from TL, TR, BL, BR of each rectangle.
	 * They are used to establish a set of points where in between a collision can happen
	 */
	/**
	 * 
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
	 * @mutates |this
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
