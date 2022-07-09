
public class ArrayDeque<T> {
    private T[] items;
    private int length;
    private  int size;
    private int nextfirst;
    private int nextlast;



    public ArrayDeque(){
        items = (T []) new Object[20];
        size = 0;
        this.length = 20;
        this.nextfirst = 10;
        this.nextlast = 11;
    }

    private void resize(int capacity){
        int start = this.nextfirst==this.size?0:this.nextfirst+1;
        int end = this.nextlast==0?this.size:this.nextlast-1;

        T[] newitems = (T []) new Object[capacity];
        if(start<=end){
            System.arraycopy(items, start, newitems, 0, start-end+1);
        }
        else{
            System.arraycopy(items,start,newitems,0,size-start);
            System.arraycopy(items,0,newitems,size-start,start);
        }
        this.nextfirst = capacity-1;
        this.nextlast = this.size;
        this.length = capacity;
        items = newitems;
    }
    private void balance(){
        if(((double)this.size/this.length<0.25)&(this.length>20)) {
            int start = this.nextfirst==this.size?0:this.nextfirst+1;
            int end = this.nextlast==0?this.size:this.nextlast-1;
            int newlength = this.length/2;

            T[] newitems = (T []) new Object[newlength];
            if(start<=end){//bug at here
                System.arraycopy(items, start, newitems, 0, start-end+1);
            }
            else{
                System.arraycopy(items,start,newitems,0,size-start);
                System.arraycopy(items,0,newitems,size-start,start);
            }
            this.nextfirst = newlength-1;
            this.nextlast = this.size;
            this.length = this.length/2;
            items = newitems;
        }

    }


    public void addFirst(T item){
        if(this.size==this.length){
            resize(items.length*2);
        }

        items[this.nextfirst] = item;
        this.size +=1;
        this.nextfirst = this.nextfirst==0?this.length-1:this.nextfirst-1;

    }

    public void addLast(T item){
        if(this.size==this.length){
            resize(items.length*2);
        }

        items[this.nextlast] = item;
        this.size +=1;
        this.nextlast = (this.nextlast+1)%this.length;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int index=this.nextfirst+1;
        for(int i=0;i<size;i++){
            System.out.println(items[index]);
            index = index==this.length-1?0:index+1;
        }
    }


    public T removeFirst(){
        if(size!=0) {
            this.nextfirst = this.nextfirst==this.length-1?0:this.nextfirst+1 ;
            T tmp = items[this.nextfirst];
            items[this.nextfirst ] = null;
            this.size -= 1;
            balance();
            return tmp;
        }
        return null;
    }


    public T removeLast(){
        if(size!=0) {
            this.nextlast = this.nextlast==0?this.length-1:this.nextlast-1;
            T tmp = items[this.nextlast];
            items[nextlast] = null;
            this.size -= 1;
            balance();
            return tmp;
        }
        return null;
    }

    public T get(int index){
        if(index<size){
            return items[(index+this.nextfirst+1)%this.length];
        }
        return null;
    }

}
