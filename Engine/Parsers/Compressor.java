package Engine.Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compressor {
    public static final String compress(String path, boolean forced){
        File file = new File(path);
        if (file.isFile()) {
            //подготовка к чтению
            Scanner reader;
            try {
                reader = new Scanner(file, "utf-8"); //https://www.rgagnon.com/javadetails/java-handle-utf8-file-with-bom.html ("?" artefact)
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "";
            }
            //чтение:
            //если строка содержит комментарий - вырезать
            //если строка без комментария пустая - проигнорировать
            String text = "";
            while (reader.hasNextLine()) {
                String t = reader.nextLine();
                while(t.length() > 0) {
                    if (t.charAt(0) == '\t' || t.charAt(0) == ' ') {
                        t = t.substring(1);
                    }
                    else {
                        break;
                    }
                }
                if (t.contains("#")) {
                    t = t.substring(0, t.indexOf("#"));
                }
                while(t.length() > 0) {
                    if (t.charAt(t.length() - 1) == '\t' || t.charAt(t.length() - 1) == ' ') {
                        t = t.substring(0, t.length() - 1);
                    }
                    else {
                        break;
                    }
                }
                if (t.length() > 0) {
                    text += t + "\n";
                }
            }
            //сжатие по "=", "<", ">"
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '=' || text.charAt(i) == '>' || text.charAt(i) == '<')  {
                    while (text.charAt(i + 1 < text.length() ? i + 1 : i) == ' ') {
                        text = text.substring(0, i + 1) + text.substring(i + 2);
                    }
                    while (text.charAt(i - 1 > -1 ? i - 1 : 0) == ' ') {
                        text = text.substring(0, i - 1) + text.substring(i);
                        i--;
                    }
                }
            }

            //сжатие по "\n" и "\t"
            Pattern pattern = Pattern.compile("\n");
            Matcher matcher = pattern.matcher(text);
            text = matcher.replaceAll(Matcher.quoteReplacement(" "));
            pattern = Pattern.compile("\t");
            matcher = pattern.matcher(text);
            text = matcher.replaceAll(Matcher.quoteReplacement(" "));
            //сжатие по " " и "{" и "}"
            boolean stringisclosed = true;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '"')  {
                    stringisclosed = !stringisclosed;
                    if (stringisclosed && forced) {
                        while (text.charAt(i - 1 > -1 ? i - 1 : 0) == ' ') {
                            text = text.substring(0, i - 1) + text.substring(i);
                            i--;
                        }
                    }
                } else if (text.charAt(i) == '{' || text.charAt(i) == ' ') {
                    if (!(i + 1 < text.length())) break;
                    if (forced || stringisclosed) {
                        while (text.charAt(i + 1) == ' ') {
                            text = text.substring(0, i + 1) + text.substring(i + 2);
                            i--;
                        }
                    }
                } else if (text.charAt(i) == '}') {
                    while (text.charAt(i - 1 > -1 ? i - 1 : 0) == ' ') {
                        text = text.substring(0, i - 1) + text.substring(i);
                        i--;
                    }
                    while (text.charAt(i + 1 < text.length() ? i + 1 : i) == ' ') {
                        text = text.substring(0, i + 1) + text.substring(i + 2);
                        i--;
                    }
                }
            }
            //проверка для спецсимвола, если кодировка utf-8 with bom
            if (text.startsWith("\uFEFF")) text = text.substring(1);

            //System.out.println(text);
            reader.close();
            return text;
        }
        return "";
    }
}