public class LinkedListDeque<T> {

    public class DLList{
        public T item;
        public DLList pre;
        public DLList next;
        public DLList(T x,DLList next, DLList pre){
            this.item = x;
            this.next = next;
            this.pre = pre;
        }
    }

    private DLList sentinel;
    private int size;

    // add node at first and update the pre and next message;
    public void addFirst(T item){
        DLList newList =new DLList(item,sentinel.next,sentinel);
        sentinel.next.pre = newList;
        sentinel.next = newList;
        size +=1;
    }
    // add node at last and update the pre and next message;
    public void  addLast(T item){
        DLList newList =new DLList(item,sentinel,sentinel.pre);
        sentinel.pre.next = newList;
        sentinel.pre = newList;
        size +=1;
    }
    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        return  false;
    }
    public int size(){
        return  size;
    }
    public void printDeque(){
        DLList p = sentinel;
        for(int i =0;i<size;i++){
            p=sentinel.next;
            System.out.println(p.item);
        }
    }
    public T removeFirst(){
        if(size==0) {
            return null;
        }
        T tmp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return tmp;
    }
    public T removeLast(){
        if(size==0) {
            return null;
        }
        T tmp = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        size -=1;
        return tmp;
    }
    public T get(int index){
        DLList p = sentinel;
        for(int i = 0;(i<index)&&(index<size);i++){
            p = p.next;
            if(i==(index-1)){
                return p.item;
            }
        }
        return null;
    }
    public LinkedListDeque(){
        // can not inatialize by two statements.
        sentinel =new DLList(null,null,null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
    }
    private T getRecursiveHelper(DLList nodes,int index){
        if(index==0){
            return nodes.item;
        }
        return getRecursiveHelper(nodes.next,index-1);

    }
    public T getRecursive(int index){
        if (index<size){
            return getRecursiveHelper(sentinel.next,index);
        }
        return null;

    }
}
