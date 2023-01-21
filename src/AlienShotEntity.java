/******************************************************************************************
 * Name:        AlienShotEntity
 * Author:      Frederick Wang and Kyssen Yu
 * Date:        Mar 7, 2021
 * Purpose:     To create the alien shot entities
 ******************************************************************************************/
import java.awt.geom.AffineTransform;

public class AlienShotEntity extends Entity {

    private double moveSpeed = -800; // shot move speed
    private boolean used = false; // true if shot hits something
    private Game game; // the game in which the ship exists

    /*
     * constructs the alien shot
     */
    public AlienShotEntity(Game g, String r, int newX, int newY, String newT) {
        super(r, newX, newY, newT); // calls the constructor in Entity
        game = g;
        //sets the shot to move towards the player ship
        double diffX = (game.getShipCords()[0] + 50 - x);
        double diffY = (game.getShipCords()[1] + 50 - y);
        double hypo = Math.sqrt(diffY * diffY + diffX * diffX);
        double ratio = hypo / moveSpeed;
        dx = -diffX / ratio;
        dy = -diffY / ratio;
    } // constructor

    /* 
     * allows the shot to move
     */
    public void move(long delta) {
        super.move(delta); // calls the move method in Entity
        affline = AffineTransform.getTranslateInstance(x, y);
        // if shot moves off top of screen, remove it from entity list
        if (y < -100) {
            game.removeEntity(this);
        } // if
    } // move


    /* 
     * runs when it collides with player ship
     */
    public void collidedWith(Entity other) {
        // prevents double kills
        if (used) {
            return;
        } // if

        // if it has hit an alien, kill it!
        if (other instanceof ShipEntity) {
            // remove affect entities from the Entity list
            game.removeEntity(this);
            game.removeShip(other);

            // notify the game that the alien is dead
            game.notifyDeath();
            used = true;
        } // if

    } // collidedWith

} // AlienShotEntity