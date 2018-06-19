package server;

public class Location {
	int level;
	int row;
	int column;
	
	public Location(int level, int row, int column) {
		this.level = level;
		this.row = row;
		this.column = column;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	
}
