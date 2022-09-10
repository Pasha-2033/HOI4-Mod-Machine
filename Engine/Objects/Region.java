package Engine.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Region {
    public Region(int id){
        this.id = id;
        this.name = "STATE_" + id;
    }
    public int id;
    public String name;
    public String state_category = "wasteland";
    public List<Integer> provinces = new ArrayList<Integer>(Collections.emptyList());
    public float buildings_max_level_factor = 1;
    public float local_supplies = 0;
    public List<History> history = new ArrayList<History>(Collections.emptyList());
    public List<Resource> resources = new ArrayList<Resource>(Collections.emptyList());
    public List<VictoryPoint> victorypoints = new ArrayList<VictoryPoint>(Collections.emptyList());
    public class History {
        public History(){
            this(null);
        }
        public History(String date){
            this.date = date;
        }
        public String date;
        //create effect list
    }
    public class Resource {
        public Resource(String token, int value){
            this.token = token;
            this.value = value;
        }
        public String token;
        public int value;
    }
    public class VictoryPoint {
        public VictoryPoint(int province, int value){
            this.province = province;
            this.value = value;
        }
        public int province;
        public int value;
    }
}
