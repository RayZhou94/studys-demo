package mode;

import entity.UrlInfo;

import java.util.List;
import java.util.regex.Matcher;

public abstract class AbstractHtmlFactory {

    public String extractBody(String html){

        Matcher matcher = MatchUtil.bodyMatcher(html);
        while (matcher.find()){
            String body = matcher.group(0);
            return body;
        }
        return null;
    }

    public abstract List<UrlInfo> collectUrl(String body);

    public abstract List<UrlInfo> collectImg(String body);
}
