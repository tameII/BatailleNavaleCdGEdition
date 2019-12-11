package fr.ul.cdg.model;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.util.Vector2;

import java.util.LinkedList;
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
            int tries = BOARD_SIZE*BOARD_SIZE;
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);
            //Try to get a location at random
            while(tries>0 && !isNotFiredCell(x,y)){
                x = random.nextInt(BOARD_SIZE);
                y = random.nextInt(BOARD_SIZE);
                tries--;
            }
            if (isNotFiredCell(x, y)) {
                return new Vector2(x, y);
            }
            //Fail-safe : if no firing location could be found at random, enumerate every cell until one is available
            for(int i=0 ; i < BOARD_SIZE ; i++){
                for(int j=0 ; j < BOARD_SIZE; j++){
                    if(isNotFiredCell(j,i)){
                        x=j;
                        y=i;
                        break;
                    }
                }
                if(isNotFiredCell(x,y)) break;
            }
            return new Vector2(x,y);
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
            int x = pos.getX() - radius + random.nextInt(diameter+1);
            int y = pos.getY() - radius + random.nextInt(diameter+1);
            int tries = 4*radius*radius;
            //Try to get a location near pos
            while(tries > 0 && isNotFiredCell(x,y)){
                x = pos.getX() - radius + random.nextInt(diameter+1);
                y = pos.getY() - radius + random.nextInt(diameter+1);
                tries--;
            }
            if (isNotFiredCell(x, y)) {
                return new Vector2(x, y);
            }
            //Fail-safe : return a position at random
            return getRandomShotPosition();
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
         * @return true if the cell is not damaged or fired
         */
        private boolean isNotFiredCell(int x, int y) {
            return array[y][x] != EMPTY_FIRED_CELL && array[y][x] != OCCUPIED_DAMAGED_CELL;
        }
    }

    public class FiredShots{
        public int x;
        public int y;
        public int state;

        private FiredShots(int x, int y, int state){
            this.state=state;
            this.x=x;
            this.y=y;
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
                    if (v.getX() == x && v.getY() == y) {
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

    public boolean takeShot(Vector2 pos){
        if(cells.isNotFiredCell(pos.getX(),pos.getY())) {
            Ship s = findBoatAtPosition(pos);
            if (s != null) {
                s.takeShot();
            }
            cells.setCellFired(pos.getX(), pos.getY());
            return true;
        }
        return false;
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

    public void placeShip(Ship s) {
        cells.placeShip(s);
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public int getFleetHp(){
        int val=0;
        for (Ship s : getShipList()){
            val+=s.getHp();
        }
        return val;
    }

    public List<FiredShots> getFiredShots(){
        List<FiredShots> list = new LinkedList<>();
        for(int x = 0; x < BOARD_SIZE ; x++){
            for(int y = 0; y < BOARD_SIZE; y++){
                int c = cells.getCell(x,y);
                if(c==EMPTY_FIRED_CELL || c==OCCUPIED_DAMAGED_CELL){
                    list.add(new FiredShots(x,y,c));
                }
            }
        }
        return list;
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
