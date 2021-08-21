package persistence;

import models.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public void write(Game game) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("heroX", game.getHeroPositionX());
        jsonObject.put("heroY", game.getHeroPositionY());
        jsonObject.put("pacmanX", game.getPacman().getPosX());
        jsonObject.put("pacmanY", game.getPacman().getPosY());

        JSONArray gems = new JSONArray();
        for (int i = 0; i < game.getGems().length; i++) {
            JSONObject objGem = new JSONObject();
            objGem.put("x", game.getGems()[i].getPosX());
            objGem.put("y", game.getGems()[i].getPosY());
            objGem.put("isVisible", game.getGems()[i].isVisible());
            gems.add(objGem);
        }
        jsonObject.put("gems", gems);

        JSONArray enemies = new JSONArray();
        for (int i = 0; i < game.getEnemies().length; i++) {
            if (i == 0) {
                enemies.add(game.getEnemies()[i].getPosY());
            } else {
                enemies.add(game.getEnemies()[i].getPosX());
            }
        }
        jsonObject.put("enemies", enemies);

        try {
            FileWriter file = new FileWriter("src/resources/data/data.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {

    }
}
