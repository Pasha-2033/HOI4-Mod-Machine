package Engine.Translator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Translator {
    public static final List<Code> PDX_to_LLPL(String compressed) {
        Code root = new Code(null, null);
        Code node = root;
        boolean str_closed = true;
        for (int i = 0; i < compressed.length(); i++) {
            char c = compressed.charAt(i);
            if (c == '"') {
                str_closed = !str_closed;
            }
            if (str_closed) {
                if (c == '=') {
                    for (int ii = i; ii < compressed.length(); i--){
                        if (IsPDXChildStartSymbol(compressed.charAt(ii))){
                            Code code = new Code(compressed.substring(i + 1, i));
                            node.AddChild(code);
                            node = code;
                            break;
                        }
                    }
                    if (compressed.charAt(i + 1 < compressed.length() ? i + 1 : i) == '{'){
                        node.childs = new ArrayList<Code>(Collections.emptyList());
                    }
                }
                else if (c == '>' || c == '<') {
                    //to do
                }
                else if (c == '}'){
                    node = node.parent;
                }
            }
        }
        return root.childs;
    }
    public static final List<Code> HLPL_to_LLPL(String code) {
        List<Code> result = new ArrayList<Code>(Collections.emptyList());
        //to do
        return result;
    }
    private static boolean IsPDXChildStartSymbol(char symbol) {
        return symbol == '{' || symbol == ' ';
    }
    private static boolean IsPDXChildEndSymbol(char symbol) {
        return symbol == '}' || symbol == ' ';
    }
}