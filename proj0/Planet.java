/** Class that dipicts the motion of N bodies.
 *  @author Shi Xiangting
 */
public class Planet 
{		
		// 6 instance variables 
		public double xxPos;
		public double yyPos;
		public double xxVel;
		public double yyVel;
		public double mass;  //zhi liang
		public String imgFileName;

		////scientific notation
		private static final double G = 6.67e-11;     

		// 2 Body constructors that can initialize an instance of the Body class
		public Planet(double xP,double yP,double xV,double yV,
					double m,String img)
			{
				xxPos = xP;
				yyPos = yP;
				xxVel = xV;
				yyVel = yV;
				mass = m;
				imgFileName = img;
			}
		//The second constructor should take in a Body object and initialize an identical Body object (i.e. a copy).
		public Planet(Planet p)
		{
			this.xxPos = p.xxPos;
			this.yyPos = p.yyPos;
			this.xxVel = p.xxVel;
			this.yyVel = p.yyVel;
			this.mass = p.mass;
			this.imgFileName = p.imgFileName;
		}

		//a method called   *calcDistance    that calculates the distance between two Planets.
		//This method will take in a single Planet and should    *return a double   equal to the distance between the supplied planet and the planet that is doing the calculation
		public double calcDistance(Planet p)
			{
				double dx = p.xxPos - this.xxPos;
				double dy = p.yyPos - this.yyPos;
				double r = Math.sqrt(dx * dx + dy * dy);

				return r;
			}
		
		// a method called  *calcForceExertedBy   takes in a planet, and returns *a double   describing the force exerted on this planet by the given planet. 
		//You should be calling the calcDistance method in this method. 
		public double calcForceExertedBy(Planet p)
			{
				double rr = calcDistance(p);
				double force = G * this.mass * p.mass / (rr * rr);
				
				return force;
			}

		//*calcForceExertedByX and *calcForceExertedByY  , these two methods describe(return) the force exerted in the X and Y directions, respectively. 
		//Remember to check your signs! 
		public double calcForceExertedByX(Planet p)
			{
				double netForce = calcForceExertedBy(p);
			    double dx = p.xxPos - this.xxPos;
				double rr = calcDistance(p);
//				if (p.xxPos > this.xxPos)
//				{
//				}
//				else
//				{
//					dxx = - dxx;
//				}
				double fx = netForce * dx / rr;
				
				return fx;
			}
			public double calcForceExertedByY(Planet p)
			{
				double netForce = calcForceExertedBy(p);
				double dy = p.yyPos - this.yyPos;
				double rr = calcDistance(p);
				double fy = netForce * dy / rr;
				
				return fy;
			}
			// *calcNetForceExertedByX  and  *calcNetForceExertedByY,
			// take in an array of Planets and calculate the net X and net Y force exerted by all planets in that array upon the current Planet.
			// return the  double value 
			//ignore any planet in the array that is equal to the current planet.
			//e.g :samh.equals(samh) (which would return true)
			public double calcNetForceExertedByX(Planet[] allPlanets)
				{
					int l = allPlanets.length;
					double netForceX = 0;
					for(int i = 0; i < l; i++)
						{
							if(allPlanets[i].equals(this))
								{
									continue;
								}
							netForceX = netForceX + calcForceExertedByX(allPlanets[i]);
						}

					return netForceX;
				}
			public double calcNetForceExertedByY(Planet[] allPlanets)
				{
					int l = allPlanets.length;
					double netForceY = 0;
					for(int i = 0; i < l; i++)
						{
							if(allPlanets[i].equals(this))
								{
									continue;
								}
							netForceY = netForceY + calcForceExertedByY(allPlanets[i]);
						}

					return netForceY;
				}
			
			//Write a method  *update(dt, fX, fY) 
			//that uses the steps above to update the planet's position and velocity instance variables
			//(this method does not need to return anything).
			public void update(double dt,double fX,double fY)
				{
					//calculate the ax and ay
					double aX = fX / mass;
					double aY = fY / mass;

					//calculate the new velocity in x- and y-
					this.xxVel = this.xxVel + aX * dt;
					this.yyVel = this.yyVel + aY * dt;

					//Calculate the new position 
					this.xxPos = this.xxPos + dt * xxVel;
					this.yyPos = this.yyPos + dt * yyVel;


				}

			//  *draw    
			//uses the StdDraw API to draw the Planet's image at the Planet's position. 
			//The draw method should return nothing and take in no parameters.
			public void draw()
				{
					StdDraw.picture(xxPos,yyPos,imgFileName);
				}


		
}
