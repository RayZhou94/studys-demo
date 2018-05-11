package mode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public abstract class AbstractHtmlFactory {

    public String extractBody(String html){

        Matcher matcher = MatchUtil.bodyMatcher(html);
        while (matcher.find()){
            String body = matcher.group();
            return body;
        }
        return null;
    }

    public List<String> collectUrl(String body){

        List<String> urlList = new ArrayList<>();
        Matcher matcher = MatchUtil.htmlMatcher(body);
        while (matcher.find()){
            String url = matcher.group();
            urlList.add(url);
        }
        return urlList;
    }

    public List<String> collectImg(String body){

        List<String> imgList = new ArrayList<>();
        Matcher matcher = MatchUtil.imgMatcher(body);
        while (matcher.find()){
            String img = matcher.group();
            imgList.add(img);
        }
        return imgList;
    }
}
