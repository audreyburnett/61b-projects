package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;

import java.util.ArrayList;
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

        ArrayList stringrep = wn.hyponymIDList(words.get(0));
        return stringrep;


//        return wn.doSomething();
    }
}
