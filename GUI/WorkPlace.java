package GUI;

import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Engine.WorkEnvironment;
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
        if (WorkEnvironment.nationalfocustree != null) paintfocusmode(g2d);
        g2d.dispose();
    }
    private void paintfocusmode(Graphics2D g2d) {
        paintcontinuousfocus(g2d);
        paintfocusconnections(g2d);
        paintfocuses(g2d);
    }
    private void paintfocusconnections(Graphics2D g2d) {
        for (NationalFocus focus : WorkEnvironment.nationalfocustree.nationalfocuses) {
            drawOR(g2d, focus);
            drawAND(g2d, focus);
            drawXOR(g2d, focus);
        }
    }
    private void drawOR(Graphics2D g2d, NationalFocus focus) {
        //вниз штрих на 1/2 расстояние между фокусами по у
        //вбок штрих
        //вниз штрих
    }
    private void drawAND(Graphics2D g2d, NationalFocus focus) {
        //вниз штрих на 1/2 расстояние между фокусами по у
        //вбок штрих
        //вниз штрих
    }
    private void drawXOR(Graphics2D g2d, NationalFocus focus) {
        
    }
    private void paintfocuses(Graphics2D g2d) {
        for (NationalFocus focus : WorkEnvironment.nationalfocustree.nationalfocuses) {
            g2d.drawImage(null, 0, 0, 0, 0, null);    //focus image
            g2d.drawImage(null, 0, 0, 0, 0, null);    //focus lable
            g2d.drawString("Focus", 0, 0);
        }
    }
    private void paintcontinuousfocus(Graphics2D g2d){
        g2d.setColor(new Color(0,0,0,50));
        Shape shape = new Rectangle(WorkEnvironment.nationalfocustree.continuous_focus_position_x, WorkEnvironment.nationalfocustree.continuous_focus_position_y, 200, 100);
        g2d.draw(shape);
        g2d.setColor(new Color(0,0,0,30));
        g2d.fill(shape);
    }
}