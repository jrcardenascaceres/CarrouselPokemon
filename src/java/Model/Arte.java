/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jcardenas
 */
public class Arte {

    private Arte1 dream_world;
    private Arte2 official_artwork;

    public Arte1 getDream_world() {
        return dream_world;
    }

    public void setDream_world(Arte1 dream_world) {
        this.dream_world = dream_world;
    }

    public Arte2 getOfficial_artwork() {
        return official_artwork;
    }

    public void setOfficial_artwork(Arte2 official_artwork) {
        this.official_artwork = official_artwork;
    }
}
