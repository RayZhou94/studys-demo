package mode;

import entity.UrlInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Created by shallowdream on 2018/5/12.
 */
public class JsoupFactory extends AbstractHtmlFactory {

    public List<UrlInfo> extractHtml(String url){
        return null;
    }

    @Override
    public List<UrlInfo> collectUrl(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    //模拟火狐浏览器
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = document.select("a[abs:href]").select("a[target=_blank]");
        HashMap<String, String> urlMap = new HashMap<>(links.size());
        for (Element element: links){
            String href = element.attr("href");
            String text = element.text();
            if (href.startsWith("htm")){
                urlMap.put(href, text);
            }
        }
        List<UrlInfo> urlInfoList = new ArrayList<>(urlMap.size());
        urlMap.entrySet().stream().forEach(entry->{
            UrlInfo urlInfo = new UrlInfo();
            urlInfo.setUrl(url+entry.getKey());
            urlInfo.setText(entry.getValue());
            urlInfoList.add(urlInfo);
        });

        urlInfoList.stream().forEach(System.out::println);
        return urlInfoList;
    }

    @Override
    public List<UrlInfo> collectImg(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    //模拟火狐浏览器
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        Set<UrlInfo> set = new HashSet<>(links.size());
        for (Element element: links){
            String href = element.attr("src");
            UrlInfo urlInfo = new UrlInfo();
            urlInfo.setUrl(href);
            set.add(urlInfo);
        }
        return new ArrayList<>(set);
    }
}
