package Engine.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Engine.Objects.ObjectAttribute.Offset;
public class NationalFocusTree extends BasicObject {
    public NationalFocusTree(){}
    public NationalFocusTree(String id){
        this.id = id;
    }
    public String id;
    public boolean isdefault = false;
    public boolean reset_on_civilwar = false;
    public int continuous_focus_position_x = 0;
    public int continuous_focus_position_y = 0;
    public List<String> otherdata = new ArrayList<String>(Collections.emptyList());
    public List<Offset> offsets = new ArrayList<Offset>(Collections.emptyList());
    public List<NationalFocus> nationalfocuses = new ArrayList<NationalFocus>(Collections.emptyList());
    public int[] focus_xy(NationalFocus focus, List<String> activetriggers) {
        return focus_xy(nationalfocuses.indexOf(focus), activetriggers);
    }
    public int[] focus_xy(int index, List<String> activetriggers) {
        NationalFocus focus = nationalfocuses.get(index);
        int x = focus.x;
        int y = focus.y;
        for (Offset o : focus.offsets) {
            if (activetriggers.contains(o.trigger_id)) {
                x = o.x;
                y = o.y;
                break;
            }
        }
        if (focus.relative_position_id != null) {
            for (NationalFocus f : nationalfocuses) {
                if (f.id.equals(focus.relative_position_id)) {
                    int[] xy = focus_xy(f, activetriggers);
                    x += xy[0];
                    y += xy[1];
                    break;
                }
            }
        }
        return new int[]{x, y};
    }
}