package org.quoDroid.logic;


public class ActivityP {
	private double coordX;
	private double coordY;
	private double radius;
	private String name;
	private String description;
	private int categoryId;
	private int userId;
	
	private int startPoint;
	private int endPoint;
	
	
	public String getMessage() {		
		
		String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(new java.util.Date().getTime() - startPoint));
		return "You are at " + name + " doing " + description + " " + date;
		
	}
	
	public String getProfileUrl() {
		return "http://chronometrium.apphb.com/Profile/" + userId;
	}
	
	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getRadius() {
		return radius;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getUserId() {
		return userId;
	}

	public boolean isInside(double x, double y) {
		//return radius > Math.sqrt((coordX-x)*(coordX-x) + (coordY-y)*(coordY-y));
		double d2r = (Math.PI / 180.0);
		double dist =  6367444.6571225*2*
				Math.asin(
						Math.sqrt(
								Math.sin(d2r*(coordX - x)/2)*Math.sin(d2r*(coordX - x)/2)
								+ Math.cos(d2r*y)*Math.cos(d2r*coordY)*Math.sin(d2r*(coordY - y)/2)*Math.sin(d2r*(coordY - y)/2)
						)
				);
		System.out.println("result is here - " + dist);
		return radius > dist;

	}


	public ActivityP(double coordX, double coordY, double radius, String name,
			String description, int catId, int user) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.name = name;
		this.description = description;
		this.radius = radius;
		categoryId = catId;
		userId = user;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	public int getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

}
