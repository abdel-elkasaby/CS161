public class MidtermPractice {

	public static void main(String[] args) {
	      float x = (float) 3.4;
	      String y = Float.toString(x)+3;
	      System.out.println(y);
	      
	      double num = 2.3;
	      double result = 3.5;
	      result += num;
	      System.out.println(result);
	      
	      int[][] grid = new int[2][2];
	      for (int row = 0; row < grid.length; row++) {
				String output = "";
				for (int column = 0; column < grid[0].length; column++) {
					output += "\t" + row + "," + column;
				}
				System.out.println(output);
	      }
	      String output2 = "";
	      int[] arr = {3,4,5};
	      for (int row = 0; row < arr.length; row++) {
	    	  output2 += arr[row] + "\n";
	      }
	      System.out.println(output2);
	      
	      String z = "35";
	      int number = Integer.parseInt(z);
	      System.out.println(number);
	}

}
