package ngordnet.main;

public class DepthFirstPaths {
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths (HyponymsGraph G, int s) {
        //
        dfs(G, s);
    }

    private void dfs (HyponymsGraph G, int v) {
        for (int w : G.getAdjList(v)) {
            edgeTo[w] = v;
            dfs(G, w);
        }
    }
}
