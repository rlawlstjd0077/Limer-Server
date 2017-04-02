package main;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.lucene.analysis.kr.morph.MorphException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

/**
 * Created by dsm_025 on 2017-03-16.
 */
public class PageParser {
    private String url;
    private Queue<String> urlQueue;
    private Timer timer;
    private int timeout;
    private static int timeCount = 0;
    private UrlValidator validator;
    private KoreanAnalyzerHandler handler;
    private MongoClientManager manager;

    public PageParser(String startUrl, int timeout) {
        url = startUrl;
        urlQueue = new LinkedList<>();
        urlQueue.add(url);
        timer = new Timer();
        timer.schedule(new TimeCountTask(), 1000);
        this.timeout = timeout;
        this.validator = new UrlValidator();
        this.handler = new KoreanAnalyzerHandler();
        manager = MongoClientManager.getInstance();
    }

    public void doParse() {
        int cnt = 0;
            System.out.println("=============" + cnt++ + "번 째 파싱 중 ==============");
            try {
                String url = urlQueue.poll();
                ArrayList<String> list = handler.extractNoun(Jsoup.connect(url).get().toString(), url);
                System.out.println(url + " 에서 " + list.size() + " 개의 단어 추출됨.");
                for(String s : list){
                    System.out.println(s);
                }
                manager.putData(list);

                //href String 들 큐에 저장
                Elements elements = Jsoup.connect(url).get().select("a");
                for (Element element : elements) {
                    if (validator.isValid(element.attr("href"))) {
                        urlQueue.add(element.attr("href"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MorphException e) {
                e.printStackTrace();
            }
    }

    public static class TimeCountTask extends TimerTask {
        @Override
        public void run() {
            timeCount++;
        }
    }
}
