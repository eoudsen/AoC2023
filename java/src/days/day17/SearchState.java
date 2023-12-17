package days.day17;

import java.util.LinkedList;
import java.util.List;

public class SearchState extends State {

    private final MapPoint location;
    private final int cost;
    private final MapPoint direction;
    private final int dir_moves;
    private final TwoDimensionalMap<Character> map;

    public SearchState(final MapPoint location, final int cost, final MapPoint direction, final int dir_moves, final TwoDimensionalMap map) {
        this.location = location;
        this.cost = cost;
        this.direction = direction;
        this.dir_moves = dir_moves;
        this.map = map;
    }

    public boolean isTerm() {
        if (dir_moves <= 3 && location.getX() == map.high_x && location.getY() == map.high_y) {
            return true;
        }
        return false;
    }
    public String toString() {
        return location + " " + direction + " " + dir_moves;
    }

    public List<State> next() {
        final LinkedList<State> lst = new LinkedList<>();
        if (dir_moves > 3) {
            return lst;
        }

        if (dir_moves <= 2) {
            final MapPoint np = location.add(direction);
            final int c = Day17.getCostP(np, map);
            if (c >= 0) {
                lst.add(new SearchState(np, cost+c, direction, dir_moves+1, map));
            }
        }
        {
            final MapPoint nd = Navigator.turnLeft(direction);
            final MapPoint np = location.add(nd);

            int c = Day17.getCostP(np, map);
            if (c >= 0) {
                lst.add(new SearchState(np, cost+c, nd, 1, map));
            }

        }
        {
            final MapPoint nd = Navigator.turnRight(direction);
            final MapPoint np = location.add(nd);

            final int c = Day17.getCostP(np, map);
            if (c >= 0) {
                lst.add(new SearchState(np, cost+c, nd, 1, map));
            }
        }
        return lst;
    }

    public double getCost() {
        return cost;
    }
}