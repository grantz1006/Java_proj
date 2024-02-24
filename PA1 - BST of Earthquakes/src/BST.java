import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.base.BSTElement;
import bridges.data_src_dependent.EarthquakeUSGS;
import java.util.List;

public class BST
{
    TreePoint root;
    private void insertBST (TreePoint point) {
        TreePoint trav = this.root;

        while(trav.getLeft() != null || trav.getRight() != null){
            if((int)(point.getMagnitude()*100) < (int)(trav.getMagnitude()*100)){
                if(trav.getLeft() == null){
                    trav.setLeft(point);
                    break;
                }else{
                    trav = trav.getLeft();
                }
            }else{
                if(trav.getRight() == null){
                    trav.setRight(point);
                    break;
                }else{
                    trav = trav.getRight();
                }
            }
        }

        if(trav.getLeft() == null && trav.getRight() == null){
            if((int)(point.getMagnitude()*100) < (int)(trav.getMagnitude()*100)){
                trav.setLeft(point);
            }else{
                trav.setRight(point);
            }
        }
    }

    private void updateRoot(TreePoint curr, TreePoint point)
    {
        point.setMonth(curr.getMonth());
        point.setCountry(curr.getCountry());
        point.setMagnitude(curr.getMagnitude());
        point.setYear(curr.getYear());
    }

    private static BST buildTree(MinimalLinkedList QuakesList, int value) {
        if (QuakesList.head == null) {
            return null;
        } else {
            BST ret = new BST();
            ret.root = new TreePoint("", (double) (value), "", 0);
            ListPoint curr = QuakesList.head;
            ListPoint closest = QuakesList.head;
            while (curr.getNext() != null) {
                if ((curr.getMagnitude() - value) * (curr.getMagnitude() - value) < (closest.getMagnitude() - value) * (closest.getMagnitude() - value)) {
                    closest = curr;
                }
                curr = curr.getNext();
            }

            curr = QuakesList.head;
            while (curr.getNext() != null) {
                if (curr.equals(closest)) {
                    ret.updateRoot(new TreePoint(curr.getCountry(), curr.getMagnitude(), curr.getMonth(), curr.getYear()), ret.root);
                } else {
                    ret.insertBST(new TreePoint(curr.getCountry(), curr.getMagnitude(), curr.getMonth(), curr.getYear()));
                }
                curr = curr.getNext();
            }
            return ret;
        }
    }

    private static BSTElement<Double, TreePoint> insert_R (BSTElement<Double, TreePoint> rt,
                                                           BSTElement<Double, TreePoint> new_el) {
        if (rt == null)
            return new_el;
        else if (new_el.getValue().getMagnitude() < rt.getValue().getMagnitude())
            rt.setLeft(insert_R(rt.getLeft(), new_el));
        else
            rt.setRight(insert_R(rt.getRight(), new_el));
        return rt;
    }
    private static BSTElement<Double, TreePoint> tranlateBST (BST QuakeTree, int MaxQuakes, MinimalLinkedList QuakesList)
    {
        BSTElement<Double, TreePoint> root = null;
        root = new BSTElement<Double, TreePoint>(QuakeTree.root.getMagnitude(), QuakeTree.root);
        ListPoint curr = QuakesList.head;
        while (curr!=null)
        {
            String label = curr.getCountry() + "->"+ curr.getMagnitude() + "->"+ curr.getMonth() + "->"+ curr.getYear();
            TreePoint p = new TreePoint(curr.getCountry(),curr.getMagnitude(),curr.getMonth(),curr.getYear());
            BSTElement<Double, TreePoint> bst_point =
                    new BSTElement<Double, TreePoint>(curr.getMagnitude(), p);
            bst_point.setLabel(label);
            curr = curr.getNext();
            root = insert_R (root, bst_point);
        }
        return root;
    }
    public static void main(String[] args) throws Exception {

        //create the Bridges object
        //Bridges bridges = new Bridges(36, "BRIDGES_USER_ID", "BRIDGES_API_KEY");
        Bridges bridges = new Bridges(16, "zobelg","1614310944535");
        // set title, description
        bridges.setTitle("A Binary Search Tree Example with Earthquake Data");
        bridges.setDescription("This example illustrates retrieving USGS earthquake data records and inserted into a binary search tree. Attributes of the quake are displayed at each node.");

        // set max quakes for this example
        final int MaxQuakes = 250;

        // Retrieve a list of MaxQuakes earthquake records 
        // from USGS using BRIDGES

        MinimalLinkedList QuakesList = new MinimalLinkedList();
        QuakesList = MinimalLinkedList.buildList(MaxQuakes);

        BST QuakeTree = new BST();
        QuakeTree = buildTree(QuakesList, 3);

        // create a binary search tree and insert the EQ records into the tree

        BSTElement<Double, TreePoint> root = null;
        root = tranlateBST (QuakeTree, MaxQuakes, QuakesList);


        //set visualizer type
        bridges.setDataStructure(root);
        // visualize the tree
        bridges.visualize();
    }
}
