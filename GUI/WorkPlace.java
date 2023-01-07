package GUI;
import javax.swing.JPanel;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import Engine.WorkEnvironment_file;
import Engine.Objects.NationalFocus;
public class WorkPlace extends JPanel {
    private Graphics2D g2d;
    public WorkPlace(){
        setLayout(null);
        setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g.create();
        if (!WorkEnvironment_file.focusmodding.hasnodata()) paintfocusmode(g2d);
        g2d.dispose();
    }
    private void paintfocusmode(Graphics2D g2d) {
        if (!(WorkEnvironment_file.focusmodding.trees.size() > WorkEnvironment_file.focusmodding.selectedtree)) {
            return;
        }
        paintcontinuousfocus(g2d);
        paintfocusconnections(g2d);
        paintfocuses(g2d);
    }
    private void paintfocusconnections(Graphics2D g2d) {
        for (NationalFocus focus : WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).nationalfocuses) {
            //переделать
            for (List<String> pre : focus.prerequisites) {
                switch (pre.size()) {
                    case 0:
                        System.out.println("should PDX standart for <prerequisites>, focus:" + focus.id);
                        break;
                    case 1:
                        //drawAND(g2d, focus, pre.get(0));
                        break;
                    default:
                        for (String focusto : pre) {
                            //drawOR(g2d, focus, focusto);
                        }
                        break;
                }
            }
            //drawXOR(g2d, focus);
        }
    }
    private void drawOR(Graphics2D g2d, NationalFocus focusfrom, NationalFocus focusto) {
        //вниз штрих на 1/2 расстояние между фокусами по у
        //вбок штрих
        //вниз штрих
    }
    private void drawAND(Graphics2D g2d, NationalFocus focusfrom, NationalFocus focusto) {
        //вниз штрих на 1/2 расстояние между фокусами по у
        //вбок штрих
        //вниз штрих
        g2d.setColor(ColorList.BLACK[0]);
        int[] xyfrom = WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).focus_xy(focusfrom, new ArrayList<>(Collections.emptyList()));
        int[] xyto = WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).focus_xy(focusto, new ArrayList<>(Collections.emptyList()));
        g2d.drawLine(0, 0, 100, 100);
    }
    private void drawXOR(Graphics2D g2d, NationalFocus focusfrom, NationalFocus focusto) {
        //int i = 3;
        //int y;
        //for (NationalFocus exlusive : focus.mutually_exclusive) {
        //    if (focus.y == exlusive.y && exlusive != focus) {
        //        //y = focus.y * i;
        //        g2d.drawImage(null, 0, 0, 0, 0, null);    //focus leftarrow
        //        g2d.drawImage(null, 0, 0, 0, 0, null);    //focus rigtharrow
        //        //g2d.drawLine(0, y, 0, y);
        //    }
        //}
    }
    private void paintfocuses(Graphics2D g2d) {
        for (NationalFocus focus : WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).nationalfocuses) {
            //g2d.drawImage(null, 0, 0, 0, 0, null);    //focus image
            //g2d.drawImage(null, 0, 0, 0, 0, null);    //focus lable
            //g2d.drawString(focus.id, 0, 0);

            g2d.setColor(ColorList.BLACK[0]);
            int[] xy = WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).focus_xy(focus, new ArrayList<>(Collections.emptyList()));
            g2d.fillRect(xybound(xy[0], 40.0f) + 300, xybound(xy[1], 40.0f) + 300, 40, 40);
            g2d.drawString(focus.id, xy[0] * WorkEnvironment_file.scale * 40 + 300, xy[1] * WorkEnvironment_file.scale * 40 + 380);
        }
    }
    private void paintcontinuousfocus(Graphics2D g2d){
        g2d.setColor(new Color(0,0,0,50));
        Shape shape = new Rectangle(WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).continuous_focus_position_x, WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree).continuous_focus_position_y, 200, 100);
        g2d.draw(shape);
        g2d.setColor(new Color(0,0,0,30));
        g2d.fill(shape);
    }
    //draw methods
    private int xybound(int pos, float size) {
        return Math.round(pos * WorkEnvironment_file.scale * size);
    }
}