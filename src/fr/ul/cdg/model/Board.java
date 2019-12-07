package fr.ul.cdg.model;

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
    public static final int INTERVAL_NEAR = 5;


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
        public Vector2 getRandomAvailablePosition(int size, int orientation) {
            Random random = new Random();
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);
            if((array[y][x] == OCCUPIED_CELL || array[y][x] == OCCUPIED_DAMAGED_CELL) || x + (size* (1- orientation)) >= BOARD_SIZE || y + (size*orientation) >= BOARD_SIZE){
                return getRandomAvailablePosition(size, orientation);
            }
            else{
                return new Vector2(x, y);
            }
        }

        public void placeShip(Ship s) {
            for (int i = 0; i < s.getNbCells(); i++){
                //System.out.println(s.getPosition().getY() + (i * s.getOrientation()));
                //System.out.println(s.getPosition().getX() + (i * (1-s.getOrientation())));
                array[s.getPosition().getY() + (i * s.getOrientation())][s.getPosition().getX() + (i * (1-s.getOrientation()))] = OCCUPIED_CELL;
            }
        }

        public Vector2 getRandomShotPosition() {
            Random random = new Random();
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);
            if (isNotFiredCell(x, y)) {
                setCellFired(x, y);
                return new Vector2(x, y);
            }
            return getRandomShotPosition();
        }

        /**
         * We start at the bottom left corner of the circle and
         * we add a random int too it coordinates
         * @param pos the position you want to be near
         * @return a random Vector2 near the given pos
         */
        public Vector2 getRandomShotNearPosition(Vector2 pos) {
            Random random = new Random();
            int diameter = BOARD_SIZE/INTERVAL_NEAR;
            int radius = diameter/2;
            int x = pos.getX() - radius + random.nextInt(diameter);
            int y = pos.getY() - radius + random.nextInt(diameter);
            if (isNotFiredCell(x, y)) {
                setCellFired(x,y);
                return new Vector2(x, y);
            }
            return getRandomShotNearPosition(pos);
        }

        /**
         * Set the cell in the given coordinate at fired
         * @param x posX
         * @param y posY
         */
        private void setCellFired(int x, int y){
            if(array[y][x] == EMPTY_CELL){
                array[y][x] = EMPTY_FIRED_CELL;
            }
            else{
                array[y][x] = OCCUPIED_DAMAGED_CELL;
            }
        }

        /**
         * Test if the cell can be fired on
         * @param x posX
         * @param y posY
         * @return tru if the cell is not damaged or fired
         */
        private boolean isNotFiredCell(int x, int y) {
            return array[y][x] != EMPTY_FIRED_CELL && array[y][x] != OCCUPIED_DAMAGED_CELL;
        }
    }




    /**
     * @param shipList the list of the ship who will be placed on the board
     */
    public Board(List<Ship> shipList){
        this.shipList = shipList;
        cells = new Cells();
    }

    /**
     * Give the ship at the given position
     * @param v the given position
     * @return the ship at the given position or null
     */
    public Ship findBoatAtPosition(Vector2 v) {
        for (Ship s : shipList) {
            Vector2 vectorBoat = s.getPosition();
            if(vectorBoat != null) {
                int x = vectorBoat.getX();
                int y = vectorBoat.getY();
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
        }
        return null;
    }


    public void takeShot(Vector2 pos){
        Ship s = findBoatAtPosition(pos);
        if(s != null){
            s.takeShot();
        }
    }

    public Vector2 getRandomAvailablePosition(int size, int orientation){
        return cells.getRandomAvailablePosition(size, orientation);
    }

    /**
     * Place the ships randomly (for AI only)
     */
    public void placeShips(){
        int nbPlaced = 0;
        for(Ship s : shipList){

            boolean canBePlaced = false;
            Random random = new Random();

            s.setOrientation(random.nextInt(2));
            Vector2 v = getRandomAvailablePosition(s.getNbCells(), s.getOrientation());
            int x = v.getX(), y = v.getY();
            while (!canBePlaced){

                boolean conflict = false;
                for(int i = 0; i < s.getNbCells(); i++){
                    //System.out.println(x + " " + y);
                    if(cells.getCell(x,y) == OCCUPIED_CELL || cells.getCell(x,y) == OCCUPIED_DAMAGED_CELL || x >= BOARD_SIZE || y >= BOARD_SIZE){
                        conflict = true;
                    }
                    if(s.getOrientation() == 0){
                        x++;
                    }
                    else{
                        y++;
                    }
                }
                if(conflict){
                    v = getRandomAvailablePosition(s.getNbCells(), s.getOrientation());
                    x = v.getX();
                    y = v.getY();
                }
                else{
                    canBePlaced = true;
                }
                //System.out.println(v);
                //System.out.println(s.getOrientation());
                //System.out.println(printCells());

            }
            nbPlaced++;
            System.out.println(nbPlaced);
            s.setPosition(v);
            //System.out.println(s.getPosition());
            placeShip(s);
        }


    }

    public Vector2 getRandomShotNearPosition(Vector2 pos) {return cells.getRandomShotNearPosition(pos);}
    public Vector2 getRandomShotPosition() {
        return cells.getRandomShotPosition();
    }

    private void placeShip(Ship s) {
        cells.placeShip(s);
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public String printCells(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++) {
                sb.append(cells.getCell(i,j));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
