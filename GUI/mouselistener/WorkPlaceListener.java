package GUI.mouselistener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import Engine.WorkEnvironment_file;
import Engine.Objects.NationalFocus;
import GUI.Menu;
import mainclassfolder.Main;
public class WorkPlaceListener extends MouseInputAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (WorkEnvironment_file.workplacemenu) {
                WorkEnvironment_file.workplacemenuinstance.setVisible(false);
                WorkEnvironment_file.workplacemenu = false;
            }
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            String type = null;
            if (!WorkEnvironment_file.focusmodding.trees.isEmpty()) {
                //if mouse in focus shape - type = "focus"
                for (NationalFocus focus : WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).nationalfocuses) {
                    //if mouse in focus shape - type = "focus"
                    if (type != null) break;
                }
            }
            Main.workenvironment.workplace.remove(WorkEnvironment_file.workplacemenuinstance);
            WorkEnvironment_file.workplacemenuinstance = Menu.WorkPlaceMenu.workplacemenu(type, null);
            Main.workenvironment.workplace.add(WorkEnvironment_file.workplacemenuinstance);
            WorkEnvironment_file.workplacemenuinstance.setBounds(0, 0,(int) WorkEnvironment_file.workplacemenuinstance.getPreferredSize().getWidth(),(int) WorkEnvironment_file.workplacemenuinstance.getPreferredSize().getHeight());
            WorkEnvironment_file.workplacemenuinstance.setLocation(e.getX(), e.getY());
            if (!WorkEnvironment_file.workplacemenu) {
                WorkEnvironment_file.workplacemenuinstance.setVisible(true);
                WorkEnvironment_file.workplacemenu = true;
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
            if (WorkEnvironment_file.scale < 10.0F) WorkEnvironment_file.scale += 0.1F;
        } else {
            if (WorkEnvironment_file.scale > 1.0F) WorkEnvironment_file.scale -= 0.1F;
        }
        Main.workenvironment.workplace.updateUI();
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
}
