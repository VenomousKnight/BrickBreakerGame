import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	
	public int map[][];
	public int height;
	public int width;

	
	MapGenerator(int row, int col){
		
		map = new int[row][col];
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				map[i][j] = 1;
			}
		}
		height =150/row;
		width = 540/col;
		
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * width + 80, i * height + 50, width, height);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * width + 80, i * height + 50, width, height);
				}
			}
				
		}
	}
	
	public void setValue(int value, int row, int col) {
		map[row][col] = value;
		
	}

}
