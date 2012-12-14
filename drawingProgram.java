import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import javax.swing.Timer;

public class drawingProgram implements ActionListener
{
  private BufferedImage buffer;
  private Graphics canvas;
  private Random rand = new Random();
  private Timer myTimer;
  private Processor objects;
  private Picture myPic;
  private static int WIDTH = 600;
  private static int HEIGHT = 600;
  private Color[] colors = new Color[10];
  {
    colors[0] = Color.red;
    colors[1] = Color.orange;
    colors[2] = Color.yellow;
    colors[3] = Color.green;
    colors[4] = Color.cyan;
    colors[5] = Color.blue;
    colors[6] = Color.magenta;
    colors[7] = Color.pink;
    colors[8] = Color.white;
    colors[9] = Color.black;

  }
  private byte radius;
  private int originX = WIDTH / 2;
  private int originY = HEIGHT / 2;
  private int lengthX;
  private int lengthY;
  private int objectCheck = 0;

  public void fold()
  {
    canvas.setColor(Color.WHITE);
    canvas.fillRect(0, 0, WIDTH, HEIGHT);
    objects.foldLine();
    objects.cycleLine();
    lengthX = 10;
    lengthY = 10;
    canvas.setColor(Color.BLACK);
    canvas.fillRect(10, 10, 10, 10);
    // for(int i = 0; i > objects.folds; i++)
    // { 
    originX = WIDTH/2;
    originY = HEIGHT/2;
    for(int i = 0; i < objects.folds; i++)
    {
      int dummyX = originX;
      int dummyY = originY;
      switch (objects.lineObject[i])
      {
        case 0: 
          dummyY = originY + lengthY;
          break;
        case 1:
          dummyX = originX + lengthX;
          break;
        case 2:
          dummyY = originY - lengthY;
          break;
        case 3:
          dummyX = originX - lengthX;
          break;
      }
    canvas.setColor(Color.BLACK);
    canvas.drawLine(originX, originY, dummyX, dummyY);
    originX = dummyX;
    originY = dummyY;
    }
    canvas.fillRect(200, 10, 10, 10);
    if (objectCheck == objects.folds) objectCheck = 0; 
    myPic.repaint();
  }

  public drawingProgram()
  {

    myPic = new Picture(WIDTH, HEIGHT);
    canvas = myPic.getOffScreenGraphics();
    canvas.setColor(Color.WHITE);
    canvas.fillRect(0, 0, WIDTH, HEIGHT);
    objects = new Processor();

    myPic.setTitle("WEASELS");
    myTimer = new Timer(1000, this); // miliseconds
    System.out.println("STARTING");
    myTimer.start();
  }

  public static void main(String[] args)
  {
    drawingProgram myDraw = new drawingProgram();

  }

  public void actionPerformed(ActionEvent arg0)
  {
    fold();
    myPic.repaint();
  }
}
