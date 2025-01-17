package ngordnet.proj2b_testing;

import ngordnet.hugbrowsermagic.HugNgordnetServer;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.main.WordNet;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        WordNet wn = new WordNet(synsetFile, hyponymFile, wordFile, countFile);
        HyponymsHandler handler = new HyponymsHandler(wn);

        return handler;
    }
}
