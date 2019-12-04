package fr.ul.cdg.model;

import fr.ul.cdg.factory.Era;
import fr.ul.cdg.factory.Ship;

import java.util.List;

public class Board {
    private List<Ship> shipList;
    private Cells cells;
    public static final int BOARD_SIZE = 10;
    public static final int EMPTY_CELL = 0;

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
    }
    public Board(Era era){
        shipList = era.shipCreation();
        cells = new Cells();
    }
}
