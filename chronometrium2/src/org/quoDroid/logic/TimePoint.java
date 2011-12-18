package org.quoDroid.logic;

public class TimePoint {
	private int catId;
	private int uId;
	private int pointStar;
	private int pointEnd;
	
	public TimePoint(int catId, int uId) {
		super();
		this.catId = catId;
		this.uId = uId;
	}
	public void setPointStar(int pointStar) {
		this.pointStar = pointStar;
	}
	public void setPointEnd(int pointEnd) {
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
	public int getPointStar() {
		return pointStar;
	}
	public int getPointEnd() {
		return pointEnd;
	}
	
	

}
