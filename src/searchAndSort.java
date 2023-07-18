import java.util.Arrays;
import java.util.Scanner;

public class searchAndSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input from the user
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }



        // Choose search type
        System.out.print("Choose search type (1 - Linear, 2 - Binary): ");
        int searchType = scanner.nextInt();
        int target;
        if (searchType == 2) {
            // Binary search requires sorted array
            Arrays.sort(arr);
        }
        System.out.print("Enter the target element: ");
        target = scanner.nextInt();

        // Choose sorting algorithm
        System.out.println("Choose sorting algorithm (1 - Bubble Sort, 2 - Merge Sort): ");
        int sortAlgorithm = scanner.nextInt();
        long startTime, endTime;

        // Perform search operation
        if (searchType == 1) {
            startTime = System.currentTimeMillis();
            int linearSearchIndex = linearSearch(arr, target);
            endTime = System.currentTimeMillis();

            if (linearSearchIndex != -1) {
                System.out.println("Element found at index " + linearSearchIndex);
            } else {
                System.out.println("Element not found");
            }
        } else if (searchType == 2) {
            startTime = System.currentTimeMillis();
            int binarySearchIndex = binarySearch(arr, target);
            endTime = System.currentTimeMillis();

            if (binarySearchIndex != -1) {
                System.out.println("Element found at index " + binarySearchIndex);
            } else {
                System.out.println("Element not found");
            }
        } else {
            System.out.println("Invalid search type selected.");
            return;
        }

        System.out.println("Time taken for the search operation: " + (endTime - startTime) + " milliseconds");

        // Perform sorting operation
        if (sortAlgorithm == 1) {
            startTime = System.currentTimeMillis();
            bubbleSort(arr);
            endTime = System.currentTimeMillis();
        } else if (sortAlgorithm == 2) {
            startTime = System.currentTimeMillis();
            mergeSort(arr);
            endTime = System.currentTimeMillis();
        } else {
            System.out.println("Invalid sorting algorithm selected.");
            return;
        }

        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Time taken for the sorting operation: " + (endTime - startTime) + " milliseconds");
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
