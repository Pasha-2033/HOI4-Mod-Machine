package Engine.WorkEnvironment.Containers;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import Engine.WorkEnvironment.Container;
public class LocContainer extends Container {
    public LocContainer(String folder, String file, String type) {
        super(folder, file, type);
    }
    HashMap<String, Entry<String, Integer>> lockeys;
    @Override
    public String ToPDXCode() {
        String result = "";
        Iterator<String> iterator = lockeys.keySet().iterator();
        String key, text;
        Integer version;
        while (iterator.hasNext()) {
            key = iterator.next();
            version = lockeys.get(key).getValue();
            text = lockeys.get(key).getKey();
            result += key + ":" + version == null ? "" : version.toString() + " " + text;
        }
        return result;
    }
    @Override
    public String ToHLPLCode() {
        // TODO Auto-generated method stub
        return null;
    }
}