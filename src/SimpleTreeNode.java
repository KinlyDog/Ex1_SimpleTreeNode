import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        // ваш код добавления нового дочернего узла существующему ParentNode
        if (ParentNode.Children != null && ParentNode.Children.contains(NewChild)) {
            return;
        }

        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }

        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        // ваш код удаления существующего узла NodeToDelete
        if (NodeToDelete.Children == null) {
            DeleteNodeSimple(NodeToDelete);
            return;
        }

        List<SimpleTreeNode<T>> list = new ArrayList<>();
        GetAllNodesRec(NodeToDelete, list);

        for (SimpleTreeNode<T> t : list) {
            DeleteNodeSimple(t);
        }

        DeleteNodeSimple(NodeToDelete);

    }

    // HOW IT MAKE?
    public void DeleteNodeSimple(SimpleTreeNode<T> NodeToDelete) {
        NodeToDelete.NodeValue = null;

        NodeToDelete.Parent.Children.remove(NodeToDelete);
        if (NodeToDelete.Parent.Children.size() == 0) {
            NodeToDelete.Parent.Children = null;
        }

        NodeToDelete.Parent = null;
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        // ваш код выдачи всех узлов дерева в определённом порядке
        if (Root.Children == null) {
            return null;
        }

        List<SimpleTreeNode<T>> list = new ArrayList<>();
        GetAllNodesRec(Root, list);

        return list;
    }

    public void GetAllNodesRec(SimpleTreeNode<T> start, List<SimpleTreeNode<T>> list) {
        for (SimpleTreeNode<T> node : start.Children) {
            list.add(node);

            if (node.Children != null) {
                GetAllNodesRec(node, list);
            }
        }
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        // ваш код поиска узлов по значению
        List <SimpleTreeNode<T>> list = GetAllNodes();

        List <SimpleTreeNode<T>> ls = new ArrayList<>();

        for (SimpleTreeNode<T> t : list) {
            if (t.NodeValue.equals(val)) {
                ls.add(t);
            }
        }

        return ls;
    }


    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        SimpleTreeNode<T> node = OriginalNode;

        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(NewParent, node);

    }

    public int Count() {
        if (Root == null) {
            return 0;
        }

        if (Root.Children == null) {
            return 1;
        }

        List<SimpleTreeNode<T>> list = new ArrayList<>();
        CountRec(Root, list);

        return list.size();
    }

    public void CountRec(SimpleTreeNode<T> Node, List<SimpleTreeNode<T>> list) {
        for (SimpleTreeNode<T> node : Node.Children) {
            list.add(node);

            if (node.Children != null) {
                CountRec(node, list);
            }
        }
    }

    public int LeafCount() {
        if (Root == null || Root.Children == null) {
            return 0;
        }

        List<SimpleTreeNode<T>> list = new ArrayList<>();
        LeafCountRec(Root, list);

        return list.size();
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

    public static void main(String[] args) {
/*        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(0, null);
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);

        SimpleTree<Integer> tree = new SimpleTree<>(root);

        tree.AddChild(root, node1);
        tree.AddChild(node1, node2);
        tree.AddChild(node1, node3);
        tree.AddChild(node2, node4);
        tree.AddChild(node1, node5);
        tree.AddChild(node2, node6);

//        tree.DeleteNode(node1);
        System.out.println(tree.LeafCount());
//        List list = tree.GetAllNodes();
        System.out.println(tree.Count());

        tree.DeleteNode(node2);*/

        SimpleTree<Integer> tree = new SimpleTree<>(null);
        System.out.println(tree.LeafCount());
        System.out.println(tree.Count());

    }
}
