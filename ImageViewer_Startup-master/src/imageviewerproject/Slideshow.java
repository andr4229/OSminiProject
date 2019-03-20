/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewerproject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Wezzy Laptop
 */
public class Slideshow implements Runnable {

    ImageView iv;
    List<Image> i;
    int currentIndex;

    public Slideshow(ImageView iv, List<Image> i, int currentIndex) {
        this.iv = iv;
        this.i = i;
        this.currentIndex = currentIndex;

    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!i.isEmpty()) {
                    iv.setImage(i.get(currentIndex));
                    TimeUnit.SECONDS.sleep(1);
                    if(currentIndex == i.size()-1){       currentIndex = 0;  }   else currentIndex++;
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Erorr");
        }
    }

}
