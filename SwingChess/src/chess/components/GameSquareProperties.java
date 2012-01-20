package chess.components;

import java.awt.Color;
import se.kth.mlanglet.types.Point;

/**
 * Calculates and holds data about a square on the chess board based on which index is provided
 * @author Mathias
 *
 */
public class GameSquareProperties {
	
	private String row;
	private String column;
	private Color color;
	private Point point;
	private int number;
	private int i;
	
	String rowStrings[] = {"A","B","C","D","E","F","G","H"};
	String columnStrings[] = {"8","7","6","5","4","3","2","1"};
	
	public GameSquareProperties(int index){
		if(index < 8){
			row = rowStrings[index];
			column = columnStrings[0];
			color = Color.WHITE;
			
			if(index % 2 != 0)
				color = Color.BLACK;
			
			point = new Point((index)*75, 0);
		}
		else if(index < 16){
			i = index - 7;
			
			row = rowStrings[i - 1];
			column = columnStrings[1];
			color = Color.BLACK;
			
			if(index % 2 != 0)
				color = Color.WHITE;
			
			point = new Point((i-1)*75, 75);
		}
		else if(index < 24){
			i = index - 15;
			
			row = rowStrings[i - 1];
			column = columnStrings[2];
			color = Color.WHITE;
			if(index % 2 != 0)
				color = Color.BLACK;
			
			point = new Point((i-1)*75, 150);
		}
		else if(index < 32){
			i = index - 23;
			
			row = rowStrings[i - 1];
			column = columnStrings[3];
			color = Color.BLACK;
			if(index % 2 != 0)
				color = Color.WHITE;
			
			point = new Point((i-1)*75, 225);
		}
		else if(index < 40){
			i = index - 31;
			
			row = rowStrings[i - 1];
			column = columnStrings[4];
			color = Color.WHITE;
			
			if(index % 2 != 0)
				color = Color.BLACK;
			
			point = new Point((i-1)*75, 300);
		}
		else if(index < 48){
			i = index - 39;
			
			row = rowStrings[i - 1];
			column = columnStrings[5];
			color = Color.BLACK;
			
			if(index % 2 != 0)
				color = Color.WHITE;
			
			point = new Point((i-1)*75, 375);
		}
		else if(index < 56){
			i = index - 47;
			
			row = rowStrings[i - 1];
			column = columnStrings[6];
			color = Color.WHITE;
			if(index % 2 != 0)
				color = Color.BLACK;
			
			point = new Point((i-1)*75, 450);
		}
		else if(index < 64){
			i = index - 55;
			
			row = rowStrings[i - 1];
			column = columnStrings[7];
			color = Color.BLACK;
			
			if(index % 2 != 0)
				color = Color.WHITE;
			
			point = new Point((i-1)*75, 525);
		}
		
		number = index;
	}
	
	public String getRow(){
		return row;
	}
	public String getColumn(){
		return column;
	}
	public Color getColor(){
		return color;
	}
	
	public Point getPoint(){
		return point;
	}
	
	public int getIndex(){
		return number;
	}
	
	public String toString(){
		String description = row + column + " | Index: " + number;
		return description;
	}
	
}
