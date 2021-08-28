package persistence;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import models.Game;

import java.io.*;

public class FileManager {

    public void writeJson(Game game) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("heroX", game.getHeroPositionX());
        jsonObject.put("heroY", game.getHeroPositionY());
        jsonObject.put("pacmanX", game.getPacman().getPosX());
        jsonObject.put("pacmanY", game.getPacman().getPosY());
        jsonObject.put("lives",game.getHeroLives());

        JsonArray gems = new JsonArray();
        for (int i = 0; i < game.getGems().length; i++) {
            JsonObject objGem = new JsonObject();
            objGem.put("isVisible", game.getGems()[i].isVisible());
            gems.add(objGem);
        }
        jsonObject.put("gems", gems);

        JsonArray enemies = new JsonArray();
        for (int i = 0; i < game.getEnemies().length; i++) {
            if (i == 0) {
                enemies.add(game.getEnemies()[i].getPosY());
            } else {
                enemies.add(game.getEnemies()[i].getPosX());
            }
        }
        jsonObject.put("enemies", enemies);

        JsonArray energy = new JsonArray();
        for (int i = 0; i < game.getEnergy().length; i++) {
            JsonObject objGem = new JsonObject();
            objGem.put("isVisible", game.getEnergy()[i].isVisible());
            energy.add(objGem);
        }
        jsonObject.put("energy", energy);

        try {
            FileWriter file = new FileWriter("src/resources/data/data.json");
            file.write(jsonObject.toJson());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] readJson() {
        String[] data=new String[28];
        try {
            File file = new File ("src/resources/data/data.json");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            JsonObject jsonObject = (JsonObject) Jsoner.deserialize(br);
            data[0]= jsonObject.get("heroX").toString();
            data[1]= jsonObject.get("heroY").toString();
            data[2]= jsonObject.get("pacmanX").toString();
            data[3]= jsonObject.get("pacmanY").toString();

            JsonArray gems = (JsonArray) jsonObject.get("gems");
            for (int i = 0; i < gems.size(); i++) {
                JsonObject gem = (JsonObject) gems.get(i);
                data[4+i]=gem.get("isVisible").toString();
            }

            JsonArray enemies = (JsonArray) jsonObject.get("enemies");
            for (int i = 0; i < enemies.size(); i++) {

                if (i == 0) {
                    data[10+i]=enemies.get(i).toString();
                } else {
                    data[10+i]=enemies.get(i).toString();
                }
            }

            JsonArray energy = (JsonArray) jsonObject.get("energy");
            for (int i = 0; i < energy.size(); i++) {
                JsonObject e = (JsonObject) energy.get(i);
                data[i+17]=e.get("isVisible").toString();

            }

            data[27]=jsonObject.get("lives").toString();

        } catch (FileNotFoundException | JsonException e) {
            e.printStackTrace();
        }
        return data;
    }
}
