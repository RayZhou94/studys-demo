package mode;

import entity.UrlInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class HtmlFactory extends AbstractHtmlFactory {

    @Override
    public List<UrlInfo> collectUrl(String body){

        List<UrlInfo> urlList = new ArrayList<>();
        Matcher matcher = MatchUtil.htmlMatcher(body);
        while (matcher.find()){
            String url = matcher.group();

            UrlInfo urlInfo = new UrlInfo();
            urlInfo.setUrl(url);
            urlList.add(urlInfo);
        }
        return urlList;
    }

    @Override
    public List<UrlInfo> collectImg(String body){

        List<UrlInfo> imgList = new ArrayList<>();
        Matcher matcher = MatchUtil.imgMatcher(body);
        while (matcher.find()){
            String img = matcher.group();

            UrlInfo imgInfo = new UrlInfo();
            imgInfo.setUrl(img);

            imgList.add(imgInfo);
        }
        return imgList;
    }
}
