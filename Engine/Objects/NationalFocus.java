package Engine.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Engine.Objects.ObjectAttribute.Offset;
public class NationalFocus extends BasicObject {
    public NationalFocus() {}
    public NationalFocus(String id) {
        this.id = id;
    }
    public String id = "error";
    public String icon = "GFX_goal_unknown";
    public String relative_position_id = null;
    public int cost = 1;
    public int x = 0;
    public int y = 0;
    public int absolute_x = 0;
    public int absolute_y = 0;
    public boolean isloadedlikeshared = false;
    public boolean isshared = false;
    public List<NationalFocus> mutually_exclusive = new ArrayList<NationalFocus>(Collections.emptyList());
    public List<String> otherdata = new ArrayList<String>(Collections.emptyList());
    public List<Offset> offsets = new ArrayList<Offset>(Collections.emptyList());
    public List<List<String>> prerequisites = new ArrayList<List<String>>(Collections.emptyList());
}
