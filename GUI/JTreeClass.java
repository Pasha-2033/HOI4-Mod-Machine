package GUI;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import Engine.WorkEnvironment;
import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;

public class JTreeClass {
    static public TreeModel buildfocustree(NationalFocusTree nationalfocustree){
        //корневая панель
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Древо фокусов: " + WorkEnvironment.nationalfocustree.id);
        //фокусы
        for (NationalFocus nationalfocus : nationalfocustree.nationalfocuses){
            root.add(new DefaultMutableTreeNode(nationalfocus));
        }
        return new DefaultTreeModel(root);
    }
}