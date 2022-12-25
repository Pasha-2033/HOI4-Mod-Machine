package Engine;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;
import Engine.WorkPlaces.FocusModdingWorkPlace;
import GUI.JTreeClass;
import GUI.Menu;
import GUI.WorkPlace;
import GUI.mouselistener.WorkPlaceListener;

import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;

import mainclassfolder.Main;

public class WorkEnvironment {
    //workplace info
    public static boolean workplacemenu = false;
    public static JMenuBar workplacemenuinstance = Menu.WorkPlaceMenu.workplacemenu(null, null);;
    //focus modding data
    public static FocusModdingWorkPlace focusmodding = new FocusModdingWorkPlace();
    //gui data
    public JLabel titlelable = new JLabel();
    public JPanel leftmenu = new JPanel(new BorderLayout());
    public JPanel rightmenu = new JPanel(new BorderLayout());
    public JPanel workplace = new WorkPlace();
    public JScrollPane workplacescrollpane = new JScrollPane(workplace);
    public JSplitPane insplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftmenu, workplacescrollpane);
    public JSplitPane outsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, insplitpane, rightmenu);
    public JTree tree = new JTree();
    public JScrollPane treescrollpane = new JScrollPane(tree);
    public static float scale = 1.0f;

    public WorkEnvironment(){}
    //gui operations
    public void initgui(){
        Main.mainappwindow.add(outsplitpane);
        outsplitpane.addComponentListener(new ComponentAdapter() {
            public final void componentResized(ComponentEvent e) {
                insplitpane.updateUI();
                rightmenu.updateUI();
            }
        });
        insplitpane.addComponentListener(new ComponentAdapter() {
            public final void componentResized(ComponentEvent e) {
                leftmenu.updateUI();
                workplace.updateUI();
            }
        });
        leftmenu.setPreferredSize(new Dimension(400, 100));
        workplacescrollpane.setWheelScrollingEnabled(false);
        workplacescrollpane.setPreferredSize(new Dimension(1000, 500));
        workplacemenuinstance.setVisible(workplacemenu);
        workplacemenuinstance.setBounds(0, 0,(int) workplacemenuinstance.getPreferredSize().getWidth(),(int) workplacemenuinstance.getPreferredSize().getHeight());;
        workplace.setPreferredSize(new Dimension(Math.round(2500 * scale), Math.round(500 * scale)));
        workplace.addMouseListener(new WorkPlaceListener());
        workplace.addMouseWheelListener(new WorkPlaceListener());
        workplace.add(workplacemenuinstance);
        leftmenu.add(titlelable, BorderLayout.NORTH);
        leftmenu.add(treescrollpane);
        titlelable.setText("Пустое рабочее пространство");
        titlelable.setHorizontalAlignment(JLabel.CENTER);
        treescrollpane.setVisible(false);
        updatefocustree();
    }
    public void clearworkenvironment(){
        //clear gui
        titlelable.setText("Пустое рабочее пространство");
        treescrollpane.setVisible(false);
        //clear focus data
    }
    public void updateworkplace() {
        workplace.updateUI();
        workplace.repaint();
    }

    
    //focus modding: operations
    public void initfocusmodding() {
        NationalFocusTree focustree = new NationalFocusTree("generic_focus_tree");
        NationalFocus focus = new NationalFocus("generic_focus");
        focus.x = focus.y = 1;
        focustree.nationalfocuses.add(focus); //temp, for renderer test
        initfocusmodding(Arrays.asList(focustree), new ArrayList<NationalFocus>(Collections.emptyList()));
    }
    public void initfocusmodding(List<NationalFocusTree> nationalfocustree, List<NationalFocus> sharedfocuses){
        WorkEnvironment.focusmodding.cleardata();
        for (NationalFocusTree nft : nationalfocustree) {
            WorkEnvironment.focusmodding.loadtree(nft);
        }
        WorkEnvironment.focusmodding.loadsharedfocuses(sharedfocuses);
        titlelable.setText("Генерация древа фокусов");
        updatefocustree();
        treescrollpane.setVisible(true);
        updateworkplace();
    }
    //focus modding: render
    public void updatefocustree() {
        //antipattern - to do reload instead of overload
        leftmenu.remove(treescrollpane);
        tree = new JTree(JTreeClass.buildfocustree());
        tree.setCellRenderer(new JTreeClass.focustreerenderer());
        treescrollpane = new JScrollPane(tree);
        leftmenu.add(treescrollpane);
        leftmenu.updateUI();
        //focustree.addMouseListener(new ComponentTreeListener(componentroottree));
    }
}
