package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import Engine.WorkEnvironment_file;
import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;

public class JTreeClass {
    static public TreeModel buildfocustree(){
        //корневая панель
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Моддинг фокусов");
        DefaultMutableTreeNode trees = new DefaultMutableTreeNode("Древа фокусов");
        DefaultMutableTreeNode shared = new DefaultMutableTreeNode("Обобщенные фокусы");
        root.add(trees);
        root.add(shared);
        //фокусы
        for (NationalFocus nationalfocus : WorkEnvironment_file.focusmodding.sharedfocuses){
            shared.add(new DefaultMutableTreeNode(nationalfocus));
        }
        for (NationalFocusTree tree : WorkEnvironment_file.focusmodding.trees) {
            DefaultMutableTreeNode t = new DefaultMutableTreeNode(tree);
            for (NationalFocus f : tree.nationalfocuses) {
                t.add(new DefaultMutableTreeNode(f));
            }
            trees.add(t);
        }
        return new DefaultTreeModel(root);
    }
    static public class focustreerenderer implements TreeCellRenderer {
        private JLabel label = new JLabel();
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object data = node.getUserObject();
            try {
                if (((NationalFocus)data).isloadedlikeshared || ((NationalFocus)data).isshared) {
                    label.setIcon(HOI4MMResourceLoader.sharedfocusintree);
                } else {
                    label.setIcon(HOI4MMResourceLoader.focusintree);
                }
                
                label.setText(((NationalFocus)data).id);
            } catch (Exception e) {
                try {
                    label.setIcon(HOI4MMResourceLoader.focustreeintree);
                    label.setText(((NationalFocusTree)data).id);
                } catch (Exception ee) {
                    try {
                        label.setIcon(HOI4MMResourceLoader.folderintree);
                        label.setText((String)data);
                    } catch (Exception eee) {
                        label.setIcon(HOI4MMResourceLoader.errorintree);
                        label.setText(data.getClass().getName());
                    }
                }
            }
            label.setOpaque(false);
            return label;
        }
    }
}