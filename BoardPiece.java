import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class BoardPiece 
{
	private int r, c;
	private int w, h;
	private int dir;
	private Color color;
	private List<int[]> turnPoss;
	//private int debug = 0;

	public BoardPiece(int r, int c, int dir, Color color)
	{
		this.r = r;
		this.c = c;
		w = 8;
		h = 8;
		this.dir = dir;
		this.color = color;

		turnPoss = new ArrayList<int[]>();
	}

	public void addTurn(int[] turn)
	{
		//System.out.println("Add turn: " + turn[0] + ", " + turn[1] + ", " + turn[2]);
		turnPoss.add(turn);
	}

	public int getR()
	{
		return r;
	}

	public int getC()
	{
		return c;
	}

	public int getW()
	{
		return w;
	}
	
	public List<int[]> getTurnPoss()
	{
		return turnPoss;
	}

	public void move()
	{
		if(!turnPoss.isEmpty() && getR() == turnPoss.get(0)[0] && getC() == turnPoss.get(0)[1])
		{
			//System.out.println("Turn: " + debug++);
			dir = turnPoss.get(0)[2];
			turnPoss.remove(0);
		}
		
		if(dir == 0)
		{	
			c++;	
		}

		else if(dir == 90)
		{
			r--;
		}

		else if(dir == 180)
		{
			c--;
		}

		else if(dir == 270)
		{
			r++;
		}
	}

	public int getH()
	{
		return h;
	}
	
	public int getDir()
	{
		return dir;
	}

	public Color getColor()
	{
		return color;
	}
}
