/**
 * By: Niranjan Sahi
 * Class: AP CS A
 *
 *
 * Revision history:
 * Date - Version - Comments
 * 03/05/2019 - 1.0 - Beginning the main framework of the Texture class.
 *
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Texture {

    public static Texture wood = new Texture("wood.png", 64);
    public static Texture brick = new Texture("redbrick.png", 64);
    public static Texture bluestone = new Texture("bluestone.png", 64);
    public static Texture stone = new Texture("greystone.png", 64);

    public ArrayList<Texture> textures;


    public int[] pixels;
    private String loc;
    public final int SIZE;


    public Texture(String location, int size) {
        loc = location;
        SIZE = size;
        pixels = new int[SIZE * SIZE];

        textures = new ArrayList<Texture>();
        textures.add(Texture.wood);
        textures.add(Texture.brick);
        textures.add(Texture.bluestone);
        textures.add(Texture.stone);


        load();


    }

    private void load() {
        try {

            URL resource = getClass().getResource(loc);

            BufferedImage image = ImageIO.read(resource);

           /* File file = new File("Hunt_the_Wumpus/res/wood.png");
            String path = file.getAbsolutePath();
            System.out.print(path); */

            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            /*File file = new File("Hunt_the_Wumpus/res/wood.png");
            String path = file.getAbsolutePath();
            System.out.print(path);*/

            e.printStackTrace();
        }
    }





}

