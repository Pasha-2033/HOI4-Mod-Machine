package Engine.Translator;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Syntaxer {
    public static Code Translate(String parsed) {
        Code result = new Code(null, null);
        /*
         * Here should be code for identifying type of script
         */
        //if(code_type == "regular_script") {
        while(parsed != ""){
            int nextPtr = parsed.indexOf(';');
            String currentLine = parsed.substring(0, nextPtr);
            parsed = parsed.substring(nextPtr + 1);

            if(currentLine.indexOf('=') != -1) { //Not a method/function call
                String[] expr = currentLine.split("=");
                if(expr[0].indexOf("var") != -1) { //Variable
                    String scope = null;
                    if(Pattern.matches("<\\S*>", expr[0])) { //Scoped variable
                        scope = expr[0].substring(expr[0].indexOf("<") + 1, expr[0].indexOf(">"));
                        expr[0] = expr[0].substring(expr[0].indexOf(">"));
                    }
                    else expr[0] = expr[0].substring(expr[0].indexOf("var") + 2);
                    //result.AddChild(new Code(null, (scope != null) ? scope + '.' + expr[0] : expr[0], Arrays.asList(expr[1])));
                }
            }
        }
        return result;
        //}
    }
}
