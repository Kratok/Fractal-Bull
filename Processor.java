public class Processor
{
  private static boolean run = true;
  public int folds = 1; //now counts EACH crease. should make it as the power of creases made for(folds) {objects=*objects}
  public byte[] lineObject = new byte[1000000]; {lineObject[0] = 2;}
  byte[] buffer = new byte[1000000];
  public int height; // count how many lines go up or down
  public int width; // count how many lines go horizontally
  void foldLine()
  {
    for (int i = 0; i < folds; i++) buffer[i] = lineObject[i];
    for (int i = 0; i < folds; i++)
    { 
      //if(folds > lineObject.length)break;
      if (buffer[i] == 3) buffer[i] = 0;
      else buffer[i]++;
    }
    for (int i = 0; i < folds; i++)
    {
      lineObject[folds+i] = buffer[folds-1-i];
    }
    folds = folds * 2;
  }
  void cycleLine()
  {
    height = 0;
    width = 0;
    for (int i = 0; i < folds; i++)
    {
      switch (lineObject[i])
      {
        case 0: height--;
        break;
        case 1: width++;
        break;
        case 2: height++;
        break;
        case 3: width--;
        break;
      }
      if(height > maxY) maxY = height;
      if(height < minY) minY = height;
      if(width > maxX) maxX = width;
      if(width < minX) minX = width;
    }
  }
}
//Take the whole object by its beginning, rotate it 90 to the right, and add it to the object
//for every fold create a byte that is 0 1 2 3 to lead the cardinal directions for a fold
// Starting from the bottom (red) 
// 2, 2 3, 2 3 0 3, "" 0 1 0 3, "" 0 1 2 1 0 1 0 3,
// Start with last line object, flip all values, increment, insert
