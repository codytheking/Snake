import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board 
{
	private BoardPiece[][] board;
	private BoardPiece apple;
	private boolean collision;

	public Board()
	{
		board = new BoardPiece[50][50];

		int[] spot = randomSpot();
		apple = new BoardPiece(spot[0], spot[1], 0, Color.RED);  // apple shouldn't have a dir
		board[apple.getR()][apple.getC()] = apple;

		collision = false;
	}

	public void drawBoard(GraphicsContext gc, Snake snake)
	{
		updateBoard(snake);

		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[r].length; c++)
			{
				if(board[r][c] != null)
				{
					gc.setFill(board[r][c].getColor());
					gc.fillRect(c * board[r][c].getH(), r * board[r][c].getW(), board[r][c].getW(), board[r][c].getH());
				}
			}
		}
	}

	private void updateBoard(Snake snake)
	{
		resetBoard();

		for(int i = 0; i < snake.getBody().size(); i++)
		{
			BoardPiece p = snake.getBody().get(i); 
					
			if(isOcc(p.getR(), p.getC()) && p.getR() != apple.getR() && p.getC() != apple.getC())
			{
				collision = true;
				return;  // avoid IOE below
			}

			else if(isOcc(p.getR(), p.getC()))
			{
				resetApple();
				snake.addLen();
			}

			board[p.getR()][p.getC()] = p;				
		}
	}

	private void resetBoard()
	{
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[r].length; c++)
			{
				board[r][c] = null;
			}
		}

		board[apple.getR()][apple.getC()] = apple;
	}

	private int[] randomSpot()
	{
		int[] spot = {-1, -1};

		while(spot[0] < 0 || spot[0] >= board.length || spot[1] < 0 || spot[1] >= board[0].length || isOcc(spot[0], spot[1]))
		{
			spot[0] = (int) (board.length * Math.random());
			spot[1] = (int) (board[0].length * Math.random());
		}

		return spot;
	}

	public void resetApple()
	{
		resetBoard();

		int[] spot = randomSpot();
		apple = new BoardPiece(spot[0], spot[1], 0, Color.RED);  // again, apple shouldn't have a dir
		board[apple.getR()][apple.getC()] = apple;
	}

	private boolean isOcc(int r, int c)
	{
		if(r >= board.length || c >= board[0].length || r < 0 || c < 0)
		{
			return true;
		}

		return !(board[r][c] == null);
	}

	public boolean hasLost()
	{
		return collision;
	}
}
