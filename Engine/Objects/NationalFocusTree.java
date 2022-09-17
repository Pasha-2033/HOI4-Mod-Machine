package Engine.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Engine.Objects.ObjectAttribute.Offset;
public class NationalFocusTree {
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
}