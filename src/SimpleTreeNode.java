import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;
    public int Level;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
        Level = -1;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    // ok
    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode.Children != null && ParentNode.Children.contains(NewChild)) {
            return;
        }

        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }

        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    // ok
    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (NodeToDelete.Children == null) {
            DeleteNodeSimple(NodeToDelete);
            return;
        }

        List<SimpleTreeNode<T>> listNodes = new ArrayList<>();
        GetAllNodesRec(NodeToDelete, listNodes);
        listNodes.add(NodeToDelete);

        for (SimpleTreeNode<T> node : listNodes) {
            DeleteNodeSimple(node);
        }
    }

    // ok
    public void DeleteNodeSimple(SimpleTreeNode<T> NodeToDelete) {
        NodeToDelete.NodeValue = null;

        NodeToDelete.Parent.Children.remove(NodeToDelete);
        if (NodeToDelete.Parent.Children.size() == 0) {
            NodeToDelete.Parent.Children = null;
        }

        NodeToDelete.Parent = null;
    }

    // ok
    public List<SimpleTreeNode<T>> GetAllNodes() {
        if (Root == null) {
            return null;
        }

        List<SimpleTreeNode<T>> listNodes = new ArrayList<>();
        listNodes.add(Root);

        GetAllNodesRec(Root, listNodes);

        return listNodes;
    }

    // ok
    public void GetAllNodesRec(SimpleTreeNode<T> firstNode, List<SimpleTreeNode<T>> listNodes) {
        if (firstNode.Children == null) {
            return;
        }

        for (SimpleTreeNode<T> node : firstNode.Children) {
            listNodes.add(node);

            if (node.Children != null) {
                GetAllNodesRec(node, listNodes);
            }
        }
    }

    // ok
    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        List<SimpleTreeNode<T>> allNodes = GetAllNodes();
        List<SimpleTreeNode<T>> equalNodes = new ArrayList<>();

        for (SimpleTreeNode<T> node : allNodes) {
            if (node.NodeValue.equals(val)) {
                equalNodes.add(node);
            }
        }

        return equalNodes;
    }

    // ok
    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        SimpleTreeNode<T> newNode = OriginalNode;

        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(NewParent, newNode);
    }

    public int Count() {
        if (Root == null) {
            return 0;
        }

        if (Root.Children == null) {
            return 1;
        }

        List<SimpleTreeNode<T>> allNodes  = GetAllNodes();

        return allNodes .size();
    }

    // ok
    public int LeafCount() {
        if (Root == null) {
            return 0;
        }

        if (Root.Children == null) {
            return 1;
        }

        List<SimpleTreeNode<T>> allNodes = new ArrayList<>();
        LeafCountRec(Root, allNodes);

        return allNodes.size();
    }

    public void LeafCountRec(SimpleTreeNode<T> Node, List<SimpleTreeNode<T>> list) {
        for (SimpleTreeNode<T> node : Node.Children) {
            if (node.Children == null) {
                list.add(node);
            } else {
                LeafCountRec(node, list);
            }
        }
    }

    public void SetLevel() {
        if (Root == null) {
            return;
        }

        Root.Level = 0;

        if (Root.Children == null) {
            return;
        }

        SetLevelRec(Root.Children,1);
    }

    public void SetLevelRec(List <SimpleTreeNode<T>> nodeChildren, int count) {
        for (SimpleTreeNode<T> node : nodeChildren) {
            node.Level = count;

            if (node.Children != null) {
                SetLevelRec(node.Children, count + 1);
            }
        }
    }
}
