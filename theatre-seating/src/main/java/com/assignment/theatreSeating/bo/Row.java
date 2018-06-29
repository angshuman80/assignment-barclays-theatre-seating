package com.assignment.theatreSeating.bo;

public class Row {
	
	private int rowId;
	private Section section;
	
	public Row(int rowId , Section section){
		this.rowId = rowId;
		this.section = section;
	}
	public int getRowId() {
		return rowId;
	}
	public Section getSection() {
		return section;
	}
	
	

}
