package com.assignment.theatreSeating.bo;

public class Section implements Comparable<Section> {
	
	private Integer sectionId;
	private int count;
	
	public Section(int sectionId, int count){
		this.sectionId = sectionId;
		this.count = count;
	}
	public int getSectionId() {
		return sectionId;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public int compareTo(Section o) {
		// TODO Auto-generated method stub
		if(o==null || this==null || this.sectionId==null || o.sectionId==null) return 0;
	
		//ascending order
		return this.sectionId.compareTo(o.sectionId);
		
	}
	
	
	

}
