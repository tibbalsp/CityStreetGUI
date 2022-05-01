//Patrick Tibbals - Program #4
//Kivanic Dincer
package model;


import java.util.Locale;
import java.util.Random;

/**
 * Abstract class responsible for managing all the vehicles
 */
public abstract class AbstractVehicle implements Vehicle {
    final Random random = new Random();

    //Holding space for all instantiable variables
    private boolean alive = true;
    private String vehicleImg = getClass().getSimpleName().toLowerCase(Locale.ROOT) + ".gif";
    private int xPosition;
    private int yPosition;
    private int startingX;
    private int startingY;
    private int pokeCount;
    private int deathTime;
    private Direction startingDirection;

    /**
     * Getter for Random object
     * @return Random object
     */
    Random getRandom() {
        return random;
    }

    /**
     * Getter for starting direction
     * @return startingDirection
     */
    Direction getStartingDirection() {
        return this.startingDirection;
    }

    /**
     * Setter for starting direction
     * @param startingDirection incoming new direction
     */
    public void setStartingDirection(Direction startingDirection) {
        this.startingDirection = startingDirection;
    }


    /**
     * Constructor for all the child classes to call too
     * @param theX current X position
     * @param theY current Y position
     * @param direction current direction
     * @param deathCount this vehicle's death count
     */
    public AbstractVehicle(int theX, int theY, Direction direction,int deathCount) {
        this.startingX = theX;
        this.startingY = theY;
        this.deathTime = deathCount;
        setX(theX);
        setY(theY);
        setDirection(direction);
    }

    /**
     * Getter for X position
     * @return current X position
     */
    public int getX() {
        return this.xPosition;
    }
    /**
     * Getter for Y position
     * @return current Y position
     */
    public int getY() {
        return this.yPosition;
    }
    /**
     * Setter for X position
     * @param theX the current X position
     */
    public void setX(int theX) {
        this.xPosition = theX;
    }
    /**
     * Setter for Y position
     * @param theY the current Y position
     */
    public void setY(int theY) {
        this.yPosition = theY;
    }
    /**
     * Setter for the direction
     * @param theDir the current direction
     */
    public void setDirection(Direction theDir) {
        this.startingDirection = theDir;
    }
    /**
     * Getter for image file name
     * @return String image name
     */
    public String getImageFileName() {
        return this.vehicleImg;
    }

    /**
     * Gets the current direction vehicle is facing
     * @return Direction enum
     */
    public Direction getDirection() {
        if (this.startingDirection == Direction.EAST) {
            return Direction.EAST;
        } else if (this.startingDirection == Direction.NORTH) {
            return Direction.NORTH;
        } else if (this.startingDirection == Direction.SOUTH) {
            return Direction.SOUTH;
        } else if (this.startingDirection == Direction.WEST) {
            return Direction.WEST;
        } else {
            return null;
        }

    }

    /**
     * Detirmines the proper image for a vehicle if it is alive or dead
     * @return true or false if the vehicle is alive or dead
     */
    public boolean isAlive() {
        if(this.alive == true){
            this.vehicleImg = getClass().getSimpleName().toLowerCase(Locale.ROOT) + ".gif";
        }else{
            this.vehicleImg = getClass().getSimpleName().toLowerCase(Locale.ROOT) + "_dead.gif";
        }
        return this.alive;
    }

    /**
     * Detirmine if a vehicle will die or survive in a collision
     * @param theOther The other vehicle object.
     */
    public void collide(Vehicle theOther) {
        if(theOther.getDeathTime() < getDeathTime()){
               this.alive = false;
               this.vehicleImg = getClass().getSimpleName().toLowerCase(Locale.ROOT) + "_dead.gif";
        }
    }

    /**
     * Responsible for reviving the vehicle object when death time has run out
     */
    public void poke() {
        if (!this.alive && this.pokeCount <= this.deathTime) {
            this.pokeCount++;
        } else {
            this.pokeCount = 0;
            this.alive = true;
            setStartingDirection(Direction.random());
        }
    }

    /**
     * Resets the frame back to original position before start button was pressed
     */
    public void reset() {
        this.alive = true;
        this.vehicleImg = getClass().getSimpleName().toLowerCase(Locale.ROOT) + ".gif";
        setY(this.startingY);
        setX(this.startingX);
        setStartingDirection(Direction.random());
    }

    /**
     * Get the current vehicles' death time
     * @return the current vehicles' death time
     */
    public int getDeathTime() {
        return this.deathTime;
    }

    /**
     * Place the name and coordinate positions on the panel next to them
     * @return String of the current vehicles' name and coordinates
     */
    public String toString(){
        return getClass().getSimpleName() +"("+getX()+","+getY()+")";
    }
}

