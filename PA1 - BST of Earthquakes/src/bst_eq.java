import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.base.BSTElement;
import bridges.data_src_dependent.EarthquakeUSGS;
import java.util.List;


public class bst_eq {
    public static void main(String[] args) throws Exception {

        //create the Bridges object
        //Bridges bridges = new Bridges(36, "BRIDGES_USER_ID", "BRIDGES_API_KEY");
        Bridges bridges = new Bridges(16, "zobelg","1614310944535");
        // set title, description
        bridges.setTitle("A Binary Search Tree Example with Earthquake Data");
        bridges.setDescription("This example illustrates retrieving USGS earthquake data records and inserted into a binary search tree. Attributes of the quake are displayed at each node.");

        // set max quakes for this example
        final int MaxQuakes = 25;

        // Retrieve a list of MaxQuakes earthquake records 
        // from USGS using BRIDGES
        DataSource ds = bridges.getDataSource();
        List<EarthquakeUSGS> eqlist = ds.getEarthquakeUSGSData(MaxQuakes);


        // create a binary search tree and insert the EQ records into the tree
        BSTElement<Double, EarthquakeUSGS> root = null;
        for (int k = 0; k < MaxQuakes; k++) {
            BSTElement<Double, EarthquakeUSGS> bst_node =
                new BSTElement<Double, EarthquakeUSGS>(eqlist.get(k).getMagnitude(), eqlist.get(k));
            // set label of the node
            bst_node.setLabel(eqlist.get(k).getTitle() + "\n\nLat/Long: ( " +
                eqlist.get(k).getLatit() + "," + eqlist.get(k).getLongit() + " )\n\n" +
                eqlist.get(k).getTime());
            root = insert_R (root, bst_node);
        }

        // set some visual attributes
        root.setSize (30.);
        root.setColor("red");

        // TODO: Modify the insert function to color all the nodes, except the
        //       root node.

        // TODO: Write a function to traverse the tree and highlight the 
        // largest quake (color it in a different color and set it to a larger
        // size (using the setSize(sz) size ranges 1-50 and change its color to
        // orange


        //set visualizer type
        bridges.setDataStructure(root);
        // visualize the tree
        bridges.visualize();
    }
    // recursive insert method to insert nodes into a binary search tree
    public static BSTElement<Double, EarthquakeUSGS> insert_R (BSTElement<Double, EarthquakeUSGS> rt,
        BSTElement<Double, EarthquakeUSGS> new_el) {
        if (rt == null)
            return new_el;
        else if (new_el.getKey() < rt.getKey())
            rt.setLeft(insert_R(rt.getLeft(), new_el));
        else
            rt.setRight(insert_R(rt.getRight(), new_el));
        return rt;
    }
}
