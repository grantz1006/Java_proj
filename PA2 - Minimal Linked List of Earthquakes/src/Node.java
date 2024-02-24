// author: Grant Zobel
// assignment:  HW6
// description: This is the Node class used for the linked list.  In addition to the properties created in the last HW,
// it also contains a visited variable for the DFS
public class Node {

    private String country;
    private String month;
    private int year;
    private double Magnitude;
    private boolean visited;
    private Node next;

    public Node(String c, Double ma, String m, int y){
        this.country = c;
        this.Magnitude = ma;
        this.month = m;
        this.year = y;
        this.visited = false;
        this.next = null;
    }
    public String getCountry(){
        return country;
    }
    public String getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public double getMagnitude(){
        return Magnitude;
    }
    public boolean getVisited(){return visited;}
    public Node getNext(){return next;}



    public void setCountry(String c){
        this.country = c;
    }
    public void setMonth(String m){
        this.month=m;
    }
    public void setYear(int y){
        this.year=y;
    }
    public void setMagnitude(double ma){
        this.Magnitude=ma;
    }
    public void setVisited(boolean b){
        this.visited = b;
    }
    public void setNext(Node n){
        this.next = n;
    }
}
