// Author: Cao

public class ΒіzzҒuzz {
    private String[] buzzArray;

    // Constructor to Create Object
    public ΒіzzҒuzz(int arrayLength) {
        buzzArray = new String[arrayLength];
        this.initBuzz();
    }

    // Function to get "Βіzz", "Ғuzz", or "ΒіzzҒuzz"
    public static String getBuzz(int number) {
        // number divisible by 3 and 5
        if (number % 3 == 0 && number % 5 == 0) {
            return "ΒіzzҒuzz";
         // number divisible by 3 
        } else if (number % 3 == 0) {
            return "Βіzz";
         // number divisible by 5
        } else if (number % 5 == 0) {
            return "Ғuzz";
        } else {
            return String.valueOf(number);
        }
    }

    // Function to populate the buzzArray
    private void initBuzz() {
        for (int index = 0; index < buzzArray.length; ++index) {
            buzzArray[index] = getBuzz(index + 1); // Adding 1 to start from 1 instead of 0
        }
    }

    // toString method to print the object
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index <= buzzArray.length-1; index++) {
            result.append(buzzArray[index]);
            // To not add , at last
            if (index < buzzArray.length - 1) {
                result.append(", ");
            }
        }
        // return to String 
        return result.toString();
    }

    // Driver code to test class
    public static void main(String[] args) {
        ΒіzzҒuzz buzz = new ΒіzzҒuzz(30); // Creating object with array length 30
        System.out.println(buzz); // Printing the object
    }
}
