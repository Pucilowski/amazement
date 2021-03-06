package com.pucilowski.navigation.ui.swing.panels;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.mazes.process.Task;
import com.pucilowski.navigation.mazes.process.pathfinding.AStar;
import com.pucilowski.navigation.mazes.process.pathfinding.SearchMeta;
import com.pucilowski.navigation.mazes.grid.Cell;
import com.pucilowski.navigation.mazes.grid.Edge;
import com.pucilowski.navigation.mazes.grid.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by martin on 11/12/13.
 */
public class GridPanel extends JPanel {

    final Environment main;


    int xOffset;
    int yOffset;

    public GridPanel(Environment main) {
        this.main = main;

        resize();
    }

    Rectangle r;

    public void resize() {
        Grid grid = main.grid;

        grid.resize(getBounds());

       /* PointD d = grid.getScaledSize();
        double sWidth = d.x;
        double sHeight = d.y;

        cellSize = (int) Math.floor(Math.min((double) getWidth() / sWidth, (double) getHeight() / sHeight));

        grid.resize(cellSize);*/

        Point size = grid.getSize();
        int width = size.x;
        int height = size.y;

        xOffset = (int) ((getWidth() - width) / 2D);
        yOffset = (int) ((getHeight() - height) / 2D);
        r = new Rectangle(xOffset, yOffset, width, height);

        //System.out.println(width + ", " + height);


        //main.gui.frame.invalidate();

    }

    public static final Color[] colors = {
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.BLUE,
            Color.MAGENTA,
            Color.CYAN
    };


    @Override
    public void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;

        resize();


        Grid grid = main.grid;
        Task algo = main.logic.getTask();


        //g.setColor(Color.RED);
        //((Graphics2D) g).draw(r);

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                Cell cell = grid.cells[x][y];

                //Point location = grid.getLocation(cell, size);
                Point location = grid.getLocation(cell);
                int px = xOffset + location.x;
                int py = yOffset + location.y;

                //Polygon polygon = grid.getPolygon(cell, size);
                Polygon polygon = grid.getPolygon(cell);
                polygon.translate(px, py);


                if (algo != null) {
                    Color c = algo.getColor(cell);

                    if (c != null) {
                        g.setColor(c);
                        //px = 8 + size/2 * x;
                        //py = 8 + size * y;
                        g.fillPolygon(polygon);
                    }
                }

                for (int index = 0; index < cell.sides; index++) {
                    Edge n = cell.edges[index];

                    if (n != null && grid.isConnected(n)) {
                        //g.setColor(Color.LIGHT_GRAY);
                        //g.setColor(new Color(230, 230, 230, 255));
                        continue;
                    } else {
                        g.setColor(Color.BLACK);
                        //g.setColor(colors[side]);

                    }

                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


                    Polygon side = grid.getSide(cell, index);
                    side.translate(px, py);
                    g.drawPolygon(side);
                }


            }
        }


        if (algo != null) {

            for (int y = 0; y < grid.height; y++) {
                for (int x = 0; x < grid.width; x++) {
                    //if(x%2!=1 )continue;

                    Cell cell = grid.cells[x][y];

                    //Point location = grid.getLocation(cell, size);
                    Point location = grid.getLocation(cell);
                    int px = xOffset + location.x;
                    int py = yOffset + location.y;

                    //Polygon polygon = grid.getPolygon(cell, size);
                    Polygon polygon = grid.getPolygon(cell);
                    polygon.translate(px, py);


                    if (cell.equals(algo.getCurrent())) {

                        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


                        g.setColor(Color.BLACK);

                        g.drawPolygon(polygon);

                    }

                    if (algo instanceof AStar) {
                        SearchMeta sm = ((AStar) algo).getMeta(cell);
                        //g.drawString(sm.fScore + " / " + sm.gScore, px, py);

                        if (sm.gScore > 0)
                            g.drawString((int)sm.gScore + "", px-4, py+16);
                    }

                    /*if (algo instanceof Kruskals) {
                        CellMeta sm =algo.getMeta(cell);
                        //g.drawString(sm.fScore + " / " + sm.gScore, px, py);

                            g.drawString((int)sm.depth + "", px+8, py+16);
                    }*/

                }
            }
        }

    }


}
