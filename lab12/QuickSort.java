import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     * <p>
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item : q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted A Queue of unsorted items
     * @param pivot    The item to pivot on
     * @param less     An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are less than the given pivot.
     * @param equal    An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are equal to the given pivot.
     * @param greater  An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        if (unsorted.isEmpty()) return;

        while (!unsorted.isEmpty()) {
            Item temp = unsorted.dequeue();

            if (pivot.compareTo(temp) > 0) less.enqueue(temp);
            else if (pivot.compareTo(temp) < 0) greater.enqueue(temp);
            else equal.enqueue(temp);

        }

        return;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        // 当处理泛化类型时必须要确保 队列中的元素时可比较的；因此在定义函数之前加入<Item extends Comparable>
        if (items.size() <= 1) return items;


        Item pivot = getRandomItem(items);
        Queue<Item> less = new Queue<Item>(), equal = new Queue<Item>(), greater = new Queue<Item>();

        // 因为是静态方法，可以传递下去；静态方法可以访问静态数据成员，并可以更改静态数据成员的值。
        partition(items, pivot, less, equal, greater);

        less = quickSort(less);
        greater = quickSort(greater);

        Queue<Item> res = catenate(less, equal);
        res = catenate(res, greater);
        return res;

    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Mark");
        // result: Alice Ethan Mark Vanessa

        System.out.println(QuickSort.quickSort(students));

    }
}
