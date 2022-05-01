package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * ATV specific actions
 */
public class ATV extends AbstractVehicle{
    /**
     * Constructor that sends out to AbstractVehicles constructor
     * @param theX - current x position
     * @param theY - current y position
     * @param direction - current direction
     */
        public ATV(int theX, int theY, Direction direction) {
            super(theX,theY,direction,25);
        }

    /**
     * Designate the passable Terrains
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return true or false for can or cannot pass
     */
        @Override
        public boolean canPass(Terrain theTerrain, Light theLight) {
            return !theTerrain.equals(Terrain.WALL);
        }

    /**
     * Choose the Direction based upon required rules
     * @param theNeighbors The map of neighboring terrain.
     * @return Direction of choice
     */
        @Override
        public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {

            ArrayList<Direction> turnList = new ArrayList<>();

            if (!theNeighbors.get(getStartingDirection()).equals(Terrain.WALL)) {
                turnList.add(getStartingDirection());
            }
            if (!theNeighbors.get(getDirection().left()).equals(Terrain.WALL)){
                turnList.add(getDirection().left());
            }
            if (!theNeighbors.get(getDirection().right()).equals(Terrain.WALL)){
                turnList.add(getDirection().right());
            }if(turnList.size() == 0){
                getDirection().reverse();
            }else {
                int count = getRandom().nextInt(turnList.size());
                setStartingDirection(turnList.get(count));
            }
            return getStartingDirection();
        }

    /**
     * Method to access this vehicles specific death time
     * @return Death time
     */
        @Override
        public int getDeathTime() {
            return 25;
        }
}