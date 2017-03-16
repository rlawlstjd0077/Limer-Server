package main;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.lucene.analysis.kr.morph.MorphException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dsm_025 on 2017-03-13.
 */
public class TestDriver {
    public static void main(String args[]){
//        Parser parser = new Parser("http://krdic.naver.com/list.nhn?");
//        parser.doParse();

//        KoreanAnalyzerHandler koreanAnalyzerHandler = new KoreanAnalyzerHandler();
//        try {
//            ArrayList<String> tempList = koreanAnalyzerHandler.extractNounAndAdjective(Jsoup.connect("http://news.naver.com/main/hotissue/read.nhn?mid=hot&sid1=100&cid=1059834&iid=1183583&oid=421&aid=0002612344&ptype=052").get().toString());
//            for(String s : tempList){
//                System.out.println(s);
//            }
//        } catch (MorphException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        PageParser pageParser = new PageParser("http://www.naver.com/", 10);
        pageParser.doParse();
    }
}
