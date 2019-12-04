package fr.ul.cdg.model;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.util.Vector2;

import java.util.List;
import java.util.Random;

public class Board {
    private List<Ship> shipList;
    private Cells cells;
    public static final int BOARD_SIZE = 10;
    public static final int EMPTY_CELL = 0;
    public static final int EMPTY_FIRED_CELL = 1;
    public static final int OCCUPIED_CELL = 2;
    public static final int OCCUPIED_DAMAGED_CELL = 3;
    private class Cells{
        int[][] array;

        Cells(){
            array = new int[BOARD_SIZE][BOARD_SIZE];
            for(int i = 0; i < BOARD_SIZE; i++){
                for(int j = 0; j < BOARD_SIZE; j++){
                    array[i][j] = EMPTY_CELL;        //ALL THE CELLS INITIALISED AS EMPTY
                }
            }
        }

        int getCell(int x, int y){
            return array[y][x];
        }

        /**
         *
         * @return a position where there is no ship
         */
        public Vector2 getRandomAvailablePosition() {
            Random random = new Random();
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            if(array[x][y] == OCCUPIED_CELL || array[x][y] == OCCUPIED_DAMAGED_CELL){
                return getRandomAvailablePosition();
            }
            else{
                return new Vector2(x, y);
            }
        }

        public void placeShip(Ship s) {
            for (int i = 0; i < s.getNbCells(); i++){
                array[s.getPosition().getY() + (i * s.getOrientation())][s.getPosition().getX() + (i * (1-s.getOrientation()))] = OCCUPIED_CELL;
            }
        }
    }
    public Board(Era era){
        shipList = era.shipCreation();
        cells = new Cells();
    }

    private Ship findBoatAtPosition(Vector2 v) {
        for (Ship s : shipList) {
            int x = s.getPosition().getX();
            int y = s.getPosition().getY();
            for (int i = 0; i < s.getNbCells(); i++) {
                if (v.getX() == x && v.getY() == v.getY()) {
                    return s;
                }
                if (s.getOrientation() == Ship.HORIZONTAL) {
                    x++;
                } else {
                    y++;
                }
            }
        }
        return null;
    }


    public void takeShot(Vector2 pos){
        Ship s = findBoatAtPosition(pos);
        if(s != null){
            s.takeShot();
        }
    }

    public Vector2 getRandomAvailablePosition(){
        return cells.getRandomAvailablePosition();
    }

    /**
     * Place the ships randomly (for AI only)
     */
    public void placeShips(){
        for(Ship s : shipList){
            Vector2 v = getRandomAvailablePosition();
            int canBePlaced = 0;
            Random random = new Random();
            s.setOrientation(random.nextInt(2));
            int x = v.getX(), y = v.getY();
            while (canBePlaced == 0){
                int conflict = 0;
                for(int i = 0; i < s.getNbCells(); i++){
                    if(findBoatAtPosition(new Vector2(x,y)) != null){
                        conflict = 1;
                    }
                }
                if(conflict == 1){
                    v = getRandomAvailablePosition();
                    x = v.getX();
                    y = v.getY();
                }
                else{
                    canBePlaced = 1;
                }
            }
            s.setPosition(v);
            placeShip(s);
        }

    }

    private void placeShip(Ship s) {
        cells.placeShip(s);
    }

}
