package org.quoDroid.logic;

public class TimePoint {
	private int catId;
	private int uId;
	private double pointStar;
	private double pointEnd;
	
	public TimePoint(int catId, int uId) {
		super();
		this.catId = catId;
		this.uId = uId;
	}
	public void setPointStar(double pointStar) {
		this.pointStar = pointStar;
	}
	public void setPointEnd(double pointEnd) {
		this.pointEnd = pointEnd;
	}
	
	public boolean isReady() {
		return (pointStar>0 && pointEnd>0);
	}
	public int getCatId() {
		return catId;
	}
	public int getuId() {
		return uId;
	}
	public double getPointStar() {
		return pointStar;
	}
	public double getPointEnd() {
		return pointEnd;
	}
	
	

}
