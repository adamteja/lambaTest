import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TagCompare {

    public static HashMap<String, compareMethod> keymap = new HashMap<String, TagCompare.compareMethod>();

    public TagCompare(){
        TagCompare.keymap.put("default", TagCompare::defaultCompare);
        TagCompare.keymap.put("guid", TagCompare::guidCompare);
        TagCompare.keymap.put("url", TagCompare::urlCompare);
        TagCompare.keymap.put("event", TagCompare::eventsCompare);
        TagCompare.keymap.put("ignore", TagCompare::ignore);
    }

    public interface compareMethod
    {
        boolean operation(String source, String target);

    }

    public HashMap<String, compareMethod> getKeymap(){
        return keymap;
    }

    public static boolean doCompare(String source, String target, compareMethod op){
        return op.operation(source, target);
    }

    /// compare operations

    public static boolean defaultCompare(String source, String target){
        return source.equals(target);
    }

    public static boolean existsOnly(String source, String target){

        //value exists only
        if( (source != null && source != "")&& (target != null && target != "")){
            return true;
        }
        else{
            return false;
        }

    }

    public static boolean guidCompare(String source, String target){

        if( source.matches("^[0-9A-F\\-]+$") && target.matches("^[0-9A-F\\-]+$") ){
            return true;
        }else{
            return false;
        }
    }

    public static boolean urlCompare(String source, String target){

        try{
            URL sURL = new URL(source);
            URL tURL = new URL(target);
            return true;
        }catch(MalformedURLException e){
            return false;
        }
    }

    public static boolean eventsCompare(String source, String target){

        List<String> sEvents = Arrays.asList(source.split(","));
        List<String> tEvents = Arrays.asList(target.split(","));

        // make sure the target is same or bigger than source.
        // bigger accounts for added tags
        if(tEvents.size() >= sEvents.size()){
            for(int s=0; s<sEvents.size(); s++){
                if( ! tEvents.get(s).contains(sEvents.get(s)) ){
                    return false; // source event not in target
                };
            }
        }else{
            return false; // there are less events in the target, something is missing
        }

        return true;
    }

    public static boolean ignore(String source, String target) {
        return true;
    }



}
