public class Planet{
	private static final double Gravity = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	/* contructor */
	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos =xP;yyPos = yP;xxVel = xV;yyVel = yV;mass = m;imgFileName = img;

	}
	/*take in a Planet object and initialize an idential Planet object */
	public Planet(Planet P){
		xxPos =P.xxPos;yyPos = P.yyPos;xxVel = P.xxVel;yyVel = P.yyVel;mass = P.mass;imgFileName = P.imgFileName;
	}
	public double calcDistance(Planet P){
		return Math.sqrt(Math.pow((P.xxPos - xxPos),2) + Math.pow(( P.yyPos - yyPos),2));
	}	
	public double calcForceExertedBy(Planet P){
		return Gravity*(mass * P.mass) / Math.pow(calcDistance(P),2);
	}
	public double calcForceExertedByX(Planet P){
		double dx = P.xxPos - xxPos;
		return ( calcForceExertedBy(P) * dx ) / calcDistance(P);
	}
	public double calcForceExertedByY(Planet P){
		double dy = P.yyPos - yyPos;
		return ( calcForceExertedBy(P) * dy ) / calcDistance(P);

	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double res=0;
		int index = 0;
		while(index<allPlanets.length){
			if (!(this.equals(allPlanets[index]))){
				res += calcForceExertedByX(allPlanets[index]);	
			}
			index++;
		}
		return res;
	}


	public double calcNetForceExertedByY(Planet[] allPlanets){
		double res=0;
		int index = 0;
		while(index<allPlanets.length){
			if (!(this.equals(allPlanets[index]))){
				res += calcForceExertedByY(allPlanets[index]);	
			}
			index++;
		}
		return res;
	}

	public void update(double dt,double fX,double fY){
		double aNetX = fX / mass;
		double aNetY = fY / mass;

		xxVel = aNetX * dt + xxVel;
		yyVel = aNetY * dt + yyVel;

		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;

	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+ imgFileName);

	}
}



	

	


