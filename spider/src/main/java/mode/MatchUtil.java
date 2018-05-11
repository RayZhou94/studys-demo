package mode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchUtil {

    private static final String HTML_REGEX = ".+\\.(html|htm|com)";
    private static final String BODY_REGEX = "<body>.+</body>";
    private static final String IMG_REGEX = ".+\\.(jpg|jpeg|gif|png)";

    private static Pattern HTML_PATTERN = Pattern.compile(HTML_REGEX);
    private static Pattern BODY_PATTERN = Pattern.compile(BODY_REGEX);
    private static Pattern IMG_PATTERN = Pattern.compile(IMG_REGEX);

    public static Matcher htmlMatcher(String input){
        return HTML_PATTERN.matcher(input);
    }

    public static Matcher bodyMatcher(String input){
        return BODY_PATTERN.matcher(input);
    }

    public static Matcher imgMatcher(String input){
        return IMG_PATTERN.matcher(input);
    }

}
