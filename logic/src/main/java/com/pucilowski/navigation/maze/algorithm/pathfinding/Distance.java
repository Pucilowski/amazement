package com.pucilowski.navigation.maze.algorithm.pathfinding;

import com.pucilowski.navigation.maze.grid.Cell;

/**
 * Created by martin on 15/11/13.
 */
public interface Distance {

    public double weight(Cell a, Cell b);

    public double heuristic(Cell a, Cell b);

}
