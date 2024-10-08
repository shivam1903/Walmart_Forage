import java.util.ArrayList;
import java.util.Collections;

public class PowerOfTwoMaxHeap {
    private ArrayList<Integer> heap;
    private int childrenPower;
    private int numChildren;

    
    public PowerOfTwoMaxHeap(int childrenPower) {
        this.heap = new ArrayList<>();
        this.childrenPower = childrenPower;
        this.numChildren = (int) Math.pow(2, childrenPower);
    }

    
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    
    public int popMax() {
        if (heap.size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);

        if (heap.size() > 0) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return max;
    }

    
    private void heapifyUp(int index) {
        int parentIndex = getParentIndex(index);

        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private void heapifyDown(int index) {
        int largest = index;

        while (true) {
            int startChildIndex = getChildStartIndex(index);

            
            for (int i = 0; i < numChildren; i++) {
                int childIndex = startChildIndex + i;
                if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largest)) {
                    largest = childIndex;
                }
            }

            if (largest != index) {
                Collections.swap(heap, index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / numChildren;
    }

    private int getChildStartIndex(int index) {
        return numChildren * index + 1;
    }
    
    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2); 

        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(15);
        heap.insert(40);
        heap.insert(50);

        heap.printHeap();

        System.out.println("Max: " + heap.popMax());
        heap.printHeap();

        System.out.println("Max: " + heap.popMax());
        heap.printHeap();
    }
}
