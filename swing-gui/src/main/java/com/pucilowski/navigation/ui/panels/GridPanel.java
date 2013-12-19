package com.pucilowski.navigation.ui.panels;

import com.pucilowski.navigation.maze.model.grid.BooleanGrid;
import com.pucilowski.navigation.maze.model.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by martin on 11/12/13.
 */
public abstract class GridPanel extends JPanel {

    public GridPanel() {

    }

    public abstract Grid getGrid();

    public void paintComponent(Graphics g) {
        Grid grid =getGrid();
        BooleanGrid bGrid = grid.booleanGrid;


        int w = bGrid.width;
        int h = bGrid.height;


        int size = Math.min(getWidth() / w, getHeight() / h);

        for (int y = 0; y < bGrid.height; y++) {
            for (int x = 0; x < bGrid.width; x++) {
                //Cell cell = grid.cells[x][y];
                boolean walkable = bGrid.passage[x][y];


                int d = 0;
                if (x % 2 == 0) d = size / 2;

                int px = size * x;
                int py = size * y + d;

/*
                float ratio = (float) cell.depth / (float) getGrid().maxDepth;

                float[] end = new float[3];
                for (int i = 0; i < 3; i++) {
                    end[i] = Lerp.rainbow[i].interpolate(ratio);
                }

                Color c = new Color(end[0], end[1], end[2], 150f / 255f);

                g.setColor(c);*/


                if (walkable) {
                    g.setColor(Color.WHITE);
                    g.fillRect(px, py, size - 1, size - 1);
                    //g.setColor(Color.LIGHT_GRAY);
                    //g.drawRect(px, py, size, size);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(px, py, size - 1, size - 1);
                    //g.setColor(Color.LIGHT_GRAY);
                    //g.drawRect(px, py, size - 1, size - 1);
                }


            }
        }

    }

}
