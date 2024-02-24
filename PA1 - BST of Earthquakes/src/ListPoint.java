// author: Grant Zobel
// assignment: Homework BST Earthquake
// description
public class ListPoint {
    private ListPoint next;
    private String country;
    private String month;
    private int year;
    private Double Magnitude;


    public ListPoint(String c, Double ma, String m, int y){
        this.country = c;
        this.Magnitude = ma;
        this.month = m;
        this.year = y;
    }
    public ListPoint getNext(){
        return next;
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
    public void setNext(ListPoint n){
        this.next=n;
    }
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
}
