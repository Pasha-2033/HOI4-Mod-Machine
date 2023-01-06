package Engine.Parsers;
import java.io.File;
import java.util.Scanner;
import mainclassfolder.Main;  
public class ModFileReader {
    //gets absolute mod dir path
    public static String getmoddir(){
        try {
            File file = new File(Main.modfilepath);
            if(!file.exists()) return null;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String command = line.split("=")[0];
                if (command.contains("path") && !command.contains("replace_path")){
                    sc.close();
                    String dirpath = line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'));
                    String path = Main.modfilepath.substring(0, Main.modfilepath.lastIndexOf('\\') + 1);
                    //System.out.println(path);
                    if (dirpath.startsWith("mod/")){
                        path += dirpath.substring(4);
                    }
                    //if (line.contains("..")) return null;
                    if (path.charAt(path.length() - 1) != '/'){
                        path += "/";
                    }
                    path = path.replace("/", "\\");
                    return path;
                }
            }
            sc.close();
            return null; 
        } catch(Exception e){  
            e.printStackTrace();
            return null;
        }
    }
}