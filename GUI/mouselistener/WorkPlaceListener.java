package GUI.mouselistener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import Engine.WorkEnvironment;
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
            WorkEnvironment.workplacemenuinstance.setLocation(e.getX(), e.getY());
            if (!WorkEnvironment.workplacemenu) {
                WorkEnvironment.workplacemenuinstance.setVisible(true);
                WorkEnvironment.workplacemenu = true;
            }
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
