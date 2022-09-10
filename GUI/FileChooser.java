package GUI;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
public class FileChooser {
    public JFileChooser chooser = new JFileChooser();
    public FileChooser(){}
    public FileChooser(String filterdesc, String fileformat){
        chooser.setFileFilter(new FileNameExtensionFilter(filterdesc, fileformat));
    }
    public String getabsolutepath(){
        if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION){
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }
}