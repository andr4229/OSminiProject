/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewerproject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author andreas
 */
public class StartSlideshow implements Runnable
{
    private final long DELAY = 1;
    private int currentIndex = 0;
    private ImageView imageView;
    private List<Image> images;
    
    public StartSlideshow(ImageView imageView, List<Image> images){
        this.imageView = imageView;
        this.images = images;
    }
    @Override
    public void run()
    {
        if (!images.isEmpty())
        {
            while (true){
                imageView.setImage(images.get(currentIndex));
                currentIndex = (currentIndex + 1)%images.size();
                try
                {
                    TimeUnit.SECONDS.sleep(DELAY);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(StartSlideshow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
