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
    public ArrayList<String> extractNounAndAdjective(String text) throws MorphException {
        ArrayList<String> list = new ArrayList<>();
        MorphAnalyzer maAnal = new MorphAnalyzer();
        StringTokenizer stringTokenizer = new StringTokenizer(text, " ");
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();

            List<AnalysisOutput> indexList = null;
            try {
                if(Pattern.matches("(^[-龥]*$)", token) == false) {
                    indexList = maAnal.analyze(token);
                }else{
                    continue;
                }
            }catch (IndexOutOfBoundsException ex){
                ex.printStackTrace();
            }
            for (AnalysisOutput morpheme : indexList) {
                if (morpheme.getPos() == 'N') {
                    String pattern = "(^[가-힣]*$)";
                    if (Pattern.matches(pattern, morpheme.getStem()) == true && morpheme.getStem().length() != 1) {
                        System.out.println(morpheme.getStem());
                        list.add(morpheme.getStem());
                    }
                }
            }
        }
        return list;
    }
}
