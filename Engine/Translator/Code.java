package Engine.Translator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Code {
    public Code parent;
    public String token;
    public List<Code> childs;
    public Code(String token, List<Code> childs) {
        this.childs = (childs == null) ? new ArrayList<Code>(Collections.emptyList()) : childs;
        this.token = token;
    }
    public final Code AddChild(Code component) {
        childs.add(component);
        component.parent = this;
        return component;
    }
    public final String ToPdxCode(int tab_lvl) {
        if(childs.isEmpty()) {
            return GetTabs(tab_lvl) + token + "\n";
        }
        String result = "";
        if (childs.size() > 1 || !childs.get(0).childs.isEmpty()){
            result += GetTabs(tab_lvl++) + token + " = {\n";
            for (Code c : childs) {
                result += c.ToPdxCode(tab_lvl);
            }
            result += GetTabs(--tab_lvl) + "}\n";
        }
        else {
            result += GetTabs(tab_lvl) + token + GetSignedValue(childs.get(0).token) + "\n";
        }
        return result;
    }
    private String GetTabs(int tab_lvl) {
        return "\t".repeat(tab_lvl);
    }
    private String GetSignedValue(String s) {
        String buf = "";
        switch(s.charAt(0)){
            case '>':
                buf += " > " + s.substring(1);
                break;
            case '<':
                buf += " < " + s.substring(1);
                break;
            default:
                return " = " + s;
        }
        return buf;
    }
}
