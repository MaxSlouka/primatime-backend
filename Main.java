import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    // 1,
    // 2 -> 3, 4
    // 5 -> 6 -> 7
    List<Node> list = new ArrayList<>();
   /* list.add(node(1));
    list.add(node(2, new Node[]{ node(3), node(4) }));
    list.add(node(5, new Node[]{ node(6, new Node[]{ node(7) })})); */
    /* list.add(node(10));
    list.add(node(20, new Node[]{ node(30), node(40) }));
    list.add(node(50, new Node[]{ node(60, new Node[]{ node(70) })})); */
    list.add(node(10.5));
    list.add(node(20.3, new Node[]{ node(30.7), node(40.1) }));
    list.add(node(50.9, new Node[]{ node(60.2, new Node[]{ node(70.4) })}));

    // should return 4
    // because (1 + 2 + 3 + 4 + 5 + 6 + 7) / 7 = 4
    System.out.println(getMeanValue(list));
  }

  public static interface Node {
    public double getValue();
    public List<Node> getNodes();
  }

  public static double getMeanValue(List<Node> nodes) {
    // please implement algorithm for mean value of all given nodes
    // each node has own value and sub-nodes of the same structure,
    // mean value should be calculated across all values in the tree
    double[] sumCount = calculateSumAndCount(nodes);

    return sumCount[0] / sumCount[1];
  }

  private static double[] calculateSumAndCount(List<Node> nodes){
    double sum = 0;
    int count = 0;

    for (Node node : nodes){
      sum += node.getValue();
      count++;

      List<Node> subNodes = node.getNodes();
      if (subNodes != null && !subNodes.isEmpty()){
        double[] subSumCount = calculateSumAndCount(subNodes);
        sum+= subSumCount[0];
        count+= subSumCount[1];
      }
    }

    return new double[]{sum, count};
  }

  // builders

  public static Node node(double value) {
    return node(value, new Node[]{});
  }

  public static Node node(double value, Node[] nodes) {
    Node[] nodesCopy = Arrays.copyOf(nodes, nodes.length);
    return new Node() {
      public double getValue() {
        return value;
      }
      public List<Node> getNodes() {
        return Arrays.asList(nodesCopy);
      }
    };
  }
}
