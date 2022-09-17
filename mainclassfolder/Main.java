package mainclassfolder;
import GUI.AppWindow;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import Engine.WorkEnvironment;
import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;
import Engine.Parsers.Compressor;
import Engine.Parsers.FocusParser;
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
        String s = Compressor.compress("mainclassfolder\\file.txt", true);
        Object[] o = FocusParser.parsefocusesandtree(s);
        System.out.println(
            ((List<NationalFocusTree>)o[0]).get(0).id
        );
        /*System.out.println(
            ((List<NationalFocusTree>)o[0]).get(1).id
        );*/
        System.out.println(
            ((List<NationalFocusTree>)o[0]).get(0).nationalfocuses.size()
        );
        System.out.println(
            ((List<NationalFocusTree>)o[0]).get(1).nationalfocuses.size()
        );
        System.out.println("---");
        for (NationalFocus f : ((List<NationalFocusTree>)o[0]).get(0).nationalfocuses){
            System.out.println(f.id + ":" + f.isloadedlikeshared + ":" + f.x + "/" + f.y);
        }
        System.out.println("---");
        for (NationalFocus f : ((List<NationalFocusTree>)o[0]).get(1).nationalfocuses){
            System.out.println(f.id + ":" + f.isloadedlikeshared + ":" + f.x + "/" + f.y);
        }
    }
    public static WorkEnvironment workenvironment;
    public static AppWindow mainappwindow;
    public static String modfilepath = null;
    public static String moddirpath = null;
}