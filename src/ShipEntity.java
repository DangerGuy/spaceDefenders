/******************************************************************************************
 * Name:        ShipEntity
 * Author:      Frederick Wang and Kyssen Yu
 * Date:        Mar 7, 2021
 * Purpose:     constructs player ship entity
 ******************************************************************************************/
import java.awt.geom.AffineTransform;

public class ShipEntity extends Entity {
    private Game game; // the game in which the ship exists

    /*
     * construct player ship
     */
    public ShipEntity(Game g, String r, int newX, int newY, String newT) {
        super(r, newX, newY, newT); // calls the constructor in Entity
        game = g;
    } // constructor

    /* move the player ship 
     */
    public void move(long delta) {
        // stop at left side of screen
        if ((dx < 0) && (x < 10)) {
            return;
        } // if
        // stop at right side of screen
        if ((dx > 0) && (x > 1850)) {
            return;
        } // if

        // stop at top side of screen
        if ((dy < 0) && (y < 10)) {
            return;
        } //if

        // stop at bottom side of screen
        if ((dy > 0) && (y > 850)) {
            return;
        } //if

        //point ship towards mouse cursor
        double diffX = (x + 50 - game.getMouseCords()[0]);
        double diffY = (y + 50 - game.getMouseCords()[1] + 30);
        double angle = Math.atan2(diffY, diffX);
        affline = AffineTransform.getTranslateInstance(x, y);
        affline.rotate(angle, 50, 50);
        super.move(delta); // calls the move method in Entity
    } // move


    /* 
     * runs if ship collides with entity
     */
    public void collidedWith(Entity other) {

    } // collidedWith    

} // ShipEntity class