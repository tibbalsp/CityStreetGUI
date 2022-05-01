package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Car specific actions
 */
public class Car extends AbstractVehicle {
    /**
     * Constructor that sends out to AbstractVehicles constructor
     * @param theX - current x position
     * @param theY - current y position
     * @param direction - current direction
     */
    public Car(int theX, int theY, Direction direction) {
        super(theX,theY,direction,15);
    }
    /**
     * Designate the passable Terrains
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return true or false for can or cannot pass
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (theTerrain.equals(Terrain.STREET)) {
            return true;
        }
        if (theTerrain.equals(Terrain.LIGHT)) {
            if (theLight.equals(Light.GREEN) || theLight.equals(Light.YELLOW)) {
                return true;
            }
        }
        if (theTerrain.equals(Terrain.CROSSWALK)) {
            if (theLight.equals(Light.GREEN)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Choose the Direction based upon required rules
     * @param theNeighbors The map of neighboring terrain.
     * @return Direction of choice
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        if (getDirection() == (null)) {
            setStartingDirection(Direction.random());
        }
        ArrayList<Direction> turnList = new ArrayList<>();

        if (theNeighbors.get(getStartingDirection()).equals(Terrain.STREET) ||
                theNeighbors.get(getStartingDirection()).equals(Terrain.CROSSWALK) ||
                theNeighbors.get(getStartingDirection()).equals(Terrain.LIGHT)) {
            turnList.add(getStartingDirection());
        }
        if (theNeighbors.get(getDirection().left()).equals(Terrain.STREET) ||
                theNeighbors.get(getDirection().left()).equals(Terrain.CROSSWALK) ||
                theNeighbors.get(getDirection().left()).equals(Terrain.LIGHT)){
            turnList.add(getDirection().left());
        }
        if (theNeighbors.get(getDirection().right()).equals(Terrain.STREET) ||
                theNeighbors.get(getDirection().right()).equals(Terrain.CROSSWALK) ||
                theNeighbors.get(getDirection().right()).equals(Terrain.LIGHT)){
            turnList.add(getDirection().right());
        }
        if(turnList.size() == 0){
            setStartingDirection(getDirection().reverse());
        }
        if (turnList.contains(getStartingDirection())){
            //Do Nothing
        }else if (turnList.contains(getDirection().left())) {
            setStartingDirection(getDirection().left());
        }else if (turnList.contains(getDirection().right())) {
            setStartingDirection(getDirection().right());
        }else {
        }
        return getStartingDirection();
    }
    /**
     * Method to access this vehicles specific death time
     * @return Death time
     */
    @Override
    public int getDeathTime() {
        return 15;
    }
}