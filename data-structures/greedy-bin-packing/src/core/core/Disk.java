package core;
import adt.*;
import java.io.PrintStream;
import java.util.*;
import java.lang.*;

public class Disk implements Comparable<Disk>{
    IntQueueImpl folders;
    private int id;
    private static final int CAPACITY = 1_000_000;


    public Disk(int key){
        this.id=key;
        this.folders=new IntQueueImpl();
    }

    public int getId() {
        return id;
    }

    public int compareTo(Disk b)
    {
        if(this.getFreeSpace()==b.getFreeSpace())
            return 0;
        else if(this.getFreeSpace()>b.getFreeSpace())
            return 1;
        else
            return -1;
    }


    public void addFolder(int size) {
        folders.put(size);
    }

    public void printFolders(PrintStream out) {
        folders.printQueue(out);
    }

    public int getFreeSpace() {
        int used = 0;

        IntQueueImpl temp = new IntQueueImpl();

        while (!folders.isEmpty()) {
            int size = folders.get();
            used += size;
            temp.put(size);
        }

        // επαναφορά ουράς
        while (!temp.isEmpty()) {
            folders.put(temp.get());
        }

        return CAPACITY - used;
    }



}
