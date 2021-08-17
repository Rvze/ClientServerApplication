package subsidiary;

public class InputChecker {
    static boolean checkInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    static boolean checkLong(String str) {
        try {
            Long.parseLong(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    static boolean checkFloat(String str) {
        try {
            Float.parseFloat(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
