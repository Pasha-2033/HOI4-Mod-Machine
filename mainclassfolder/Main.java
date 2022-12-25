package mainclassfolder;
import GUI.AppWindow;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
            System.out.println(f.id + ":" + f.isloadedlikeshared + ":" + f.x + "/" + f.y + ":" + f.relative_position_id);
        }
        System.out.println("---");
        for (NationalFocus f : ((List<NationalFocusTree>)o[0]).get(1).nationalfocuses){
            System.out.println(f.id + ":" + f.isloadedlikeshared + ":" + f.x + "/" + f.y + ":" + f.relative_position_id);
        }
        workenvironment.initfocusmodding((List<NationalFocusTree>)o[0], (List<NationalFocus>)o[1]);
        

        System.out.println("---");
        List<NationalFocusTree> nftl = FocusParser.parsefocustrees(s);
        for (NationalFocusTree nft : nftl){
            List<NationalFocus> nfl = FocusParser.parsetreefocuses(s, nft);
            System.out.println(nft.id);
            System.out.println(nfl.size());
            for (NationalFocus f : nfl){
                System.out.println(f.id + ":" + f.isloadedlikeshared + ":" + f.x + "/" + f.y + ":" + f.relative_position_id);
            }
        }
        

        //String path = "C:\\Users\\User\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\Melnitsa\\common\\national_focus";
        //File dir = new File(path);
        //listFilesForFolder(dir);
    }
    public static WorkEnvironment workenvironment;
    public static AppWindow mainappwindow;
    public static String modfilepath = null;
    public static String moddirpath = null;
    public static void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                try {
                    String text = Compressor.compress(fileEntry.getAbsolutePath(), false);
                    BufferedWriter out;
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileEntry.getAbsolutePath()), StandardCharsets.UTF_8));
                    //out.write('\ufeff');
                    out.write(text);
                    out.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(fileEntry.getAbsolutePath());
            }
        }
    }
}