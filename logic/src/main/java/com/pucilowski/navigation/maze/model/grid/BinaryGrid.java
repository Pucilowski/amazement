package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Neighborship;

import java.awt.*;

/**
 * Created by martin on 19/12/13.
 */
public class BinaryGrid {

    public final int width;
    public final int height;
    public final boolean[][] passage;

    public BinaryGrid(Grid grid) {
        this.width = grid.width * 2 + 1;
        this.height = grid.height * 2 + 1;
        this.passage = new boolean[width][height];

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //passage[x][y] = true;
            }
        }

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                Cell cell = grid.cells[x][y];

                if(cell.walls==0)continue;

                int nx = 2 * x + 1;
                int ny = 2 * y + 1;

                passage[nx][ny] = true;

                for (int index = 0; index < grid.sides; index++) {
                    //System.out.println("Waaat"+index);

                    int flag = (int) Math.pow(2, index);

                    //System.out.println("WallS: "+cell.walls + " flag: " + flag);

                    if ((cell.walls & flag) == flag) {
                        Cell n = grid.getAdjacentTile(cell, index);
                        if(n==null)continue;
                        Point p = grid.getOffset( n,index);


                        int px = nx + p.x;
                        int py = ny + p.y;


                        passage[px][py] = true;



                    }



                }


            }
        }

    }

}
