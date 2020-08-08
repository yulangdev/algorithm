import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NQueen_9663 {
  public static int count = 0;

  public static void doit(int size, int row, int down, int leftDown, int rightDown) {
    if (row == size) {
      count += 1;
      return;
    }
    int movable = down | leftDown | rightDown;
    for (int i = 0; i < size; i++) {
      int column = 1 << i;
      if ((movable & column) != 0) continue;
      doit(size,
           row + 1, 
           down | column, 
           (leftDown | column) << 1,
           (rightDown | column) >> 1);
    }
  }

  public static void doit2(int size, int row, int down, int leftDown, int rightDown) {
    if (row == size) {
      count += 1;
      return;
    }
    int movable = ~(down | leftDown | rightDown | (-1 << size));    
    while (movable != 0) {
      int column = movable & (movable * -1);
      doit2(size,
            row + 1, 
            down | column, 
            (leftDown | column) << 1,
            (rightDown | column) >> 1);
      movable -= column;
    }
  }

  public static void main(String[] arguments) throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    int size = Integer.parseInt(bufferedReader.readLine());
    doit2(size, 0, 0, 0, 0);
    System.out.println(count);
  }
}
