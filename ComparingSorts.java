/* Winslow Conneen- COSC 3325 Assignment 1
 * Comparing the relative efficiency of algorithms combsort, bubble sort, and selection sort
 * 10.11.2020
 * COSC 3325- Prof. Stephen Rainwater
 * 
 * This class is the main and only class of a program showcasing the efficiency of an algorithm's
 * ability to sort a random array of 10, 100, 1000, and 10000 digits in the range of 0 - 2^31.
 * 
 * 
 */

//Import random class
import java.util.Random;

public class ComparingSorts {

	public static void main(String [] args)
	{
		//initilize arrays to be filled with random numbers
		int [] decarand = new int [10];
		int [] hectorand = new int [100];
		int [] kilorand = new int [1000];
		int [] megarand = new int [10000];
		
		//call Generate() method to fill arrays
		decarand = Generate(decarand);
		hectorand = Generate(hectorand);
		kilorand = Generate(kilorand);
		megarand = Generate(megarand);
		
		//Use an in-print-statement-call to comb sort method and format
		System.out.println("COMBSORT:\n\nNumber of Integers: 10\nNumber of Comparisons:"
				+ combSort(decarand)
				+ "\n\nNumber of Integers: 100\nNumber of Comparisons:"
				+ combSort(hectorand)
				+ "\n\nNumber of Integers: 1000\nNumber of Comparisons:"
				+ combSort(kilorand)
				+ "\n\nNumber of Integers: 10000\nNumber of Comparisons:"
				+ combSort(megarand));

		//call Generate() method to fill arrays
		decarand = Generate(decarand);
		hectorand = Generate(hectorand);
		kilorand = Generate(kilorand);
		megarand = Generate(megarand);
		
		//call bubble sort and format
		System.out.println("\n\nBUBBLE SORT:\n\nNumber of Integers: 10\nNumber of Comparisons:"
				+ bubbleSort(decarand)
				+ "\n\nNumber of Integers: 100\nNumber of Comparisons:"
				+ bubbleSort(hectorand)
				+ "\n\nNumber of Integers: 1000\nNumber of Comparisons:"
				+ bubbleSort(kilorand)
				+ "\n\nNumber of Integers: 10000\nNumber of Comparisons:"
				+ bubbleSort(megarand));

		//call Generate() method to fill arrays
		decarand = Generate(decarand);
		hectorand = Generate(hectorand);
		kilorand = Generate(kilorand);
		megarand = Generate(megarand);
		
		//call selection sort and format
		System.out.println("\n\nSELECTION SORT:\n\nNumber of Integers: 10\nNumber of Comparisons:"
				+ selectionSort(decarand)
				+ "\n\nNumber of Integers: 100\nNumber of Comparisons:"
				+ selectionSort(hectorand)
				+ "\n\nNumber of Integers: 1000\nNumber of Comparisons:"
				+ selectionSort(kilorand)
				+ "\n\nNumber of Integers: 10000\nNumber of Comparisons:"
				+ selectionSort(megarand));
	}
	
	/*
	 * Selection sort method is a brute force method that scans the array index by index 
	 * to find the smallest value and put it in the starting place, then reduces the
	 * problem size by one. This method is initilized as a long to allow for a very large 
	 * integer to be returned. Its input is an array of size x and its output is an integer 
	 * value representing the number of comparisons.
	 */
	public static long selectionSort(int [] array)
	{
		
		int comparisons = 0;
		
		int x = array.length;
		
		//Initilize loop to traverse array x-1 times, presuming last pass is not needed as the array
		//would already be sorted
		for(int i = 0; i < x-1; i++)
		{
			//min value stores the smallest remaining value in each pass
			int min = i;
			//loop starts at the problem size of 
			for(int j = i; j < x; j++)
			{
				//increment comparisons
				comparisons++;
				
				if(array[j] < array[min])
				{
					min = j;
				}
			}
			
			//swap values
			int y = array[i];
			array[i] = array[min];
			array[min] = y;
		}
		
		//return the number of comparisons
		return comparisons;
	}
	/*
	 * Bubble sort compares adjacent values and swaps them, passing over the array multiple times,
	 * short circuiting when the array is sorted, or swaps = 0. The input is the an array of size x.
	 * The output is int value of the number of comparisons.
	 */
	public static long bubbleSort(int [] array)
	{
		int comparisons = 0;
		
		int x = array.length;
		
		//boolean value for short circuiting
		boolean sorted = false;
		
		while(sorted == false)
		{
			//temp value determines if there has been any swaps each pass
			int temp = 0;
			for( int j = 0; j < x-1; j++)
			{
				//increment comparisons
				comparisons++;
				if(array [j] > array[j+1])
				{
					//swap values
					int y = array[j];
					array[j] = array [j+1];
					array[j+1] = y;
					temp++;
				}
			}
			//if  no swaps, exit the loop
			if(temp == 0)
			{
				sorted = true;
			}
		}

		//return the number of comparisons
		return comparisons;
	}
	
	/*
	 * Combsort is similar to bubble sort but uses a gap (that can be calculated for a number set)
	 * set experimentally at ~1/1.3 for random values to swap values similar to bubble sorts
	 * technique. The input is an array of size x of random values. the output is a value of the number
	 * of comparisons made.
	 */
	public static long combSort(int [] array)
	{
		int comparisons = 0;
		
		int x = array.length;
		
		boolean swap = true;
		
		//gap is initialized as the size of the array to begin with and reduced with each pass by 1/1.3
		int gap = x;
		
		while (gap != 1 || swap == true)
		{
			gap = nextGap(gap);
			
			swap = false;
			
			for(int i = 0; i < x - gap; i++)
			{
				//increment comparisons
				comparisons++;
				if(array[i] > array[i + gap])
				{
					//swap values
					int y = array[i];
					array[i] = array [i + gap];
					array[i + gap] = y;
					
					swap = true;
				}
			}
		}

		//return the number of comparisons
		return comparisons;
	}
	
	public static int nextGap (int gap)
	{
		//best shrink value is found to 1.3 experimentally by creators of combSort
		
		gap = (gap*10)/13;
		if (gap < 1)
		{
			return 1;
		}
		return gap;
	}
	
	public static int [] Generate( int [] array)
	{
		// number generator is created
		Random gen = new Random();
		//  each position in an array is filled with a random
		//  integer up to maximum MAX_VALUE
		for(int i = 0; i < array.length; i++)
		{
			array[i] = gen.nextInt(Integer.MAX_VALUE);
		}
		
		return array;
	}
}
