package Engine.WorkEnvironment.Containers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Engine.Translator.Code;
import Engine.WorkEnvironment.Container;
public class CodeContainer extends Container {
    public CodeContainer(String folder, String file, String type) {
        super(folder, file, type);
    }
    List<Code> pdx_scripts = new ArrayList<Code>(Collections.emptyList());
    @Override
    public final String ToPDXCode() {   //allows to get text, that should be put into file
        String result = "#scripted with HLPL\n";
        for (Code c : pdx_scripts) {
            result += c.ToPDXCode(0);
        }
        return result;
    }
    @Override
    public String ToHLPLCode() {
        // TODO Auto-generated method stub
        //to do, how code should be traslated to HLPL
        return null;
    }
}