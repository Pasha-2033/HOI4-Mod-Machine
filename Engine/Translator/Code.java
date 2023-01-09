package Engine.Translator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Code {
    public Code parent = null;
    public String token;
    public boolean arrayforced = false;
    public List<Code> childs;
    public Code(String token){
        this(token, null);
    }
    public Code(String token, List<Code> childs) {
        this.childs = childs;
        this.token = token;
    }
    public final Code AddChild(Code component) {
        if (childs == null) {
            childs = new ArrayList<Code>(Collections.emptyList());
        }
        childs.add(component);
        component.parent = this;
        return this;
    }
    public String ToPDXCode(int tab_lvl) {
        if (childs == null) {
            return GetTabs(tab_lvl) + token + "\n";
        }
        if (childs.isEmpty()) {
            return GetTabs(tab_lvl) + token + " = {}\n";
        }
        if (childs.size() > 1){
            return GetCompledDef(tab_lvl);
        }
        else {
            if (childs.get(0).childs == null && !arrayforced) {
                return GetTabs(tab_lvl) + token + GetSignedValue(childs.get(0).token) + "\n";
            }
            return GetCompledDef(tab_lvl);
        }
    }
    private String GetTabs(int tab_lvl) {
        return "\t".repeat(tab_lvl);
    }
    private String GetCompledDef(int tab_lvl){
        String result = GetTabs(tab_lvl++) + token + " = {\n";
        for (Code c : childs) {
            result += c.ToPDXCode(tab_lvl);
        }
        result += GetTabs(--tab_lvl) + "}\n";
        return result;
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