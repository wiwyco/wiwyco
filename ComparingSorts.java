/* Winslow Conneen- COSC 3325 Assignment 2
 * Comparing the relative efficiency of algorithms insertion sort, merge sort, and quick sort
 * 11.16.2020
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
		System.out.println("INSERTION SORT:\nNumber of Integers: 10\nNumber of Comparisons:"
				+ insertionSort(decarand)
				+ "\n\nNumber of Integers: 100\nNumber of Comparisons:"
				+ insertionSort(hectorand)
				+ "\n\nNumber of Integers: 1000\nNumber of Comparisons:"
				+ insertionSort(kilorand)
				+ "\n\nNumber of Integers: 10000\nNumber of Comparisons:"
				+ insertionSort(megarand));

		//call Generate() method to fill arrays
		decarand = Generate(decarand);
		hectorand = Generate(hectorand);
		kilorand = Generate(kilorand);
		megarand = Generate(megarand);
		
				//call bubble sort and format
				mergeSort(decarand);
				System.out.println("\nMERGE SORT:\nNumber of Integers: 10\nNumber of Comparisons:"
						+ mergeComparisons);
				mergeComparisons = 0;
				
				mergeSort(hectorand);
				System.out.println("\nNumber of Integers: 100\nNumber of Comparisons:"
						+ mergeComparisons);
				mergeComparisons = 0;
				
				mergeSort(kilorand);
				System.out.println("\nNumber of Integers: 1000\nNumber of Comparisons:"
						+ mergeComparisons);
				mergeComparisons = 0;
				
				mergeSort(megarand);
				System.out.println("\nNumber of Integers: 10000\nNumber of Comparisons:"
						+ mergeComparisons);
				mergeComparisons = 0;
		
		//call Generate() method to fill arrays
		decarand = Generate(decarand);
		hectorand = Generate(hectorand);
		kilorand = Generate(kilorand);
		megarand = Generate(megarand);
		
				//call quickSort() and format
				quickSort(decarand, 0, 9);
				System.out.println("\nQUICK SORT:\n\nNumber of Integers: 10\nNumber of Comparisons:"
						+ quickComparisons);
				quickComparisons = 0;
				
				quickSort(hectorand, 0, 99);
				System.out.println("\nNumber of Integers: 100\nNumber of Comparisons:"
						+ quickComparisons);
				quickComparisons = 0;
				
				quickSort(kilorand, 0, 999);
				System.out.println("\nNumber of Integers: 1000\nNumber of Comparisons:"
						+ quickComparisons);
				quickComparisons = 0;
				
				quickSort(megarand, 0, 9999);
				System.out.println("\nNumber of Integers: 10000\nNumber of Comparisons:"
						+ quickComparisons);
				quickComparisons = 0;
				
				
		
	}
	
	/*
	 * Quick sort is an algorithm that pivots at a certain value and creates smaller 
	 * arrays that are less than or greater than the pivot value and merges them at the end
	 */
	
	static int quickComparisons = 0;
	public static void quickSort(int [] array, int l, int r)
	{
		if (l < r)
		{
			// find partition
			int s = partition(array, l, r);
			// sort left and right side of array
			quickSort(array, l, s-1);
			quickSort(array, s+1, r);
		}
		
	}
	
	public static int partition(int [] A, int l, int r)
	{
		//create partition at end
		int s = A[r];
		int i = l - 1;
		
		//sort elements around partition
		for(int j = l; j < r; j++)
		{
			quickComparisons++;
			if (A[j] <= s)
			{
				i++;
				
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		
		int temp = A[i+1];
		A[i+1] = A[r];
		A[r] = temp;
		
		return i+1;
	}
	
	
	 /*
	 * Merge sort splits the array recursively and then sorts it back into the correct order
	 */

	static int mergeComparisons = 0;
	
	public static int [] mergeSort(int [] array)
	{
		//split array
		int [] B = new int [(int) (array.length * 0.5)];
		int [] C = new int [array.length - (int) (array.length * 0.5)];
		int [] A = new int [array.length];
		if(array.length > 1)
		{
			int x = 0;
			if((int) (array.length * 0.5) < array.length - ((int) (array.length * 0.5)))
			{
				x = 1;
			}
			for(int i = 0; i < (int) (array.length * 0.5); i++)
			{
				B[i] = array[i];
				C[i] = array[array.length - ((int) (array.length * 0.5)) + i];
				
			}
			
			if(x == 1)
			{
				C[array.length - ((int) (array.length * 0.5)) - 1] = array[array.length - 1];
			}
			
			//keep splitting subarrays until length = 1
			mergeSort(B);
			mergeSort(C);
			// merge together
			A = merge(array, B, C);
		}
		
		int comparisons = mergeComparisons;
		
		return A;
	}
	
	public static int [] merge(int [] A, int [] B, int [] C)
	{
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		int p = B.length;
		int q = C.length;
		
		// sort smaller arrays into larger array
		while (i < p && j < q)
		{
			mergeComparisons++;
			if(B[i] <= C[j])
			{
				A[k] = B[i];
				i++;
			}
			else
			{
				A[k] = C[j];
				j++;
			}
			k++;
		}
		
		
		if (i == p)
		{
			int h = 0;
			for(int x = j; x <= (q - 1); x++)
			{
				A[k + h] = C[x];
				h++;
			}
		}
		else
		{
			int h = 0;
			for(int x = i; x <= (p - 1); x++)
			{
				A[k + h] = B[x];
				h++;
			}
		}
		
		return A;
	}
	
	/*
	 * Insertion sort finds the smallest value and removes it, adding it to a new array,
	 * and runs again
	 */
	public static long insertionSort(int [] array)
	{
		int comparisons = 0;
		
		for(int i = 1; i <= (array.length - 1); i++)
		{
			int v = array[i];
			int j = i - 1;
			
			//run through array finding the smallest element
			while(j >= 0 && array[j] > v)
			{
				comparisons++;
				array[j+1] = array[j];
				j--;
			}
			
			array[j + 1] = v;
		}
		
		//return the number of comparisons
		return comparisons;
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
