package GUI;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class Menu {
    public static class JFrameMenu {
        public static JMenuBar upmenubar(){
            JMenuBar menu = new JMenuBar();
            menu.add(filemenu());
            menu.add(modmanagemenu());
            return menu;
        }
        private static JMenu filemenu(){
            JMenu menu = new JMenu("Файл");
            JMenuItem item;
            item = new JMenuItem(new GUIActions.openmoddesc());
            //item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
            menu.add(item);
            item = new JMenuItem(new GUIActions.exit());
            //item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
            menu.add(item);
            return menu;
        }
        private static JMenu modmanagemenu(){
            JMenu menu = new JMenu("Редактирование мода");
            //JMenuItem item;
            menu.add(managefocustree());
            return menu;
        }
        private static JMenu managefocustree(){
            JMenu menu = new JMenu("Управление древом фокусов");
            JMenuItem item;
            item = new JMenuItem(new GUIActions.generatefocustree());
            //item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
            menu.add(item);
            item = new JMenuItem(new GUIActions.redactfocustree());
            //item.setIcon(new ImageIcon("resources/icon-all/Yaru/menu/circ-file.png"));
            menu.add(item);
            return menu;
        }
    }
    public static class WorkPlaceMenu {
        public static JMenuBar workplacemenu(String type, String target){
            JMenuBar menu = new JMenuBar();
            switch (type) {
                case "focus":
                    menu.add(focusmenu(target));
                    break;
                default:
                    break;
            }
            return menu;
        }
        private static JMenu focusmenu(String target){
            JMenu menu = new JMenu("Работа с фокусами");
            JMenuItem item;
            item = new JMenuItem(new GUIActions.addfocus());
            //item.setIcon(new ImageIcon(""));
            menu.add(item);
            return menu;
        }
    }
}
