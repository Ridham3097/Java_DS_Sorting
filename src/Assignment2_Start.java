import java.util.Arrays;

/* Comp10205 - Sorting Assignment
   ADD Your Authorship and answers to Questions here :

   I,Ridham Patel, 000831171 certify that this material is my original work.
   No other person’s work has been used without due acknowledgement.
   I have not made my work available to anyone else.

   Answer 1:    aSort -   Merge Sort
                bSort -   Selection Sort
                cSort -   Insertion Sort
                dSort     Quick Sort
                eSort -   Selection Sort
   Answer 2:    30 elements  fastest to lowest :
                            cSort -    Insertion Sort
                            bSort -    Selection Sort
                            aSort -    Merge Sort
                            dSort -    Quick Sort
                            eSort -    Selection Sort
   Answer 3:    300 elements fastest to lowest :
                            aSort -     Merge Sort
                            dSort -     Quick Sort
                            cSort -     Insertion Sort
                            bSort -     Selection Sort
                            eSort -     Selection Sort
   Answer 4:  YES, My Algorithm line up with BIG O Notation for 3000 Elements
                Merge Sort     - O(N LOG N)
                Selection Sort - O(n^2)
                Insertion Sort - O(n^2)
                Quick Sort     - O(LOG N)
                Selection Sort - O(n^2)
   Answer 5:   Selection Sort best performance of the basic step. No Basic step does n0t
               affect on my selection of algorithm for 3000 element because it will have more steps and
               and more time compare to other algorithm.
   Answer 6:   Quick Sort



 _________________________________________________

*/

public class Assignment2_Start {

    /**
     * The swap method swaps the contents of two elements in an int array.
     *
     * @param array containing the two elements.
     * @param a     The subscript of the first element.
     * @param b     The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp;

        temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * The ____Merge__________ sort - manages first call
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return int how many steps are done in this algorithm
     */
    public static int aSort(int array[]) {
        return doASort(array, 0, array.length - 1);
    }

    /**
     * The doASort method uses the ______Recursive______________ algorithm to sort
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end   The ending subscript of the list to sort
     */
    private static int doASort(int array[], int start, int end) {
        int pivotPoint;
        int count=0;            // count int to count how many times comparison done
        int count1=0;           // count int to count how many times comparison done
        int count2=0;           // count int to count how many times comparison done
        if (start < end) {
            // Get the pivot point.
            int[] q=new int[2];
            q = part(array, start, end);
            pivotPoint =q[0];
            count += q[1];
            // Note - only one +/=
            // Sort the first sub list.
            int a =doASort(array, start, pivotPoint - 1);
            count1 +=a;
            // Sort the second sub list.
            int b =doASort(array, pivotPoint + 1, end);
            count2 +=b;
        }
        return count+count1+count2;
    }

    /**
     * The partition method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array The array to partition.
     * @param start The starting subscript of the area to partition.
     * @param end   The ending subscript of the area to partition.
     * @return The subscript of the pivot value.
     */
    private static int[] part(int array[], int start, int end) {
        int pivotValue;    // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid;           // To hold the mid-point subscript
        int count =0;      // count int to count how many times comparison done
        // see http://www.cs.cmu.edu/~fp/courses/15122-s11/lectures/08-qsort.pdf
        // for discussion of middle point
        // Find the subscript of the middle element.
        // This will be our pivot value.
        mid = (start + end) / 2;

        // Swap the middle element with the first element.
        // This moves the pivot value to the start of
        // the list.
        swap(array, start, mid);

        // Save the pivot value for comparisons.
        pivotValue = array[start];

        // For now, the end of the left sub list is
        // the first element.
        endOfLeftList = start;

        // Scan the entire list and move any values that
        // are less than the pivot value to the left
        // sub list.
        for (int scan = start + 1; scan <= end; scan++) {
            count++;
            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }

        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);

        // Return the subscript of the pivot value.
        return new int[]{endOfLeftList,count};
    }

    /**
     * An implementation of the _______Selection_______________ Sort Algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */

    public static int bSort(int[] array) {
        int count=0;     // count int to count how many times comparison done
        int startScan;   // Starting position of the scan
        int index;       // To hold a subscript value
        int minIndex;    // Element with smallest value in the scan
        int minValue;    // The smallest value found in the scan

        // The outer loop iterates once for each element in the
        // array. The startScan variable marks the position where
        // the scan should begin.
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
            // Assume the first element in the scannable area
            // is the smallest value.
            minIndex = startScan;
            minValue = array[startScan];

            // Scan the array, starting at the 2nd element in
            // the scannable area. We are looking for the smallest
            // value in the scannable area.
            for (index = startScan + 1; index < array.length; index++) {
                count++;
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }

            // Swap the element with the smallest value
            // with the first element in the scannable area.
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
        return count;
    }

    /**
     * An implementation of the ________Insertion______________ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     */
    public static int cSort(int[] array) {
        int count=0;         // count int to count how many times comparison done
        int unsortedValue;  // The first unsorted value
        int scan;           // Used to scan the array

        // The outer loop steps the index variable through
        // each subscript in the array, starting at 1. The portion of
        // the array containing element 0  by itself is already sorted.
        for (int index = 1; index < array.length; index++) {
            // The first element outside the sorted portion is
            // array[index]. Store the value of this element
            // in unsortedValue.
            unsortedValue = array[index];

            // Start scan at the subscript of the first element
            // outside the sorted part.
            scan = index;

            // Move the first element in the still unsorted part
            // into its proper position within the sorted part.
            while (scan > 0 && array[scan - 1] > unsortedValue) {
                count++;
                array[scan] = array[scan - 1];
                scan--;
            }

            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }
        return count;
    }


    /**
     * completes a _____Quick______ sort on an array
     *
     * @param array the unsorted elements - will be sorted on completion
     */
    public static int dSort(int array[]) {
        int length = array.length;
        return doDSort(array, 0, length - 1);
    }

    /**
     * private recursive method that splits array until we start at array sizes of 1
     *
     * @param array       starting array
     * @param lowerIndex  lower bound of array to sort
     * @param higherIndex upper bound of array to sort
     */

    private static int doDSort(int[] array, int lowerIndex, int higherIndex) {
        int count=0;         // count int to count how many times comparison done
        int count1 =0;       // count int to count how many times comparison done
        int count2=0;        // count int to count how many times comparison done
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            int a= doDSort(array, lowerIndex, middle);
            count1+=a;
            // Below step sorts the right side of the array
            int b = doDSort(array, middle + 1, higherIndex);
            count2+=b;
            // Now put both parts together
            int c = part1(array, lowerIndex, middle, higherIndex);
            count+=c;
        }
        return count+count1+count2;
    }

    /**
     * Puts two smaller sorted arrays into one sorted array
     *
     * @param array       provided in two sorted parts (1,4,9,2,3,11)
     * @param lowerIndex  the location of the first index
     * @param middle      - the middle element
     * @param higherIndex - the upper bound of the sorted elements
     */
    private static int part1(int[] array, int lowerIndex, int middle, int higherIndex) {
        int count=0;      // count int to count how many times comparison done
        int[] mArray = new int[higherIndex - lowerIndex + 1];
        for (int i = lowerIndex; i <= higherIndex; i++) {
            mArray[i - lowerIndex] = array[i];
        }
        // Part A - from lowerIndex to middle
        // Part B - from middle + 1 to higherIndex
        int partAIndex = lowerIndex;
        int partBIndex = middle + 1;
        int m = lowerIndex;
        while (partAIndex <= middle && partBIndex <= higherIndex) {
            count++;
            // Place items back into Array in order
            // Select the lowestest of the two elements
            if (mArray[partAIndex - lowerIndex] <= mArray[partBIndex - lowerIndex]) {
                array[m] = mArray[partAIndex - lowerIndex];
                partAIndex++;
            } else {
                array[m] = mArray[partBIndex - lowerIndex];
                partBIndex++;
            }
            m++;
        }
        // Copy the remainder of PartA array (if required)
        while (partAIndex <= middle) {
            array[m] = mArray[partAIndex - lowerIndex];
            m++;
            partAIndex++;
        }
        return count;
    }

    /**
     * Sorting using the ___Selection______ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     */
    public static int eSort(int[] array) {
        int count=0;      // count int to count how many times comparison done
        int lastPos;     // Position of last element to compare
        int index;       // Index of an element to compare

        // The outer loop positions lastPos at the last element
        // to compare during each pass through the array. Initially
        // lastPos is the index of the last element in the array.
        // During each iteration, it is decreased by one.
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
            // The inner loop steps through the array, comparing
            // each element with its neighbor. All of the elements
            // from index 0 thrugh lastPos are involved in the
            // comparison. If two elements are out of order, they
            // are swapped.
            for (index = 0; index <= lastPos - 1; index++) {
                count++;
                // Compare an element with its neighbor.
                if (array[index] > array[index + 1]) {
                    // Swap the two elements.

                    swap(array, index, index + 1);
                }
            }

        }
        return count;
    }

    /**
     * Print an array to the Console
     *
     * @param A array to be printed
     */
    public static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%5d ", A[i]);
        }
        System.out.println();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] A1;
        int[] B1;
        int[] C1;
        int[] D1;
        int[] E1;
        System.out.printf("Assignment#2 Sorting and Performance Analysis\n");
        System.out.printf("Completed by _____________Ridham P___________________\n");
        for(int j=10;j<3001;j=j*10){
            int size = 3 *j;
            if(size==3000){
                size = size*10;
            }
            int[] A = new int[size];
            int[] B =new int[size];
            int[] C =new int[size];
            int[] D =new int[size];
            int[] E =new int[size];

            // Create random array with elements in the range of 0 to SIZE - 1;
            for (int i = 0; i < size; i++) {
                A[i] = (int) (Math.random() * size);
                B[i] = (int) (Math.random() * size);
                C[i] = (int) (Math.random() * size);
                D[i] = (int) (Math.random() * size);
                E[i] = (int) (Math.random() * size);
            }

            System.out.printf("\n\nComparison for array size of %d\n\n", size);
            A1 = Arrays.copyOf(A, A.length);
            long startTime = System.nanoTime();
            int a = aSort(A1);
            long elapsedTime = System.nanoTime() - startTime;

            System.out.printf("Number of compares for sort a =%10d times =%10d ns  Basic Step =  %6.1f ns", a , elapsedTime,(double) elapsedTime / a);

            System.out.println( " ");
            B1 = Arrays.copyOf(B, B.length);
            long startTime1 = System.nanoTime();
            int b = bSort(B1);
            long elapsedTime1 = System.nanoTime() - startTime1;

            System.out.printf("Number of compares for sort b =%10d times =%10d ns  Basic Step =  %6.1f ns", b , elapsedTime1,(double) elapsedTime1 /b);
            System.out.println( " ");
            C1 = Arrays.copyOf(C, C.length);
            long startTime2 = System.nanoTime();
            int c = cSort(C1);
            long elapsedTime2 = System.nanoTime() - startTime2;

            System.out.printf("Number of compares for sort c =%10d times =%10d ns  Basic Step =  %6.1f ns", c , elapsedTime2,(double) elapsedTime2 /c);

            System.out.println( " ");
            D1 = Arrays.copyOf(D, D.length);
            long startTime3 = System.nanoTime();
            int d = dSort(D1);
            long elapsedTime3 = System.nanoTime() - startTime3;

            System.out.printf("Number of compares for sort d =%10d times =%10d ns  Basic Step =  %6.1f ns", d , elapsedTime3,(double) elapsedTime3 /d);

            System.out.println( " ");
            E1 = Arrays.copyOf(E, E.length);
            long startTime4 = System.nanoTime();
            int e = eSort(E1);
            long elapsedTime4 = System.nanoTime() - startTime4;

            System.out.printf("Number of compares for sort e =%10d times =%10d ns  Basic Step =  %6.1f ns", e , elapsedTime4,(double) elapsedTime4 /e);


        }


    }
}

