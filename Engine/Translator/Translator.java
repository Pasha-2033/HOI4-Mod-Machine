package Engine.Translator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Translator {
    public static final List<Code> PDX_to_LLPL(String compressed) {
        Code root = new Code(null, null);
        Code node = root;
        int openbracketindex = 0;
        for (int i = 0; i < compressed.length(); i++) {
            char c = compressed.charAt(i);
            if (c == '"') {
                int end = compressed.indexOf('"', i + 1);
                if (end == -1) {
                    //log: critical syntax error
                    return new ArrayList<Code>(Collections.emptyList());
                }
                end++;
                node.AddChild(new Code(compressed.substring(i, end)));
                i = end;
                node = node.parent;
            }
            else if (c == '=') {
                for (int ii = i; ii > -1; ii--) {
                    if (IsPDXChildEndSymbol(compressed.charAt(ii)) || ii == 0) {
                        Code code = new Code(compressed.substring(ii == 0 ? 0 : ii + 1, i));
                        node.AddChild(code);
                        node = code;
                        break;
                    }
                }
                if (compressed.charAt(i + 1 < compressed.length() ? i + 1 : i) == '{' && node.childs == null) {
                    openbracketindex = i + 2;
                    node.childs = new ArrayList<Code>(Collections.emptyList());
                }
                else if (compressed.charAt(i + 1 < compressed.length() ? i + 1 : i) != '"'){
                    for (int ii = i; ii < compressed.length(); ii++) {
                        if (IsPDXChildEndSymbol(compressed.charAt(ii))) {
                            Code code = new Code(compressed.substring(i + 1, ii));
                            node.AddChild(code);
                            node = node.parent;
                            break;
                        }
                    }
                }
            }
            else if (c == '>' || c == '<') {
                for (int ii = i; ii > -1; ii--){
                    if (IsPDXChildEndSymbol(compressed.charAt(ii)) || ii == 0) {
                        Code code = new Code(compressed.substring(ii == 0 ? 0 : ii + 1, i));
                        node.AddChild(code);
                        node = code;
                        break;
                    }
                }
                for (int ii = i; ii < compressed.length(); ii++) {
                    if (IsPDXChildEndSymbol(compressed.charAt(ii))) {
                        Code code = new Code(compressed.substring(i, ii));
                        node.AddChild(code);
                        node = node.parent;
                        break;
                    }
                }
            }
            else if (c == '}') {
                if (node.childs == null) {
                    //log: critical syntax error
                    return new ArrayList<Code>(Collections.emptyList());
                }
                if (node.childs.isEmpty()) {
                    String[] array = compressed.substring(openbracketindex, i).split(" ");
                    for (String iter : array) {
                        node.AddChild(new Code(iter));
                    }
                    node.arrayforced = true;
                }
                node = node.parent;
            }
        }
        return root.childs;
    }
    public static final List<Code> HLPL_to_LLPL(String code) {
        List<Code> result = new ArrayList<Code>(Collections.emptyList());
        //to do
        return result;
    }
    private static boolean IsPDXChildEndSymbol(char symbol) {
        return symbol == '}' || symbol == '{' || symbol == ' ';
    }
}