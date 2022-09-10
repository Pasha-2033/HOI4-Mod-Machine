package Engine;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;
import GUI.JTreeClass;
import GUI.Menu;
import GUI.WorkPlace;
import GUI.mouselistener.WorkPlaceListener;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;

import mainclassfolder.Main;

public class WorkEnvironment {
    //workplace info
    public static boolean workplacemenu = false;
    public static JMenuBar workplacemenuinstance = Menu.WorkPlaceMenu.workplacemenu(null, null);;
    //focus modding data
    public static NationalFocusTree nationalfocustree;
    //gui data
    public JLabel titlelable = new JLabel();
    public JPanel leftmenu = new JPanel(new BorderLayout());
    public JPanel rightmenu = new JPanel(new BorderLayout());
    public JPanel workplace = new WorkPlace();
    JScrollPane workplacescrollpane = new JScrollPane(workplace);
    public JSplitPane insplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftmenu, workplacescrollpane);
    public JSplitPane outsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, insplitpane, rightmenu);
    public JTree focustree = new JTree();



    public WorkEnvironment(){}
    //gui operations
    public void initgui(){
        Main.mainappwindow.add(outsplitpane);
        outsplitpane.addComponentListener(new ComponentAdapter() {
            public final void componentResized(ComponentEvent e) {
                insplitpane.updateUI();;
                rightmenu.updateUI();;
            }
        });
        insplitpane.addComponentListener(new ComponentAdapter() {
            public final void componentResized(ComponentEvent e) {
                leftmenu.updateUI();;
                workplace.updateUI();;
            }
        });
        leftmenu.setPreferredSize(new Dimension(400, 100));
        workplacescrollpane.setWheelScrollingEnabled(false);
        workplacescrollpane.setPreferredSize(new Dimension(1000, 500));
        workplacemenuinstance.setVisible(workplacemenu);
        workplacemenuinstance.setBounds(0, 0,(int) workplacemenuinstance.getPreferredSize().getWidth(),(int) workplacemenuinstance.getPreferredSize().getHeight());;
        workplace.setPreferredSize(new Dimension(1500, 500));
        workplace.addMouseListener(new WorkPlaceListener());
        workplace.add(workplacemenuinstance);
        leftmenu.add(titlelable, BorderLayout.NORTH);
        leftmenu.add(focustree);
        titlelable.setText("Пустое рабочее пространство");
        titlelable.setHorizontalAlignment(JLabel.CENTER);
        focustree.setVisible(false);
    }
    public void clearworkenvironment(){
        //clear gui
        titlelable.setText("Пустое рабочее пространство");
        focustree.setVisible(false);
        //clear focus data
        nationalfocustree = null;
    }
    public void updateworkplace() {
        workplace.updateUI();
        workplace.repaint();
    }

    
    //focus modding: operations
    public void initfocusmodding() {
        initfocusmodding(new NationalFocusTree("generic_focus_tree"));
    }
    public void initfocusmodding(NationalFocusTree nationalfocustree){
        WorkEnvironment.nationalfocustree = nationalfocustree;
        titlelable.setText("Генерация древа фокусов");
        updatefocustree();
        focustree.setVisible(true);
        updateworkplace();
    }
    public void addfocus(NationalFocus nationalfocus) {
        nationalfocustree.nationalfocuses.add(nationalfocus);
        updateworkplace();
        updatefocustree();
    }
    public void removefocus(String id) {

    }
    //focus modding: render
    public void updatefocustree() {
        //antipattern - to do reload instead of overload
        leftmenu.remove(focustree);
        focustree = new JTree(JTreeClass.buildfocustree(nationalfocustree));
        leftmenu.add(focustree);
        //focustree.addMouseListener(new ComponentTreeListener(componentroottree));
    }
}
