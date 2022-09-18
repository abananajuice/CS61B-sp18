package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation {
    private static int scale;
    private WeightedQuickUnionUF grid;
    private ArrayList<Integer> opened_site=new ArrayList();
    private int vir_top;
    private int vir_bottom;
    public Percolation(int N)                // create N-by-N grid, with all sites initially blocked
    {
        printAruException(N);
        scale = N;
        vir_top = scale * scale;
        vir_bottom =vir_top + 1;

        grid = new WeightedQuickUnionUF(N*N + 2);
        for(int i=0;i < N;i++){   //create virtual site
            grid.union(i,vir_top);
            grid.union(N*N-1-i,vir_bottom);
        }
    }
    public void open(int row, int col)       // open the site (row, col) if it is not open already
    {
        // add Exception
       printBouException(row);
       printBouException(col);

        if (isOpen(row,col)){
            return;
        }

        for(int i = 0;i < opened_site.size();i++){
            union(xyTo1d(row,col),opened_site.get(i));
        }
        opened_site.add(xyTo1d(row,col));

    }
    public boolean isOpen(int row, int col)  // is the site (row, col) open?
    {
        printBouException(row);
        printBouException(col);
        return opened_site.contains(xyTo1d(row,col));
    }
    public boolean isFull(int row, int col)  // is the site (row, col) full?
    {
        printBouException(row);
        printBouException(col);
        if(isOpen(row, col)){
            return grid.connected(xyTo1d(row,col),vir_top);
        }
        return false;
    }
    public int numberOfOpenSites()           // number of open sites
    {
        return opened_site.size();
    }
    public boolean percolates()              // does the system percolate?
    {
        return grid.connected(vir_bottom,vir_top);
    }
    private int xyTo1d(int x,int y){
        return x * scale + y;
    }

    private void union(int x,int y){
        int a = x-y;
        if(Math.abs(a)==1){
            if((x/scale)==(y/scale)) {
                grid.union(x, y);
                return;
            }
        }
        else if (Math.abs(a) == scale) {
            grid.union(x,y);
            return;
        }
        return;
    }

    private void printAruException(int x){
        if(x<=0)
        {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private void printBouException(int x){
        if((x<0)||(x>(scale-1))){
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
    public static void main(String[] args)   // use for unit testing (not required)
    {

    }
}
