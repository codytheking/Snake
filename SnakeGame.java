/**
 * This is the runner class for the game Snake.
 * 
 * @author Cody King
 * 
 * 1.0: 6/24/2017
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class SnakeGame extends Application
{
	private int screenWidth, screenHeight;
	
	private boolean paused;

	private Board board;
	private Snake snake;
	private Timeline tl;
	private Canvas canvas;
	private GraphicsContext gc;

	public SnakeGame()
	{
		screenWidth = 400;
		screenHeight = 400;
		
		paused = true;

		board = new Board();
		snake = new Snake(5, Color.GREEN);
	}

	@Override 
	public void start(Stage stage) 
	{
		canvas = new Canvas(screenWidth, screenHeight);
		gc = canvas.getGraphicsContext2D();
		tl = new Timeline(new KeyFrame(Duration.millis(200), e -> run(gc)));
		tl.setCycleCount(Timeline.INDEFINITE);
		canvas.setFocusTraversable(true);

		// handle mouse and key events

		/*canvas.setOnMouseClicked(e -> 
        {
            speed *= 2;
        });*/

		canvas.setOnKeyPressed(e ->
		{
			if(e.getCode() == KeyCode.RIGHT)
			{
				snake.changeDir(0);
			}

			else if(e.getCode() == KeyCode.UP)
			{
				snake.changeDir(90);
			}

			else if(e.getCode() == KeyCode.LEFT)
			{
				snake.changeDir(180);
			}

			else if(e.getCode() == KeyCode.DOWN)
			{
				snake.changeDir(270);
			}

			else if(e.getCode() == KeyCode.P)
			{
				if(paused)
				{
					paused = false;
					
					/*if(board.hasCollided())
					{
						board.resetGame();
					}*/
				}
				else
				{
					paused = true;
				}
			}
		});

		stage.setTitle("Snake");
		stage.setScene(new Scene(new StackPane(canvas)));
		stage.show();
		tl.play();
	}

	private void run(GraphicsContext gc)
	{
		// color for background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, screenWidth, screenHeight);
		
		if(!paused)
		{
			snake.move();
		}

		board.drawBoard(gc, snake);
		
		// color for text
		gc.setFill(Color.WHITE);
		
		if(board.hasLost())
		{
			gc.fillText("You lose!", 175, 75);
			tl.pause();
		}
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
