package helper;

public class TypeHelper {
    public static int parseInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch(Exception e) {
            return 0;
        }
    }
}