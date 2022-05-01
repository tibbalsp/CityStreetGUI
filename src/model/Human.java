package model;

import java.util.ArrayList;
import java.util.Map;
/**
 * Human specific actions
 */
public class Human extends AbstractVehicle {

    private ArrayList<Direction> turnList = new ArrayList<>();
    /**
     * Constructor that sends out to AbstractVehicles constructor
     * @param theX - current x position
     * @param theY - current y position
     * @param direction - current direction
     */
    public Human(int theX, int theY, Direction direction) {
        super(theX, theY, direction, 45);
    }
    /**
     * Designate the passable Terrains
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return true or false for can or cannot pass
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (theTerrain.equals(Terrain.GRASS)) {
            return true;
        }
        if (theTerrain.equals(Terrain.CROSSWALK)) {
            if (theLight.equals(Light.RED) || theLight.equals(Light.YELLOW)) {
                return true;
            }
        }
        if (theTerrain.equals(Terrain.TRAIL)) {
            return true;
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

        if (theNeighbors.get(getStartingDirection()).equals(Terrain.STREET) == false) {
            this.turnList.add(getStartingDirection());
        }
        if (theNeighbors.get(getDirection().left()).equals(Terrain.STREET) == false) {
            this.turnList.add(getDirection().left());
        }
        if (theNeighbors.get(getDirection().right()).equals(Terrain.STREET) == false) {
            this.turnList.add(getDirection().right());
        }

        if (theNeighbors.get(getDirection().left()).equals(Terrain.CROSSWALK)) {
            setStartingDirection(getDirection().left());
        } else if (theNeighbors.get(getDirection().right()).equals(Terrain.CROSSWALK)) {
            setStartingDirection(getDirection().right());
        } else if (theNeighbors.get(getStartingDirection()).equals(Terrain.CROSSWALK)) {
        } else if (this.turnList.isEmpty()) {
            setStartingDirection(getStartingDirection().reverse());
        } else {
            int count = getRandom().nextInt(this.turnList.size());
            setStartingDirection(this.turnList.get(count));
        }
        return getStartingDirection();
    }
    /**
     * Method to access this vehicles specific death time
     * @return Death time
     */
    @Override
    public int getDeathTime() {
        return 45;
    }
}
