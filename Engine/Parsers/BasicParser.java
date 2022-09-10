package Engine.Parsers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class BasicParser {
    public static String deletecomments(String str) {
        while (str.contains("#")) {
            str = str.substring(0, str.indexOf("#")) + str.substring(str.indexOf("\n", str.indexOf("#")), str.length() - 1);
        }
        return str;
    }
    public static boolean bracketcountsyntaxvalid(String str) {
        return str.replaceAll("{", "") == str.replaceAll("}", "");
    }
    public static String readprimitivefield(String str, String field, int level, int count) {
        String value = "";
        int fl = field.length();
        int sl = str.length();
        if (fl == 0 || sl < fl || level < 0 || count < 0) return value;
        int c = 0;
        int l = 0;
        for (int i = 0; i + fl < sl; i++) {
            if (str.substring(i, i + fl -1).equals(field)) {
                for (int ii = 0; ii < sl; ii++) {
                    char ch = str.charAt(ii);
                    if (ch == '{') l++;
                    else if (ch == '}') l--;
                }
                if (l == level) {
                    if (c == count) {
                        boolean done;
                        str = str.substring(i + fl, sl - 1);
                        int e_check = str.indexOf("=");
                        if (e_check == -1) return ""; //syntax error
                        str = str.substring(e_check + 1, str.length() - 1);
                        List<Character> chars = new ArrayList<Character>(Collections.emptyList());
                        char prevch = ' ';
                        for (char ch : str.toCharArray()){
                            done = prevch != ' ' && ch == ' ';
                            if (done) {
                                break;
                            } else {
                                prevch = ch;
                                chars.add(ch);
                            }
                        }
                        value = chars.stream().map(e->e.toString()).reduce((acc, e) -> acc  + e).get();
                        break;
                    } else {
                        c++;
                    }
                }
            }
        }
        value.replaceAll(" ", "");
        return value;
    }
    public static String readcomplexfield(String str) {
        String value = "";
        //to do
        return value;
    }
}