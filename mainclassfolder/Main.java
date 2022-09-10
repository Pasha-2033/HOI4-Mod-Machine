package mainclassfolder;
import GUI.AppWindow;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.Toolkit;
import Engine.WorkEnvironment;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //mainappwindow = new AppWindow(0, 0, dim.width, dim.height);
        mainappwindow = new AppWindow(0, 0, dim.width, dim.height);
        mainappwindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //mainappwindow.setUndecorated(true);
        workenvironment = new WorkEnvironment();
        workenvironment.initgui();
        mainappwindow.setVisible(true);
    }
    public static WorkEnvironment workenvironment;
    public static AppWindow mainappwindow;
    public static String modfilepath = null;
    public static String moddirpath = null;
}