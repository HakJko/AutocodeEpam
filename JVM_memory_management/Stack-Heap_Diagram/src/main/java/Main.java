import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int times = 10;
        String name = "Kevin";
        List<String> list = new ArrayList<>();


        System.out.println(times + fill(list, name + name, times));
    }

    public static int fill(Collection<String> collection, String str, int times) {
        String shrunk = shrink(str);
        System.out.println(shrunk);
        times = (times + shrunk.length()) / 2;
        for (int i = 0; i < times / 2; i++) {
            collection.add(shrunk);
        }
        return times;
    }

    public static String shrink(String str) {
        int newLength = str.length() / 2 + str.length() % 2;
        char[] chars = new char[newLength];
        for (int i = 0; i < str.length(); i += 2) {
            chars[i / 2] = str.charAt(i);
        }
        return new String(chars);
    }
}
