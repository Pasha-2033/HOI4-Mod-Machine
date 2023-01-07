package Engine.WorkEnvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Engine.WorkEnvironment.Containers.CodeContainer;
class ContainerOperator {
    public List<Container> mod_scripts = new ArrayList<Container>(Collections.emptyList());
    public final Container CreateContainer(String folder, String file, String type) {
        String path = folder + "/" + file + "." + type;
        for (Container c : mod_scripts) {
            if (c.GetRelativePath().equals(path)){
                return c;
            }
        }
        Container container;
        switch (folder.substring(0, folder.indexOf('/'))) {
            case "localisation":
                //other init type, for other syntax, to do
                container = null;
                break;
            default:
                container = new CodeContainer(folder, file, type);
                break;
        }
        mod_scripts.add(container);
        return container;
    }
}