import java.util.List;
import java.util.Arrays;
import java.lang.Math;

/* Import Bridges and relevant data source */
import bridges.connect.Bridges;
import bridges.base.SLelement;
import bridges.data_src_dependent.EarthquakeUSGS;

public class MinimalLinkedList {
  public ListPoint head; 
  public ListPoint current;
  /* Given an SLelement with an EarthquakeUSGS value, set various visual properties */
  private void Insert (ListPoint p)
  {
        if (head == null)
        {
            this.head = p;
            this.current = p;
        }
        else
        {
            ListPoint lastOne = this.lastItem();
            lastOne.setNext(p);
        }     
  }
  private ListPoint lastItem()
  {
      if (this.head == null) 
      {
          return null;
      }
      else
      {
            ListPoint current = this.head;
            // find the last node on the list
            while (current.getNext() != null)
            {
                current = current.getNext();
            }
            return current;
      }
  }
  public ListPoint print()
  {
      if (this.head == null) 
      {
          return null;
      }
      else
      {
            ListPoint current = this.head;
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
            ListPoint current = this.head;
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
  private void Remove (double value)
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
      ListPoint p = this.head;
      ListPoint current = p.getNext();
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
}
