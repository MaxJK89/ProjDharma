import java.util.HashMap;

public class ColorSec {

    public static String WHITE = "#FFFFFF";
    public static String BLACK = "#000000";
    public static String RED = "#f40002";
    public static String BLUE = "#0000fe";
    public static String GREEN = "#04ff00";
    public static String ORANGE = "#ff7b00";
    public static String YELLOW = "#ffff00";
    public static String PURPLE = "#fe00ff";



    public static HashMap<String, String> colorDecode = new HashMap<String, String>();

    public static void setUpHashMap() {
        colorDecode.put(WHITE, "I see white");
        colorDecode.put(BLACK, "I see BLACK");
        colorDecode.put(RED, "I see red");
        colorDecode.put(BLUE, "I see BLUE");
        colorDecode.put(GREEN, "I see GREEN");
        colorDecode.put(ORANGE, "I see ORANGE!");
        colorDecode.put(YELLOW, "I see yellow!");
        colorDecode.put(PURPLE, "I see PURPLE!");
    }
}