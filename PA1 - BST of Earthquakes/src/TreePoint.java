public class TreePoint {
    private TreePoint left;
    private TreePoint right;
    private String country;
    private String month;
    private int year;
    private double Magnitude;

    public TreePoint(String c, Double ma, String m, int y){
        this.country = c;
        this.Magnitude = ma;
        this.month = m;
        this.year = y;
        this.left = null;
        this.right = null;
    }
    public TreePoint getLeft(){
        return left;
    }
    public TreePoint getRight(){
        return right;
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
    public Double getMagnitude(){
        return Magnitude;
    }
    public void setLeft(TreePoint l){
        this.left=l;
    }
    public void setRight(TreePoint r){this.right=r;}
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
