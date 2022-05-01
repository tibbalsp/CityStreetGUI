package test.java.tests;

import model.ATV;
import model.Direction;
import model.Light;
import model.Terrain;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
/*
 * TCSS 305 - Road Rage
 */

//Author: Patrick Tibbals

class ATVTest {
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /** Test method for Human constructor. */
    @org.junit.jupiter.api.Test
    public void testConstructor() {

        final ATV atv = new ATV(1, 11, Direction.NORTH);

        assertEquals(1, atv.getX());
        assertEquals(11, atv.getY());
        assertEquals(Direction.NORTH, atv.getDirection());
        assertEquals(25, atv.getDeathTime());
        assertTrue(atv.isAlive());

    }

    /** Test method for Human setters. */
    @Test
    public void testSetters() {
       final ATV h = new ATV(1, 11, Direction.NORTH);

        h.setX(12);
        assertEquals( 12, h.getX());
        h.setY(13);
        assertEquals(13, h.getY());
        h.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, h.getDirection());


    }

    /**
     * Test method for {@link ATV#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {

        // Humans can move to GRASS or to CROSSWALKS
        // so we need to test both of those conditions

        // Humans should NOT choose to move to other terrain types
        // so we need to test that Humans never move to other terrain types

        // Humans should only reverse direction if no other option is available
        // so we need to be sure to test that requirement also


        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.GRASS);
        validTerrain.add(Terrain.TRAIL);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);

        final ATV atv = new ATV(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain != Terrain.WALL) {
                    // humans can pass GRASS under any light condition
                    assertTrue(atv.canPass(destinationTerrain, currentLightCondition));
                } else {
                    assertFalse(atv.canPass(Terrain.WALL,currentLightCondition));
                }
            }
        }


    }

    /**
     * Test method for {@link ATV#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByTwoWall() {

        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();

        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.WALL);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final ATV atv = new ATV(1, 11, Direction.NORTH);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = atv.chooseDirection(neighbors);

            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }

        assertTrue(seenNorth && seenEast);
        assertFalse(seenWest && seenSouth );


    }

}