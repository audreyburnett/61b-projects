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

        WordNet wn = new WordNet(synsetFile, hyponymFile);

        HugNgordnetServer hns = new HugNgordnetServer();
        hns.startUp();

        hns.register("hyponyms", new HyponymsHandler(wn));

        return null;
    }
}
