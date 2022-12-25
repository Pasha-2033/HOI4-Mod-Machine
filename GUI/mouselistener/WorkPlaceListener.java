package GUI.mouselistener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import Engine.WorkEnvironment;
import Engine.Objects.NationalFocus;
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
            if (!WorkEnvironment.focusmodding.trees.isEmpty()) {
                //if mouse in focus shape - type = "focus"
                for (NationalFocus focus : WorkEnvironment.focusmodding.trees.get(WorkEnvironment.focusmodding.selectedtree).nationalfocuses) {
                    //if mouse in focus shape - type = "focus"
                    if (type != null) break;
                }
            }
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
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0){
            if (WorkEnvironment.scale < 10.0F) WorkEnvironment.scale += 0.1F;
        } else {
            if (WorkEnvironment.scale > 1.0F) WorkEnvironment.scale -= 0.1F;
        }
        Main.workenvironment.workplace.updateUI();
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
}
