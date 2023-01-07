package Engine.Translator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Translator {
    public static final List<Code> PDX_to_LLPL(String compressed) {
        Code root = new Code(null, null);
        Code node = root;
        boolean str_closed = true;
        for (int i = 0; i < compressed.length(); i++) {
            char c = compressed.charAt(i);
            if (c == '=') {
                
            }
            else if (c == '>' || c == '<') {

            }
        }
        return root.childs;
    }
    public static final List<Code> HLPL_to_LLPL(String code) {
        List<Code> result = new ArrayList<Code>(Collections.emptyList());
        //to do
        return result;
    }
}