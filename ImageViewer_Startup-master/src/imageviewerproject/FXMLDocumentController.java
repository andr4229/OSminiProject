package imageviewerproject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    private final List<Image> images = new ArrayList<>();
    private int currentImageIndex = 0;

    @FXML
    Parent root;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;

    @FXML
    private ImageView imageView;

    private Runnable slideshow;
    private ExecutorService executor;

    private void handleBtnLoadAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (!files.isEmpty()) {
            files.forEach((File f)
                    -> {
                images.add(new Image(f.toURI().toString()));
            });
            displayImage();
        }
    }

    private void handleBtnPreviousAction(ActionEvent event) {
        if (!images.isEmpty()) {
            currentImageIndex
                    = (currentImageIndex - 1 + images.size()) % images.size();
            displayImage();
        }
    }

    private void handleBtnNextAction(ActionEvent event) {
        if (!images.isEmpty()) {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            displayImage();
        }
    }

    private void displayImage() {
        if (!images.isEmpty()) {
            Platform.runLater(()-> {imageView.setImage(images.get(currentImageIndex));});
//            imageView.setImage(images.get(currentImageIndex));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLoad.setOnAction((ActionEvent event)
                -> {
            handleBtnLoadAction(event);
        });

        btnPrevious.setOnAction((ActionEvent event)
                -> {
            handleBtnPreviousAction(event);
        });

        btnNext.setOnAction((ActionEvent event)
                -> {
            handleBtnNextAction(event);
        });
        slideshow = new Slideshow(imageView, images, currentImageIndex);
        executor = Executors.newSingleThreadExecutor();
    }

    @FXML
    private void handleBtnStart(ActionEvent event) {
        executor = Executors.newSingleThreadExecutor();
        executor.submit(slideshow);

    }

//    private void handleBtnStartANDr(ActionEvent event)
//    {
//        Runnable slideshow = new StartSlideshow(imageView, images);
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(slideshow);
//    }
    @FXML
    private void handleBtnStop(ActionEvent event) {

        executor.shutdownNow();
        //executor = Executors.newSingleThreadExecutor();
    }

}
