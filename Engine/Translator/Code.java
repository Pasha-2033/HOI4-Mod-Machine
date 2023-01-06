import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Code {
    public Code(String scope, String token, List<String> value){
        childs = new ArrayList<Code>(Collections.emptyList());
        this.value = (value == null) ? new ArrayList<String>(Collections.emptyList()) : value;

        this.scope = scope;
        this.token = token;
    }

    public final Code AddChild(Code component){
        childs.add(component);
        component.parent = this;
        return component;
    }

    public final String ToPdxCode(int tab_lvl){
        String result = "";
        
        if(scope != null) result += GetTabs(tab_lvl++) + scope + " = {" + "\n";

        if(token != null){
            if(childs.size() > 0) {
                result += GetTabs(tab_lvl++) + token + " = {" + "\n";
                for (Code elem : childs) result += elem.ToPdxCode(tab_lvl); //recursive call
                result += GetTabs(--tab_lvl) + "}" + "\n"; //<token = { \n<child_texts> \n}
            }
            else if(value.size() > 1) {
                String s = token + " = { ";
                for (String string : value) s += string + " ";
                result += s + "}" + "\n"; //<token> = { <value1> <value2> <value3> ... }
            }
            else {
                result += GetTabs(tab_lvl) + token + GetSignedValue(value.get(0)) + "\n"; //<token> = <value>
            }
        }
        else for (Code elem : childs) result += elem.ToPdxCode(tab_lvl); //recursive call
        
        if(scope != null) result += GetTabs(--tab_lvl) + "}" + "\n";

        return result;
    }

    private String GetTabs(int tab_lvl) {
        String tabs = "";
        for(int i = 0; i < tab_lvl; i++) tabs += "\t";
        return tabs;
    }

    private String GetSignedValue(String s) {
        String buf = " ";
        switch(s.charAt(0)){
            case '>':
            case '<':
                buf += s.substring(0, 1) + ' ' + s.substring(1);
                break;
            default:
                return " = " + s;
        }
        return buf;
    }

    public Code parent;
    public String scope;
    public String token;
    public List<String> value;
    public List<Code> childs;
}
