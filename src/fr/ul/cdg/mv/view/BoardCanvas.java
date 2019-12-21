package fr.ul.cdg.mv.view;

import fr.ul.cdg.factory.Ship;
import fr.ul.cdg.model.Board;
import fr.ul.cdg.util.ImagesLoader;
import fr.ul.cdg.util.Vector2;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class BoardCanvas extends Canvas {

    public void drawBoard(Board bd, boolean playerBoard){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setStroke(new Color(0,0,0,1));
        double w = this.getWidth();
        double h = this.getHeight();
        double ws = w/Board.BOARD_SIZE;
        double hs = h/Board.BOARD_SIZE;
        gc.clearRect(0,0,w,h);
        //Draw board
        gc.setStroke(Color.gray(0.25));
        for(int i = 0 ; i < Board.BOARD_SIZE ; i++){
            gc.strokeLine(i*ws,0,i*ws,h);
            gc.strokeLine(0,i*hs,w,i*hs);
        }
        //Draw boats if player board or if dead
        for(Ship s : bd.getShipList()){
            if(s.getPosition()==null || (!playerBoard && s.getHp()>0)) continue;
            Vector2 pos = new Vector2(s.getPosition());
            pos.mult((int)ws,(int)hs);
            pos.add((int)ws/5,(int)hs/5);
            Vector2 size = new Vector2(s.getOrientation()==Ship.HORIZONTAL?s.getNbCells():1,s.getOrientation()==Ship.VERTICAL?s.getNbCells():1);
            size.mult((int)ws,(int)hs);
            size.add(-2*(int)ws/5,-2*(int)hs/5);
            if(playerBoard) gc.setFill(new Color(0,0,1,1)); else gc.setFill(new Color(1,0,0,1));
            gc.fillRect(pos.getX(),pos.getY(),size.getX(),size.getY());
        }
        //Draw shots
        for(Board.FiredShots f : bd.getFiredShots()){
            if(f.state==Board.EMPTY_FIRED_CELL){
                gc.drawImage(ImagesLoader.getInstance().getSprite(ImagesLoader.Sprites.CROSS),f.x*ws,f.y*ws,ws,hs);
            }else{
                gc.drawImage(ImagesLoader.getInstance().getSprite(ImagesLoader.Sprites.NOUGHT),f.x*ws,f.y*ws,ws,hs);
            }
        }


    }

    public Vector2 viewToBoard(Vector2 position){
        Vector2 pos=new Vector2((int)position.getX(),(int)position.getY());
        pos.mult(Board.BOARD_SIZE/(float)getWidth());
        return pos;
    }
}
