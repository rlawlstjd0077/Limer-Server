package main;

import org.apache.lucene.analysis.kr.morph.AnalysisOutput;
import org.apache.lucene.analysis.kr.morph.MorphAnalyzer;
import org.apache.lucene.analysis.kr.morph.MorphException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by dsm_025 on 2017-03-15.
 */
public class KoreanAnalyzerHandler {
    public ArrayList<String> extractNoun(String text, String url) throws MorphException {
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(text, " ");
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            String chRefEX = ".*[\u2e80-\u2eff\u31c0-\u31ef\u3200-\u32ff\u3400-\u4dbf\u4e00-\u9fbf\uf900-\ufaff].*";
            String enRefEX = "^[a-zA-Z]*$";
            try {
                if (token.matches(chRefEX) == false && token.matches(enRefEX) == false && token.length() >= 1) {

                } else {
                    continue;
                }

                String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
                token = token.replaceAll(match, " ");

                String result;
                if((result = doAnalyize(token)) != null){
                    list.add(result);
                }


            } catch (IndexOutOfBoundsException ex) {

                continue;
            } catch (NullPointerException ex){
                continue;
            }

        }
        return list;
    }
    private String doAnalyize(String token){
        MorphAnalyzer maAnal = new MorphAnalyzer();
        token.replaceAll(" ", "");
        List<AnalysisOutput> indexList = null;

        try {
            indexList = maAnal.analyze(token);
            AnalysisOutput morpheme;
            if((morpheme = indexList.get(0)) != null){
                if (morpheme.getPos() == 'N') {
                    String pattern = "(^[가-힣]*$)";
                    if (Pattern.matches(pattern, morpheme.getStem()) == true && morpheme.getStem().length() != 1) {
                        return morpheme.getStem();
                    }
                }
            }
        } catch (MorphException e) {
            e.printStackTrace();
        }
        return null;
    }
}
