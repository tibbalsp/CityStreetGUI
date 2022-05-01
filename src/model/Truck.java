package model;


import java.util.ArrayList;
import java.util.Map;

/**
 * Truck specific actions
 */
public class Truck extends AbstractVehicle {
    private int choice;
    /**
     * Constructor that sends out to AbstractVehicles constructor
     * @param theX - current x position
     * @param theY - current y position
     * @param direction - current direction
     */
    public Truck(int theX, int theY, Direction direction) {
        super(theX,theY,direction,0);
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
            return true;
        }
        if (theTerrain.equals(Terrain.CROSSWALK)) {
            if (theLight.equals(Light.GREEN) || theLight.equals(Light.YELLOW)) {
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

        ArrayList<Direction> turnList = new ArrayList<>();

        if (theNeighbors.get(getStartingDirection()).equals(Terrain.STREET) ||
                theNeighbors.get(getStartingDirection()).equals(Terrain.CROSSWALK) ||
                theNeighbors.get(getStartingDirection()).equals(Terrain.LIGHT)) {
            turnList.add(getStartingDirection());
        }
        if (theNeighbors.get(getDirection().left()).equals(Terrain.STREET) ||
                theNeighbors.get(getDirection().left()).equals(Terrain.CROSSWALK) ||
                theNeighbors.get(getDirection().left()).equals(Terrain.LIGHT)) {

            turnList.add(getDirection().left());
        }
        if (theNeighbors.get(getDirection().right()).equals(Terrain.STREET) ||
                theNeighbors.get(getDirection().right()).equals(Terrain.CROSSWALK) ||
                theNeighbors.get(getDirection().right()).equals(Terrain.LIGHT)) {

            turnList.add(getDirection().right());
        }
        if (turnList.size() == 0) {
            setStartingDirection(getDirection().reverse());
        } else {
            this.choice = getRandom().nextInt(turnList.size());
        }
        if (turnList.contains(getStartingDirection()) && turnList.size() == 1) {
            //Leave Direction alone
        } else if (turnList.contains(getDirection().right()) || turnList.contains(getDirection().left())) {
            setStartingDirection(turnList.get(choice));

        } else {
        }
        return getStartingDirection();
    }
    /**
     * Method to access this vehicles specific death time
     * @return Death time
     */
    @Override
    public int getDeathTime() {
        return 0;
    }
}
