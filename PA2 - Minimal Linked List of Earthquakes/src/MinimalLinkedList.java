// author: Grant Zobel
// assignment:  HW6
// description: This is the class that defines the linked list used for the graph nodes.  In addition to the methods added
// last time, it also contains an indexing function to translate the adjacency matrix to actual nodes

/* Import Bridges and relevant data source */
import bridges.connect.Bridges;
import bridges.base.SLelement;
import bridges.data_src_dependent.EarthquakeUSGS;

public class MinimalLinkedList {
    public Node head;
    public Node current;
    /* Given an SLelement with an EarthquakeUSGS value, set various visual properties */
    public void Insert (Node p)
    {
        if (head == null)
        {
            this.head = p;
            this.current = p;
        }
        else
        {
            Node lastOne = this.lastItem();
            lastOne.setNext(p);
            System.out.println("");
        }
    }
    public Node lastItem()
    {
        if (this.head == null)
        {
            return null;
        }
        else
        {
            Node current = this.head;
            // find the last node on the list
                while (current.getNext() != null) {
                    current = current.getNext();
                }

            return current;
        }
    }

    public Node print()
    {
        if (this.head == null)
        {
            return null;
        }
        else
        {
            Node current = this.head;
            int i = 1;
            // find the last node on the list
            while (current.getNext() != null)
            {
                System.out.println(i + ":  " + current.getCountry() + "->"+ current.getMagnitude() + "->"+ current.getMonth() + "->"+ current.getYear());
                current = current.getNext();
                i++;
            }
            return current;
        }
    }

    public int size()
    {
        if (this.head == null)
        {
            return 0;
        }
        else
        {
            Node current = this.head;
            int i = 0;
            // find the last node on the list
            while (current.getNext() != null)
            {
                //System.out.println(i + ":  " + current.getCountry() + "->"+ current.getMagnitude() + "->"+ current.getMonth() + "->"+ current.getYear());
                current = current.getNext();
                i++;
            }
            return i;
        }
    }
    public void Remove (double value)
    {
        int size = this.size();
        if (head == null)
        {
            System.out.println("Empty list");
            return;
        }
        // Needs to be deleted and if it is first point
        if (head.getMagnitude() < value)
        {
            head = head.getNext();
        }
        Node p = this.head;
        Node current = p.getNext();
        while (current.getNext() != null)
        {
            if (current.getMagnitude() < value)
            {
                p.setNext(current.getNext());
                current = p.getNext();
                continue;
            }
            p = current;
            current = current.getNext();
        }
        //recheck head
        if (head.getMagnitude() < value)
        {
            head = head.getNext();
        }
    }

    public Node nodeAt(int index){
        int i = 0;
        Node n = this.head;
        while(i < index & n.getNext() != null){
            n = n.getNext();
            i++;
        }
        if(i != index){
            return null;
        }
        else{
            return n;
        }
    }

}