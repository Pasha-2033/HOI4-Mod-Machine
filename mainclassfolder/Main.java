package mainclassfolder;
import GUI.AppWindow;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;

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

import Engine.Translator.Code;
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
        /*System.out.println(
            ((List<NationalFocusTree>)o[0]).get(0).id
        );*/
        /*System.out.println(
            ((List<NationalFocusTree>)o[0]).get(1).id
        );*/
        /*System.out.println(
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
        }*/
        workenvironment.initfocusmodding((List<NationalFocusTree>)o[0], (List<NationalFocus>)o[1]);
        

        /*System.out.println("---");
        List<NationalFocusTree> nftl = FocusParser.parsefocustrees(s);
        for (NationalFocusTree nft : nftl){
            List<NationalFocus> nfl = FocusParser.parsetreefocuses(s, nft);
            System.out.println(nft.id);
            System.out.println(nfl.size());
            for (NationalFocus f : nfl){
                System.out.println(f.id + ":" + f.isloadedlikeshared + ":" + f.x + "/" + f.y + ":" + f.relative_position_id);
            }
        }*/
        
        //Tests
        Code root_block = new Code("ROOT", null);
        Code if_block = new Code("if", null);
        Code limit_block = new Code("limit", null);
        Code check_block_0 = new Code("check_variable", null);
        Code check_block_1 = new Code("check_variable", null);
        Code var_0 = new Code("my_var", null);
        Code var_1 = new Code("my_var", null);
        Code check_val_0 = new Code("<30", null);
        Code check_val_1 = new Code("20", null);
        Code enum_block = new Code("enum", null);
        Code enum_val_0 = new Code("enum_val_0", null);
        Code enum_val_1 = new Code("enum_val_1", null);
        Code effect = new Code("effect_to_do", null);
        Code e_val = new Code("yes", null);

        root_block.AddChild(if_block);
        if_block.AddChild(limit_block);
        if_block.AddChild(enum_block);
        if_block.AddChild(effect);
        limit_block.AddChild(check_block_0);
        limit_block.AddChild(check_block_1);
        check_block_0.AddChild(var_0);
        check_block_1.AddChild(var_0);
        var_0.AddChild(check_val_0);
        var_1.AddChild(check_val_1);
        enum_block.AddChild(enum_val_0);
        enum_block.AddChild(enum_val_1);
        effect.AddChild(e_val);


        System.out.println(root_block.ToPdxCode(0));

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
                //System.out.println(fileEntry.getAbsolutePath());
            }
        }
    }
}