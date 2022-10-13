package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap a;
    public HistoryTextHandler(NGramMap map) {
        a = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String response = "";
        for (String x : words) {
            response += x + ": " + a.weightHistory(x).toString() + "\n";
        }
        return response;
    }
}
