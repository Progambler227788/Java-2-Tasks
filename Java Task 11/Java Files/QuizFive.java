// TODO Name: 
// TODO Date: 4/21/2024

public class QuizFive{

	public static void printLast(int[] theArray) {
		System.out.println(theArray.length-1); //FIXME 1 (print last item)
	}


	public static boolean containsDessert(String[] theFood) {
		boolean dessert = false;
		//FIXME 2 (search for "ice cream", "pie" and "cake" in the array)
		for(String f : theFood){
		    if ( f.equals("ice cream" ) || f.equals("pie" ) ){
		       dessert = true;
		       break;
		    }
		    else if(f.equals("cake" ) ){
		        dessert = false;
		    }
		}
		
		return dessert;
	}

	public static int sumEven(int[] theNumbers) {
		//FIXME 3 (return a sum of values from the array)
		int sum = 0;
		for(int i = 0; i <theNumbers.length; i++){
		    if( i % 2 == 0){
		        sum += theNumbers[i];
		    
		    }
		    
		}
		return sum;
	}

	public static void main(String[] args) {

		int values[] = new int[]{0, 1, 2, 3, 4, 5};
		printLast(values);
		values = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		printLast(values);

		String[] foods = new String[]{"taco", "pizza", "ice cream", "burger"};
		System.out.println(containsDessert(foods));
		foods = new String[]{"pie", "omelette", "toast", "cake"};
		System.out.println(containsDessert(foods));

		System.out.println(sumEven(values));

	}
}
