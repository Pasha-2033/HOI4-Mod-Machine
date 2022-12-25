package Engine.WorkPlaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Engine.Objects.NationalFocus;
import Engine.Objects.NationalFocusTree;

public class FocusModdingWorkPlace {
    public FocusModdingWorkPlace() {
        cleardata();
    }
    public void cleardata() {
        sharedfocuses = new ArrayList<NationalFocus>(Collections.emptyList());
        trees = new ArrayList<NationalFocusTree>(Collections.emptyList());
        selectedtree = 0;
        selectedfocus = 0;
    }
    public void loadtree(NationalFocusTree tree) {
        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i).id == tree.id) {
                trees.set(i, tree);
                //add log about rewriting tree
                return;
            }
        }
        trees.add(tree);
    }
    public void loadsharedfocuses(List<NationalFocus> focuslist) {
        for (NationalFocus focus : focuslist) {
            boolean isnew = true;
            for (int i = 0; i < sharedfocuses.size(); i++) {
                if (sharedfocuses.get(i).id == focus.id) {
                    sharedfocuses.set(i, focus);
                    isnew = false;
                    //add log about rewriting sharedfocus
                    break;
                }
            }
            if (isnew) {
                sharedfocuses.add(focus);
            }
        }
    }
    public boolean hasnodata() {
        return sharedfocuses.isEmpty() && trees.isEmpty();
    }
    public List<NationalFocus> sharedfocuses;
    public List<NationalFocusTree> trees;
    public int selectedtree = 0;
    public int selectedfocus = 0;
}