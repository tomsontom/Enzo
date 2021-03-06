/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.ledbargraph;

import eu.hansolo.enzo.led.Led;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 19.11.12
 * Time: 04:02
 */
public class Demo extends Application {
    private static final Random RND = new Random();
    private LedBargraph         control;
    private long                lastTimerCall;
    private AnimationTimer      timer;

    @Override public void init() {
        control = LedBargraphBuilder.create()
                                    .ledType(Led.LedType.ROUND)
                                    .ledSize(32)
                                    .build();
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 100_000_000l) {
                    control.setValue(RND.nextDouble());
                    lastTimerCall = NOW;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();

        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 400, 400, Color.rgb(0, 0, 0, 0.8));

        stage.setTitle("Led Bargraph demo");
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
