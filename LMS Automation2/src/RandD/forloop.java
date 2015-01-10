package RandD;

public class forloop {


		  public static void main(String[] arg) {
		    int limit = 10;
		    int sum = 0;
		    for (int i = 1, j = 0; i <= limit; i++, j++) {
		      sum += i * j;
		    }
		    System.out.println(sum);
		  }
		}