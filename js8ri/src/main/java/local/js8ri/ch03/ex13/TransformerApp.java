/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/java8-training-course
 */

package local.js8ri.ch03.ex13;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.annotation.Nonnull;
import java.util.function.UnaryOperator;

/**
 *
 * @author mikan
 */
public class TransformerApp extends Application {

    private static final String IMAGE_URL = "http://www001.upp.so-net.ne.jp/yshibata/myhomepage/images/js8ri.png";

    public static void main(String[] args) {
        TransformerApp.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image(IMAGE_URL);
        Image blur = LatentImage.from(image).transform(TransformerGenerator.createBlur(20)).toImage();
        Image edge = LatentImage.from(image).transform(TransformerGenerator.createEdgeDetection()).toImage();
        primaryStage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(blur), new ImageView(edge))));
        primaryStage.show();
    }

    @Nonnull
    public static ColorTransformer createColorTransformer(@Nonnull UnaryOperator<Color> f) {
        return (x, y, c) -> f.apply(c.getImage().getPixelReader().getColor(x, y));
    }

    @FunctionalInterface
    public interface ColorTransformer {

        @Nonnull
        Color apply(int x, int y, @Nonnull LatentImage image);
    }
}
