package Engine.Parsers;
import Engine.Objects.NationalFocusTree;
public class FocusTreeParser {
    static NationalFocusTree parsefocustree(String str) {
        str = BasicParser.deletecomments(str);
        String id = BasicParser.readprimitivefield(str, "id", 1, 0);
        if (id == "") id = "error_reading_focus_tree_id";
        NationalFocusTree tree = new NationalFocusTree(id);

        //add focus reading

        return tree;
    }
}
