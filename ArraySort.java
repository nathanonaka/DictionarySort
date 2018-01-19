/**
 * Assignment 9, generic sorting algorithms
 * insertion, bubble, selection, heap, merge, quick
 * @author Nathan
 *
 */
import java.util.Comparator;

public class ArraySort<E>
{
	//empty constructor
	public ArraySort()
	{

	}
	
	//insertion sort
	public void insertionSort(E[] data, Comparator<E> compare)
	{
		long start = 0;
		long end = 0;
		int swaps = 0;
		int compares = 0;
		
		start = System.nanoTime();
		
		for(int i=1; i<data.length; i++)
		{
			E key = data[i];
			int j = i-1;
			while(j>=0 && compare.compare(key, data[j]) < 0)
			{
				compares++;
				data[j+1] = data[j];
				j--;
				swaps++;
			}
			data[j+1] = key;
		}
		end = System.nanoTime();
		System.out.println("\n Insertion sort took " + (end-start) + " nanoseconds. " + compares + " compares. " + swaps + " swaps.");
	}
	
	//bubble sort
	public void bubbleSort(E[] data, Comparator<E> compare)
	{
		long start = 0;
		long end = 0;
		int swaps = 0;
		int compares = 0;
		
		start = System.nanoTime();
		
		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j <data.length-1; j++)
			{
				compares++;
				if(compare.compare(data[j], data[j+1]) > 0)
				{
					E temp = data[j];
					data[j]=data[j+1];
					data[j+1]=temp;
					swaps++;
				}
			}
		}
		end = System.nanoTime();
		System.out.println("\n Bubble sort took " + (end-start) + " nanoseconds. " + compares + " compares. " + swaps + " swaps.");

	}
	
	//selection sort
	public void selectionSort(E[] data, Comparator<E> compare)
	{
		long start = 0;
		long end = 0;
		int swaps = 0;
		int compares = 0;
		
		start = System.nanoTime();
		
		for(int i = 0; i<data.length; i++)
		{
			int index = i;
			for(int j = i+1; j<data.length; j++)
			{
				compares++;
				if(compare.compare(data[j], data[index]) < 0)
				{
					index = j;
				}
			}
			if(index != i)
			{
				E temp = data[index];
				data[index] = data[i];
				data[i] = temp;
				swaps++;
			}
			
		}
		end = System.nanoTime();
		System.out.println("\n Selection sort took " + (end-start) + " nanoseconds. " + compares + " compares. " + swaps + " swaps.");

	}
	
	
	//HEAP SORT FOUND ON HACKERRANK.COM
	int heapCompares = 0;
	int heapSwaps = 0;
	public void heapSort(E arr[], Comparator<E> compare)
	{
		long start = 0;
		long end = 0;
		start = System.nanoTime();
		
        int N = arr.length;
        //creating a heap
        MaxHeap heap = createHeap(arr, N, compare);

        //Repeating the below steps till the size of the heap is 1.
        while(heap.len > 1) {
            //Replacing largest element with the last item of the heap
        	heapSwaps++;
            swap(heap, 0, heap.len - 1);
            heap.len--;//Reducing the heap size by 1
            heapify(heap, 0, compare);
        }
        
        end = System.nanoTime();
		System.out.println("\n Heap sort took " + (end-start) + " nanoseconds. ");
		System.out.println(heapCompares + " compares. " + heapSwaps + " swaps.");
    }
    private MaxHeap createHeap(E arr[], int N, Comparator<E> compare) {
        MaxHeap maxheap = new MaxHeap(N, arr);
        int i = (maxheap.len - 2) / 2;

        while(i >= 0) {
            maxheap = heapify(maxheap, i, compare);
            i--;
        }

        return maxheap;
    }
    private MaxHeap heapify(MaxHeap maxheap, int N, Comparator<E> compare) {
        int largest = N;
        int left = 2 * N + 1; //index of left child
        int right = 2 * N + 2; //index of right child

        heapCompares++;
        if(left < maxheap.len && compare.compare(maxheap.arr[left], maxheap.arr[largest]) > 0){
            largest = left;
        }

        heapCompares++;
        if(right < maxheap.len && compare.compare(maxheap.arr[right], maxheap.arr[largest]) > 0){
            largest = right;
        }

        if(largest != N) {
        	heapSwaps++;
            swap(maxheap, largest, N);
            heapify(maxheap, largest, compare);
        }

        return maxheap;
    }
    private void swap(MaxHeap maxheap, int i, int j) {
        E temp;
        temp = maxheap.arr[i];
        maxheap.arr[i] = maxheap.arr[j];
        maxheap.arr[j] = temp;
    }
    class MaxHeap {
        int len;
        E arr[];
        MaxHeap(int l, E a[]) {
            len = l;
            arr = a;
        }
    }
    
    //MERGE SORT FOUND IN TEXTBOOK
	int mergeSwaps = 0;
	int mergeCompares = 0;
	public void mergeSort(E[]data, Comparator<? super E> compare)
	{
		long start = 0;
		long end = 0;
		start = System.nanoTime();
		
		mergeSort(data, compare, 0);
		
		end = System.nanoTime();
		System.out.println("\n Merge sort took " + (end-start) + " nanoseconds. ");
		System.out.println(mergeCompares + " compares. " + mergeSwaps + " swaps.");
	}
	private void mergeSort(E[] data, Comparator<? super E> compare, int x)
	{	
		if(data.length > 1)
		{
			int halfSize = data.length/2;
			E[] leftData = (E[]) new Comparable[halfSize];
			E[] rightData = (E[]) new Comparable[data.length - halfSize];
			System.arraycopy(data, 0, leftData, 0, halfSize);
			System.arraycopy(data, halfSize, rightData, 0, data.length-halfSize);
			mergeSort(leftData, compare, 0);
			mergeSort(rightData, compare, 0);
			merge(data, leftData, rightData, compare);
		}
	}
	
	private void merge(E[] output, E[] left, E[] right, Comparator<? super E> compare)
	{
		int i = 0;
		int j = 0;
		int k = 0;
		while(i<left.length && j<right.length)
		{
			mergeCompares++;
			if(compare.compare(left[i], right[j]) < 0)
				output[k++] = left[i++];
			else
				output[k++] = right[j++];
			mergeSwaps++;
		}
		while(i<left.length)
		{
			output[k++] = left[i++];
			mergeSwaps++;
		}
		while(j<right.length)
		{
			output[k++] = right[j++];
			mergeSwaps++;
		}
	}
	
	//QUICK SORT FOUND IN TEXTBOOK
	int quickCompares = 0;
	int quickSwaps = 0;
	public void quickSort(E[] data, Comparator<? super E> compare)
	{
		long start = 0;
		long end = 0;
		start = System.nanoTime();
		
		sort(data, 0, data.length-1, compare);
		
		end = System.nanoTime();
		System.out.println("\n Quick sort took " + (end-start) + " nanoseconds. ");
		System.out.println(quickCompares + " compares. " + quickSwaps + " swaps.");
	}
	private void sort(E[] data, int first, int last, Comparator<? super E> compare)
	{
		if(first<last)
		{
			int pivIndex = partition(data, first, last, compare);
			sort(data, first, pivIndex-1, compare);
			sort(data, pivIndex+1, last, compare);
		}
	}
	private int partition(E[]data, int first, int last, Comparator<? super E> compare)
	{
		E pivot = data[first];
		int up = first;
		int down = last;
		do
		{
			quickCompares++;
			while(up<last && (compare.compare(pivot, data[up]) >= 0))
				up++;
			quickCompares++;
			while(compare.compare(pivot, data[down]) < 0)
				down--;
			if(up<down)
			{
				quickSwaps++;
				swap(data, up, down);
			}
		}
		while(up<down);
		quickSwaps++;
		swap(data, first, down);
		return down;
	}
	
	private void swap(E[] data, int i, int j)
	{
		E temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	
	//print method to print out the array
	public void print(E[] data)
	{
		for(int i=0; i < data.length; i++)
		{
			System.out.print(data[i] + " ");
		}
	}
}
