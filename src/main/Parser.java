package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dsm_025 on 2017-03-13.
 */
public class Parser {
    String url;
    ArrayList<String> entireWordList;
    char[] consonantList = new char[]{'ㄱ', 'ㄴ', 'ㄷ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

    public Parser(String url) {
        this.url = url;
    }

    public void doParse() {
        entireWordList = new ArrayList<>();
        Document doc;
        for (int i = 0; i < consonantList.length; i++) {
            String parameter = "&letter=" + consonantList[i];
            Elements lst_result = getConnection(url + parameter).select(".lst_result").select("li").select("a");
            for (Element element : lst_result) {
                int count = 1;
                parameter = "&letter=" + consonantList[i] + "&page=" + count++ + "&group=" + element.text().replace("~", "");
                while ((doc = getConnection(url + parameter)).toString().indexOf("Query Error.") == -1) {
                    Elements word_list = doc.select(".lst3").select("div").select("a");
                    if (word_list.size() == 0) {
                        break;
                    }
                    for (Element word : word_list) {
                        entireWordList.add(word.text());
                        System.out.println(word.text());
                    }
                    parameter = "&letter=" + consonantList[i] + "&page=" + count++ + "&group=" + element.text().replace("~", "");
                    System.out.println(url + parameter);
                }
            }
//              String parameter = "&Letter=" + (char)i;
//              int count = 2;
//              while((doc = getConnection(url + parameter)) != null){
//                  Elements paragraghList = doc.select(".exp").select("a").select("font");
//                  for(Element element : paragraghList ){
//                        System.out.println(element.text());
//                        entireWordList.add(element.text());
//                  }
//                  parameter = "";
//                  parameter += "&Letter=" + (char)i + "&go=" + count++;
//              }
        }
        System.out.print("파싱완료 \n 사이즈 : " + entireWordList.size());
//        entireWordList = new ArrayList<>();
//        Document doc = getConnection(url);
//        Elements wordCollections = doc.select("td");
//        for(Element element : wordCollections){
//            Elements wordList = element.select("a");
//            for(Element content : wordList){
//                entireWordList.add(content.attr("title").toString());
//            }
//        }
//        String choice = VowelFinder.toKoJasoAtom(entireWordList.get(100));
//
//        int count = 0;
//        String temp;
//        while(count ++ < entireWordList.size() - 1) {
//            if (choice.equals(VowelFinder.toKoJasoAtom(entireWordList.get(count)))){
//                System.out.println("변환 결과 : " + VowelFinder.toKoJasoAtom(entireWordList.get(count)));
//                System.out.println("라임 찾기 결과 : " + entireWordList.get(count));
//            }
//        }
    }

    public Document getConnection(String url) {
        Document document = null;
//        while(true){
//            try {
//                document = Jsoup.connect(url).get();
//            }catch (NullPointerException)
//        }
        while (true) {
            try {

                document = Jsoup.parse(new URL(url), 5500);
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            return document;
        }
    }
}
