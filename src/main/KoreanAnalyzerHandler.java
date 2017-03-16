package main;

import org.apache.lucene.analysis.kr.morph.AnalysisOutput;
import org.apache.lucene.analysis.kr.morph.MorphAnalyzer;
import org.apache.lucene.analysis.kr.morph.MorphException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by dsm_025 on 2017-03-15.
 */
public class KoreanAnalyzerHandler {
    public ArrayList<String> extractNounAndAdjective(String text) throws MorphException {
        ArrayList<String> list = new ArrayList<>();
        MorphAnalyzer maAnal = new MorphAnalyzer();
        StringTokenizer stringTokenizer = new StringTokenizer(text, " ");
        while(stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();

            List<AnalysisOutput> indexList = maAnal.analyze(token);
            for(AnalysisOutput morpheme : indexList){
                if(morpheme.getPos() == 'N') {
                    System.out.println(morpheme.getStem());
                    list.add(morpheme.getStem());
                }
            }
        }
        return list;
    }
}
