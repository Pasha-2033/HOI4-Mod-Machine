package Engine.Objects.ObjectAttribute;
public class Offset {
    public Offset(int x, int y, String trigger_id){
        this.x = x;
        this.y = y;
        this.trigger_id = trigger_id;
    }
    public boolean istriggered = false;
    public int x;
    public int y;
    public String trigger_id;
}