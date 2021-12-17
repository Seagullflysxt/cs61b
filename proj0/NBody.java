/** NBody is a class that will actually run your simulation. This class will have NO constructor.
	 The goal of this class is to simulate a universe specified in one of the data files. 
	 The first value is an integer N which represents the number of planets. 
	 The second value is a real number R which represents the radius of the universe, 
 *  @author Shi Xiangting
 */
public class NBody
{
	public static void main(String[] args) 
	{
		//Collecting All Needed Input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);         //

		/**Drawing the Background
		   First, set the scale so that it matches the radius of the universe. 
		   Then draw the image starfield.jpg as the background.
		*/
		StdDraw.setXscale(-2 * radius,2 * radius);
		StdDraw.setYscale(-2 * radius,2 * radius);
		StdDraw.picture(0,0,"images/starfield.jpg");

		//Drawing One Planet
		//The last one method in Planet.java,down

		//Drawing All of the Planets
		// Use the draw method you just wrote to draw each one of the planets in the planets array you created.
		int l = planets.length;
		for(int i = 0;i < l; i++)
			{
				planets[i].draw();
			}

		//Creating an Animation
		//Enable double buffering by calling enableDoubleBuffering()  e.g:StdDraw.enableDoubleBuffering();
		StdDraw.enableDoubleBuffering();

		//Create a time variable and set it to 0. Set up a loop to loop until this time variable is T.
		double time_va = 0;
		while(time_va < T)
		{
			time_va = time_va + dt;
			//For each time through the loop, do the following:
			//Create an xForces array and yForces array.
			double[] xForces = new double[l];
			double[] yForces = new double[l];
			//Calculate the net x and y forces for each planet, storing these in the xForces and yForces arrays respectively.
			for(int i = 0; i < l; i++)
				{
					xForces[i] = planets[i].calcNetForceExertedByX(planets);
					yForces[i] = planets[i].calcNetForceExertedByY(planets);
				}
			//Call update on each of the planets. This will update each planet¡¯s position, velocity, and acceleration.
			for(int i = 0; i < l ; i++)
				{
					planets[i].update(dt,xForces[i],yForces[i]);
				}

			//Draw the background image.
			StdDraw.setXscale(-2 * radius,2 * radius);
			StdDraw.setYscale(-2 * radius,2 * radius);
			StdDraw.picture(0,0,"images/starfield.jpg");

			//Draw all of the planets.
			
			for(int i = 0;i < l; i++)
				{
					planets[i].draw();
				}

			//Show the offscreen buffer (see the show method of StdDraw).
			StdDraw.show();
			//Pause the animation for 10 milliseconds
			StdDraw.pause(10);
		}

		//Printing the Universe
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) 
			{
				 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
			}
	}



	/**Your first method is readRadius.
	   Given a file name, it should return  *a double corresponding to the radius of the universe in that file,
	   e.g. readRadius("./data/planets.txt") should return 2.50e+11.
		*/
	public static double readRadius(String fileName)
		{
			In in = new In(fileName);
			
			int nums = in.readInt();
			double radius = in.readDouble();

			return radius;
		}

	/**Your next method is readPlanets. 
	   Given a file name, it should return an array of Planets corresponding to the planets in the file, 
	   e.g. readPlanets("./data/planets.txt") should return an array of five planets. 
	   readInt(), readDouble(), and readString()
	*/
	public static Planet[] readPlanets(String fileName)
		{
			In in = new In(fileName);

			int nums = in.readInt();
			double radius = in.readDouble();

			Planet[] ppp = new Planet[nums];
			for(int i = 0; i < nums; i++)
				{
	  			    double xP = in.readDouble();
					double yP = in.readDouble();
					double xV = in.readDouble();
					double yV = in.readDouble();
					double m = in.readDouble();
					String imgFileName = "images/" + in.readString();

					ppp[i] = new Planet(xP,yP,xV,yV,m,imgFileName);
				}
			return ppp;
		}


}
