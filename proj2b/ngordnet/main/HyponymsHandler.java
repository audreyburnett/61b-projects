package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;

import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;
    public HyponymsHandler(WordNet wn) {
        this.wn = wn;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String stringrep = wn.hyponymIDList();
        return stringrep;


//        return wn.doSomething();
    }
}
