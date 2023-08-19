/******************************************************************************************
 * Name:        SpriteStore
 * Author:      Frederick Wang and Kyssen Yu
 * Date:        Mar 7, 2021
 * Purpose:     Manages the sprites in the game. Caches them for future use.
 ******************************************************************************************/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SpriteStore {

    // one instance of this class will exist
    // this instance will be accessed by Game.java
    private static final SpriteStore single = new SpriteStore();
    private final HashMap < String, Sprite > sprites = new HashMap<>(); // key,value pairs that stores
    // the three sprites (alien, ship, shot)

    // returns the single instance of this class
    public static SpriteStore get() {
        return single;
    } // get

    /* getSprite
     * input: a string specifying which sprite image is required
     * output: a sprite instance containing an accelerated image
     *         of the requested image
     * purpose: to return a specific sprite
     */
    public Sprite getSprite(String ref) {

        // if the sprite is already in the HashMap
        // then return it
        // Note:
        if (sprites.get(ref) != null) {
            return sprites.get(ref);
        } // if

        // else, load the image into the HashMap off the
        // hard drive (and hence, into memory)

        BufferedImage sourceImage = null;

        try {
            // get the image location


            sourceImage = ImageIO.read(new File(ref)); // get image
        } catch (IOException e) {
            System.out.println("Failed to load: " + ref);
            System.exit(0); // exit program if file not loaded
        } // catch

        // create an accelerated image (correct size) to store our sprite in
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);

        // draw our source image into the accelerated image
        image.getGraphics().drawImage(sourceImage, 0, 0, null);

        // create a sprite, add it to the cache and return it
        Sprite sprite = new Sprite(image);
        sprites.put(ref, sprite);

        return sprite;
    } // getSprite

} // SpriteStore