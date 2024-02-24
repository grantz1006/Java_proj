// author: Grant Zobel
// assignment:  HW6
// description: This is composed of a linked list representing the nodes of a graph, and an array matrix representing the edges.
//  It also builds said graph and returns the DFS of the graph as well as the number of islands.
import bridges.base.BSTElement;
import bridges.base.Element;
import bridges.base.SLelement;
import bridges.base.GraphAdjMatrixSimple;
import bridges.base.Edge;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.EarthquakeUSGS;

import java.util.List;
import java.util.Scanner;

public class Graph {
    public MinimalLinkedList list;
    public MinimalLinkedList test;
    public boolean[][] adjMat;

    public static void DFS(int j, Graph g){
        Node nodeat = g.list.nodeAt(j);
        System.out.println(nodeat.getCountry() + " " + nodeat.getMagnitude());
        nodeat.setVisited(true);

        for(int i = 0; i < g.adjMat[j].length; i++){
            if(g.adjMat[j][i] & !g.list.nodeAt(i).getVisited()){
                DFS(i, g);
            }
        }
    }
    public boolean[][] addVertexes(int numQua, GraphAdjMatrixSimple<String> graph){
        boolean[][] adjMat = new boolean[numQua][numQua];
        Node curr = this.list.head;
        int i = 0;
        while(curr.getNext() != null){
            Node oth = this.list.head;
            int j = 0;
            while(oth.getNext() != null){
                if(oth.getCountry().equals(curr.getCountry()) & (j != i)){
                    adjMat[i][j] = true;
                    Node n1 = this.list.nodeAt(i);
                    String s1 = n1.getCountry() + "->"+ n1.getMagnitude() + "->"+ n1.getMonth() + "->"+ n1.getYear();
                    Node n2 = this.list.nodeAt(j);
                    String s2 = n2.getCountry() + "->"+ n2.getMagnitude() + "->"+ n2.getMonth() + "->"+ n2.getYear();
                    graph.addEdge(s1, s2, 1);
                    graph.addEdge(s2, s1, 1);
                }
                oth = oth.getNext();
                j++;
            }
            curr = curr.getNext();
            i++;
        }
        return adjMat;
    }
    public void buildList(int maxQ, int eqVal, Bridges bridges, GraphAdjMatrixSimple<String> graph)throws Exception{
        this.list = new MinimalLinkedList();
        List<EarthquakeUSGS> mylist = bridges.getDataSource().getEarthquakeUSGSData(maxQ*10);
        SLelement<EarthquakeUSGS> head = new SLelement<EarthquakeUSGS>();
        int i = 0;


        while(list.size() < maxQ){
            String[] where = mylist.get(i).getLocation().split(",");
            String country = "";
            if (where.length>1) {
                country = where[1].trim();
            }else{
                country = where[0].trim();
            }

            String[] when = mylist.get(i).getTime().split(" ");
            String month = when[0];
            int year = Integer.parseInt(when[2]);
            Double magnitude = Math.round(mylist.get(i).getMagnitude()*100)/100.00;
            SLelement<EarthquakeUSGS> element = new SLelement<EarthquakeUSGS>(mylist.get(i));
            element.setLabel(mylist.get(i).getTitle());


            Node p = new Node(country, magnitude, month, year);
            if(p.getMagnitude() >= eqVal) {
                list.Insert(p);
                System.out.println(i + "\t" + country + "\t"+ month + "\t" + year + "\t" + magnitude);
                String vertex = country + "->"+ magnitude + "->"+ month + "->"+ year;
                graph.addVertex(vertex, "");
            }
            i++;
        }
        list.print();
    }

    public static void main(String[] args) throws Exception{
        // initialize Bridges
        Bridges bridges = new Bridges(17, "zobelg", "1614310944535");
        //  title, description
        bridges.setTitle("Earthquake Graph");
        bridges.setDescription("First 3000 quakes");
        GraphAdjMatrixSimple<String> graph = new GraphAdjMatrixSimple<String>();
        Graph g = new Graph();

        Scanner scnr = new Scanner(System.in);
        System.out.println("What is the minimum quake value you want?");
        int minQua = scnr.nextInt();
        final int numQua = 3000;
        g.buildList(numQua, minQua, bridges, graph);

        g.adjMat = g.addVertexes(numQua,graph);

        Node curr = g.list.head;
        MinimalLinkedList areas = new MinimalLinkedList();
        int i = 0;
        int val = 0;
        while(curr.getNext() != null){
            if(!curr.getVisited()){
                i++;
                DFS(val,g);
            }
            val++;
            curr = curr.getNext();
        }
        System.out.println(i + " Islands.");


        bridges.setDataStructure(graph);
        bridges.visualize();

    }

}
