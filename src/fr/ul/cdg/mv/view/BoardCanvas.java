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

    private Ship selected;

    public void drawBoard(Board bd, boolean playerBoard){
        GraphicsContext gc = this.getGraphicsContext2D();
        double w = this.getWidth();
        double h = this.getHeight();
        double ws = w/Board.BOARD_SIZE;
        double hs = h/Board.BOARD_SIZE;
        gc.clearRect(0,0,w,h);
        // Draw board
        gc.setStroke(Color.gray(0.25));
        for(int i = 0 ; i < Board.BOARD_SIZE ; i++){
            gc.strokeLine(i*ws,0,i*ws,h);
            gc.strokeLine(0,i*hs,w,i*hs);
        }
        // Draw boats if player board or if dead
        for(Ship s : bd.getShipList()){
            if(s.getPosition()==null || (!playerBoard && s.getHp()>0)) continue;
            if(s.equals(selected)){
                drawBoat(s,new Color(0.3,0.3,1,1));
            }
            else if(s.isDead()){
               drawBoat(s,playerBoard?new Color(1,0,0.5,1):new Color(0.5,0,0,1));
            }
            else{
                drawBoat(s,playerBoard?new Color(0,0,1,1):new Color(1,0,0,1));
            }
        }
        // Draw shots
        for(Board.FiredShots f : bd.getFiredShots()){
            if(f.state==Board.EMPTY_FIRED_CELL){
                gc.drawImage(ImagesLoader.getInstance().getSprite(ImagesLoader.Sprites.CROSS),f.x*ws,f.y*ws,ws,hs);
            }else{
                gc.drawImage(ImagesLoader.getInstance().getSprite(ImagesLoader.Sprites.NOUGHT),f.x*ws,f.y*ws,ws,hs);
            }
        }
    }

    public void drawBoat(Ship s, Color color){
        drawBoat(s,s.getPosition(),s.getOrientation(),color);
    }

    public void drawBoat(Ship s, Vector2 position, int orientation, Color color){
        GraphicsContext gc = this.getGraphicsContext2D();
        double w = this.getWidth();
        double h = this.getHeight();
        double ws = w/Board.BOARD_SIZE;
        double hs = h/Board.BOARD_SIZE;

        Vector2 pos = new Vector2(position);
        pos.mult((int)ws,(int)hs);
        pos.add((int)ws/5,(int)hs/5);
        Vector2 size = new Vector2(orientation==Ship.HORIZONTAL?s.getNbCells():1,orientation==Ship.VERTICAL?s.getNbCells():1);
        size.mult((int)ws,(int)hs);
        size.add(-2*(int)ws/5,-2*(int)hs/5);
        gc.setFill(color);
        gc.fillRect(pos.getX(),pos.getY(),size.getX(),size.getY());
        gc.setStroke(Color.gray(0));
        gc.strokeRect(pos.getX(),pos.getY(),size.getX(),size.getY());
    }

    public void setSelected(Ship selected) {
        this.selected = selected;
    }

    public Vector2 viewToBoard(Vector2 position){
        Vector2 pos=new Vector2((int)position.getX(),(int)position.getY());
        pos.mult(Board.BOARD_SIZE/(float)getWidth());
        return pos;
    }
}
