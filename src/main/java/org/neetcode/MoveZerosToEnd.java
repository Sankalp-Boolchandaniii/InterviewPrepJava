public class MoveZerosToEnd {

    // Function to move zeros to the end using two pointers
    public static void moveZeros(int[] arr) {
        int n = arr.length;
        int right = 0; // Pointer to track the next position for a non-zero element

        // Traverse the array with 'left' pointer
        for (int left = 0; left < n; left++) {
            // If the element at 'left' is non-zero, we swap it with the element at 'right'
            if (arr[left] != 0) {
                // Swap elements at 'left' and 'right'
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                // Increment the 'right' pointer
                right++;
            }
        }
    }

    // Helper function to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main function for testing
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 0, 3, 4, 0, 5};
        
        System.out.println("Before moving zeros:");
        printArray(arr);
        
        moveZeros(arr);

        System.out.println("After moving zeros:");
        printArray(arr);
    }
}
