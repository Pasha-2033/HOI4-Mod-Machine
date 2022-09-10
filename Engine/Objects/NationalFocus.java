package Engine.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Engine.Objects.ObjectAttribute.Offset;
public class NationalFocus {
    public NationalFocus(String id) {
        this.id = id;
    }
    public String id;
    public String icon = "GFX_goal_unknown";
    public String relative_position_id = null;
    public int cost = 1;
    public int x = 0;
    public int y = 0;
    public int absolute_x = 0;
    public int absolute_y = 0;
    public List<String> mutually_exclusive;
    public List<String> otherdata;
    public List<Offset> offsets;
    public List<List<String>> prerequisites = new ArrayList<List<String>>(Collections.emptyList());
}
