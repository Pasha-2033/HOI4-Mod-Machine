package GUI;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class AppWindow extends JFrame {
    public AppWindow(int x, int y, int width, int height) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        super("HOI4 Mod Machine");
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setIconImage(new ImageIcon("resources/logisim.png").getImage());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setJMenuBar(Menu.JFrameMenu.upmenubar());
    }
}