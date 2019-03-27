/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewerproject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Label;
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
    Label imgLabel;

    public Slideshow(ImageView iv, List<Image> i, int currentIndex, Label imgLabel) {
        this.iv = iv;
        this.i = i;
        this.currentIndex = currentIndex;
        this.imgLabel = imgLabel;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!i.isEmpty()) {
                    Platform.runLater(()-> {
                        iv.setImage(i.get(currentIndex));
                        imgLabel.setText(i.get(currentIndex).impl_getUrl());
                    });
//                    iv.setImage(i.get(currentIndex));
                    TimeUnit.SECONDS.sleep(1);
                    if(currentIndex == i.size()-1){       currentIndex = 0;  }   else currentIndex++;
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Erorr");
        }
    }

}
