package Engine.WorkEnvironment;
public abstract class Container {
    public Container(String folder, String file, String type){
        this.folder = folder;
        this.file = file;
        this.type = type;
    }
    public String folder;
    public String file;
    public String type;
    public String HLPL = null;  //HLPL syntax for fast code load, when its` file selected 
    public abstract String ToPDXCode();
    public abstract String ToHLPLCode();
    public String GetRelativePath(){
        return folder + "/" + file + "." + type;
    }
}