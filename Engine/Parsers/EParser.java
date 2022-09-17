package Engine.Parsers;
public class EParser {
    static final int count(String string, String instring) {
        return (instring.length() - instring.replace(string, "").length()) / string.length();
    }
}