import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Snake
{
	private List<BoardPiece> snake;
	private int len;
	private int dir;
	private Color color;
	
	public Snake(int startLen, Color color)
	{
		snake = new ArrayList<BoardPiece>();
		len = startLen;
		dir = 0;
		this.color = color;
		
		snake.add(new BoardPiece(20, 30, dir, this.color));
		
		for(int i = 1; i < len; i++)
		{
			snake.add(new BoardPiece(20, 30 - i, dir, this.color));
		}
	}
	
	public List<BoardPiece> getBody()
	{
		return snake;
	}
	
	public void move()
	{	
		for(BoardPiece p: snake)
		{
			p.move();
		}
	}
	
	public void addLen()
	{
		BoardPiece p = snake.get(snake.size() - 1);
		int r = -1, c = -1;
		
		if(p.getDir() == 0)
		{
			r = p.getR();
			c = p.getC() - 1;
		}
		
		else if(p.getDir() == 90)
		{
			r = p.getR() + 1;
			c = p.getC();
		}
		
		else if(p.getDir() == 180)
		{
			r = p.getR();
			c = p.getC() + 1;
		}
		
		else if(p.getDir() == 270)
		{
			r = p.getR() - 1;
			c = p.getC();
		}
		
		snake.add(new BoardPiece(r, c, p.getDir(), p.getColor()));
		
		List<int[]> turnPoss = snake.get(snake.size() - 2).getTurnPoss();
		for(int i = 0; i < turnPoss.size(); i++)
		{
			snake.get(snake.size() - 1).addTurn(turnPoss.get(i));
		}
	}
	
	public void changeDir(int d)
	{
		int[] turnSpot = new int[3];
		
		if(Math.abs(d - dir) != 180 && (d == 0 || d == 90 || d == 180 || d == 270))
		{
			turnSpot[0] = snake.get(0).getR();
			turnSpot[1] = snake.get(0).getC();
			turnSpot[2] = d;
			dir = d;
			
			for(BoardPiece p: snake)
			{
				p.addTurn(turnSpot);
			}
		}
	}
}
