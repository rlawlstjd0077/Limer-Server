package main;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.ObservableArray;
import org.apache.lucene.analysis.kr.morph.MorphException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dsm_025 on 2017-03-13.
 */
public class TestDriver {
    public static void main(String args[]) {
//        Parser parser = new Parser("http://krdic.naver.com/list.nhn?");
//        parser.doParse();

//        KoreanAnalyzerHandler koreanAnalyzerHandler = new KoreanAnalyzerHandler();
//        try {
//            ArrayList<String> tempList = koreanAnalyzerHandler.extractNoun(Jsoup.connect("http://news.naver.com/main/hotissue/read.nhn?mid=hot&sid1=100&cid=1059834&iid=1183583&oid=421&aid=0002612344&ptype=052").get().toString());
//            for(String s : tempList){
//                System.out.println(s);
//            }
//        } catch (MorphException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        MongoClientManager clientManager = MongoClientManager.getInstance();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("진성");
//        list.add("기황");
//        list.add("성빈");
//        clientManager.putData(list);


//        KoreanAnalyzerHandler handler = new KoreanAnalyzerHandler();
//        try {
//            handler.extractNoun("열렸을 경우");
//        } catch (MorphException e) {
//            e.printStackTrace();
//        }

//        MongoClientManager manager = MongoClientManager.getInstance();
//        Cursor cursor = manager.getCollection().find();
//        ArrayList<String> list = new ArrayList<>();
//
//        while (cursor.hasNext()) {
//            list.add(cursor.next().get("name").toString());
//        }
//        Scanner scanner = new Scanner(System.in);
//        String word = scanner.next();
//        String result = VowelFinder.lastCharJasoAtom(word.charAt(word.length() - 1));
//        for (String s : list) {
//            try {
//                if (result.equals(VowelFinder.lastCharJasoAtom(s.charAt(s.length() - 1)))) {
//                    System.out.println(s);
//                }
//            } catch (StringIndexOutOfBoundsException ex) {
//                System.out.println("오류 발생 단어 : " + s);
//                continue;
//            }
//        }
//        System.out.println("변환 완료");

        PageParser pageParser = new PageParser("https://namu.wiki/w/%EB%82%98%EB%AC%B4%EC%9C%84%ED%82%A4:%EB%8C%80%EB%AC%B8", 10);
        pageParser.doParse();
    }
}
