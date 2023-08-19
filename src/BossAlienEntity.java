/******************************************************************************************
 * Name:        BossAlienEntity
 * Author:      Frederick Wang and Kyssen Yu
 * Date:        Mar 7, 2021
 * Purpose:     To create the boss entities
 ******************************************************************************************/

import java.awt.geom.AffineTransform;

public class BossAlienEntity extends Entity {
    private long lastFire = 0; //last fired time

    private final Game game; // the game in which the alien exists


    /* 
     * boss ship constructor
     */
    public BossAlienEntity(Game g, String r, int newX, int newY, String newT) {
        super(r, newX, newY, newT); // calls the constructor in Entity
        game = g;
        // horizontal speed
        dx = -100;
        dy = 0;
    } // constructor

    /*
     * tries to fire the boss ship
     */
    public AlienShotEntity tryFire() {
        if (this.getY() < 1000 && this.getY() > 0 && this.getX() < 1800) {
            //firing interval
            long firingInterval = 2000;
            if ((System.currentTimeMillis() - lastFire) < firingInterval) {
                return new AlienShotEntity(game, "sprites/shot.png", 0, 10000, "");
            } // if

            // otherwise add a shot
            lastFire = System.currentTimeMillis();
            return new AlienShotEntity(game, "sprites/shot.png", this.getX() + 10, this.getY() + 225, "shot");
        } //if
        return new AlienShotEntity(game, "sprites/shot.png", 0, 10000, "");
    } //tryFire 

    /* 
     * moves the boss alien
     */
    public void move(long delta) {
        //makes the boss stop on the right side of screen
        if (this.getX() < 1450) {
            dx = 0;
            dy = 0;
            x = 1450;
            y = 250;
        } //if
        affline = AffineTransform.getTranslateInstance(x, y);
        // proceed with normal move
        super.move(delta);
    } // move




    /* 
     * runs if boss collides with something
     */
    public void collidedWith(Entity other) {
        if (other instanceof ShipEntity) {
            // remove affect entities from the Entity list
            game.removeShip(other);

            // notify the game that the alien is dead
            game.notifyDeath();
        } // if
    } //collidedWith

} // BossAlienEntity