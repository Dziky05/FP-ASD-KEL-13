import java.util.ArrayList;
import java.util.List;

// Minimal tree structure to reflect project outline
public class TreeNode {
    private String label;
    private List<TreeNode> children;

    public TreeNode(String label) {
        this.label = label;
        this.children = new ArrayList<>();
    }

    public String getLabel() { return label; }
    public List<TreeNode> getChildren() { return children; }
    public void addChild(TreeNode child) { children.add(child); }

    public void print(String prefix) {
        System.out.println(prefix + label);
        for (TreeNode c : children) c.print(prefix + "  ");
    }
}
