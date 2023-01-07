package GUI;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import Engine.WorkEnvironment_file;
import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;
import Engine.Parsers.ModFileReader;
import mainclassfolder.Main;
public class GUIActions {
    public static class openmoddesc extends AbstractAction {
        public openmoddesc(){putValue(NAME, "Открыть дескриптор");}
        public void actionPerformed(ActionEvent e) {
            FileChooser filechooser = new FileChooser("Дескриптор мода (.mod)", "mod");
            String path = filechooser.getabsolutepath();
            Main.modfilepath = path;
            Main.moddirpath = ModFileReader.getmoddir();
        }
    }
    public static class exit extends AbstractAction {
        public exit(){putValue(NAME, "Выход");}
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    public static class generatefocustree extends AbstractAction {
        public generatefocustree(){putValue(NAME, "Сгенерировать");}
        public void actionPerformed(ActionEvent e) {
            Main.workenvironment.initfocusmodding();
        }
    }
    public static class redactfocustree extends AbstractAction {
        public redactfocustree(){putValue(NAME, "Редактировать");}
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    public static class addfocus extends AbstractAction {
        public addfocus(){putValue(NAME, "Создать фокус");}
        public void actionPerformed(ActionEvent e) {
            NationalFocusTree tree = WorkEnvironment_file.focusmodding.trees.get(WorkEnvironment_file.focusmodding.selectedtree);
            tree.nationalfocuses.add(new NationalFocus("focus_" + tree.nationalfocuses.size()));
        }
    }
}