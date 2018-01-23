package graphen;

/**
 * @author parndt
 * class used as return value for shortest path calculations
 */
public class Path {
    private String path;
    private int distance;

    Path(String _path, int _distance) {
        path = _path;
        distance = _distance;
    }

    public String getPath() {
        return path;
    }

    public int getDistance() {
        return distance;
    }
}
