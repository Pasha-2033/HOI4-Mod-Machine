package GUI.mouselistener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import Engine.WorkEnvironment;
import GUI.Menu;
import mainclassfolder.Main;
public class WorkPlaceListener extends MouseInputAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (WorkEnvironment.workplacemenu) {
                WorkEnvironment.workplacemenuinstance.setVisible(false);
                WorkEnvironment.workplacemenu = false;
            }
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            String type = null;
            if (WorkEnvironment.nationalfocustree != null) type = "focus";
            Main.workenvironment.workplace.remove(WorkEnvironment.workplacemenuinstance);
            WorkEnvironment.workplacemenuinstance = Menu.WorkPlaceMenu.workplacemenu(type, null);
            Main.workenvironment.workplace.add(WorkEnvironment.workplacemenuinstance);
            WorkEnvironment.workplacemenuinstance.setBounds(0, 0,(int) WorkEnvironment.workplacemenuinstance.getPreferredSize().getWidth(),(int) WorkEnvironment.workplacemenuinstance.getPreferredSize().getHeight());
            WorkEnvironment.workplacemenuinstance.setLocation(e.getX(), e.getY());
            if (!WorkEnvironment.workplacemenu) {
                WorkEnvironment.workplacemenuinstance.setVisible(true);
                WorkEnvironment.workplacemenu = true;
            }
            Main.workenvironment.updateworkplace();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}
