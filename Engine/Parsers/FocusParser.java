package Engine.Parsers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;

public class FocusParser {
    public static final Object[] parsefocusesandtree(String code) {
        List<NationalFocusTree> trees = new ArrayList<NationalFocusTree>(Collections.emptyList());
        List<NationalFocus> sharedfocuses = new ArrayList<NationalFocus>(Collections.emptyList());
        boolean focustreeopened = false;
        boolean sharedfocusopened = false;
        boolean focusopened = false;
        int lvl = 0;
        int count = 0;
        for (int i = 10; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '{') {
                lvl++;
            } else if (c == '}') {
                lvl--;
                /*if (lvl < 0) {
                    System.out.println("unexpected { at " + i);
                    return new Object[]{};//syntax error
                }*/
                if (lvl < 1) {
                    focustreeopened = false;
                    sharedfocusopened = false;                    
                }
                if (lvl < 2) {
                    focusopened = false;                 
                }
            } else if (c == '=') {
                //System.out.println(code.substring(i - 12 > -1 ? i - 12 : 0, i));
                if (code.substring(i - 10, i).equals("focus_tree") && lvl == 0) {
                    focustreeopened = true;
                    trees.add(new NationalFocusTree());
                } else if (code.substring(i - 12 > -1 ? i - 12 : 0, i).equals("shared_focus") && lvl == 0) {
                    sharedfocuses.add(new NationalFocus());
                    sharedfocusopened = true;
                }
                if (focustreeopened) {
                    if (code.substring(i - 12, i).equals("shared_focus")) {
                        NationalFocus focus = new NationalFocus(code.substring(i + 1, code.indexOf(" ", i)));
                        focus.isloadedlikeshared = true;
                        trees.get(trees.size() - 1).nationalfocuses.add(focus);
                    } else if (code.substring(i - 5, i).equals("focus") && lvl == 1) {
                        System.out.println(code.charAt(i+1) + i);
                        trees.get(trees.size() - 1).nationalfocuses.add(new NationalFocus());
                        focusopened = true;
                    } else if (focusopened) {
                        NationalFocusTree tree = trees.get(trees.size() - 1);
                        NationalFocus focus = tree.nationalfocuses.get(tree.nationalfocuses.size() - 1);
                        if (code.substring(i - 20 > -1 ? i - 20 : 0, i).equals("relative_position_id") && lvl == 2) {
                            focus.relative_position_id = code.substring(i + 1, code.indexOf(" ", i));
                        } else if (code.substring(i - 2, i).equals("id") && lvl == 2) {
                            focus.id = code.substring(i + 1, code.indexOf(" ", i));
                        } else if (code.substring(i - 1, i).equals("x") && lvl == 2) {
                            try {
                                focus.x = Integer.parseInt(code.substring(i + 1, code.indexOf(" ", i)));
                            } catch (Exception e) {
                                return new Object[]{};//syntax error, non decimal number
                            }
                        } else if (code.substring(i - 1, i).equals("y") && lvl == 2) {
                            try {
                                focus.y = Integer.parseInt(code.substring(i + 1, code.indexOf(" ", i)));
                            } catch (Exception e) {
                                return new Object[]{};//syntax error, non decimal number
                            }
                        }
                    } else if (code.substring(i - 2, i).equals("id") && lvl == 1) {
                        trees.get(trees.size() - 1).id = code.substring(i + 1, code.indexOf(" ", i));
                    }  
                } else if (sharedfocusopened) {

                } 
            }
        }
        /*if (lvl > 0) {
            System.out.println("missing } in the end");
            return new Object[]{};//syntax error
        }*/
        return new Object[]{trees, sharedfocuses};
    }
}