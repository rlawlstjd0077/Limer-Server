package main;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.lucene.analysis.kr.morph.MorphException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

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

    public PageParser(String startUrl, int timeout){
        url = startUrl;
        urlQueue = new LinkedList<>();
        urlQueue.add(url);
        timer = new Timer();
        timer.schedule(new TimeCountTask(), 1000);
        this.timeout = timeout;
        this.validator = new UrlValidator();
        this.handler = new KoreanAnalyzerHandler();
    }

    public void doParse(){
        while(timeCount < timeout){
            try {
                handler.extractNounAndAdjective(Jsoup.connect(urlQueue.poll()).get().toString());
                //href String 들 큐에 저장
                Elements elements = Jsoup.connect(urlQueue.poll()).get().select("a");
                for(Element element : elements){
                    if(validator.isValid(element.attr("href"))) {
                        urlQueue.add(element.attr("href"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MorphException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TimeCountTask extends TimerTask{
        @Override
        public void run() {
            timeCount ++;
        }
    }
}
