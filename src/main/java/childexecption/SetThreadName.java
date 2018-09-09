package childexecption;

public class SetThreadName {
    public static String setName(String originName) {
        String finalName = "huke thread pool - 1 -thread- " + originName;
        return finalName;
    }
}
