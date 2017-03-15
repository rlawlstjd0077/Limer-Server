package main;

/**
 * Created by dsm_025 on 2017-03-13.
 */
public class TestDriver {
    public static void main(String args[]){
        Parser parser = new Parser("http://krdic.naver.com/list.nhn?");
        parser.doParse();
    }
}
