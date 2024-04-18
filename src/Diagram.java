import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Diagram extends ScrollPane {

	private LinkedList XYstart = new LinkedList();
	private LinkedList XYend = new LinkedList();
	private LinkedList ResultP = new LinkedList();
	private LinkedList ResultL = new LinkedList();
	private LinkedList OnLed = new LinkedList();

	public Diagram(Stage stage, Scene scene, LinkedList Result, int[] led, int[] pow) {
		for (int i = 0; i < Result.size(); i++) {
			ResultP.add((Integer) Result.get(i));
			ResultL.add((Integer) Result.get(i));
		}
		int x = 50, y = 50; // start point of led
		int z = 1360, g = 50; // start point of power

		Pane pane = new Pane();

		for (int i = 0; i < led.length; i++) { // search in all entry in array
			boolean x1 = false;

			for (int j = 0; j < ResultL.size(); j++) {
				if (led[i] == (Integer) ResultL.get(j)) {// check if the element is one of the result or no
					x1 = true;
				}
			}
			Led l = new Led(led[i], x1); // if the led number in Result, sent number and true,else sent number and false
			OnLed.add(l);
			l.setLayoutX(x);
			l.setLayoutY(y);
			pane.getChildren().add(l); // set the led in pane
			y += 150; // just move y

			if (x1) { // if the led is in Result, save X and Y point to use it in draw line
				double xl = l.getLayoutX();
				double yl = l.getLayoutY();

				double[] array = { xl + 150, yl + 80, led[i] };
				XYstart.add(array);

			}

		}

		for (int i = 0; i < pow.length; i++) {
			boolean x1 = false;

			for (int j = 0; j < ResultP.size(); j++) {
				if (pow[i] == (Integer) ResultP.get(j)) {
					x1 = true;
				}
			}
			Power p = new Power(pow[i], x1);
			p.setLayoutX(z);
			p.setLayoutY(g);
			pane.getChildren().add(p);
			g += 150;

			if (x1) {
				double xl = p.getLayoutX();
				double yl = p.getLayoutY();
				double[] array = { xl, yl + 30, pow[i] };
				XYend.add(array);
			}

		}

		for (int i = 0; i < XYstart.size(); i++) {
			double[] a = (double[]) XYstart.get(i);
			for (int j = 0; j < XYend.size(); j++) {
				double[] b = (double[]) XYend.get(j);
				if (a[2] == b[2]) { // to compare power number with all led number
					Line line = new Line(); // create line
					// XYstart.set(i, null);
					drawLineSlowly(line, b[0], b[1], a[0], a[1]);
					pane.getChildren().add(line);
				}
			}
		}

		StackPane pane2 = new StackPane();
		pane2.getChildren().add(pane);
		pane2.setStyle("-fx-background-color: #515551;");
		double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
		double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
		pane2.setMinSize(screenWidth, screenHeight);
		setContent(pane2);

		Duration duration = Duration.seconds(2);
		Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
			// Code to execute after 5 seconds
			for (int i = 0; i < OnLed.size(); i++) {
				if (((Led) (OnLed.get(i))).isOnoff()) {

					((Led) (OnLed.get(i))).setOnLed();
				}
			}
		}));

		// Start the timeline
		timeline.play();

	}

	private void drawLineSlowly(Line line, double startX, double startY, double endX, double endY) {// this method to
																									// set animation to
																									// the line, and to
																									// set start and end
		line.setStrokeWidth(7);
		Glow glow = new Glow(0.8); // ÇÚØí áãÚÉ ááÎØ
		line.setEffect(glow);// point
		Color c = Color.web("ffba08");
		line.setStroke(c);
		// Set the starting coordinates of the line
		line.setStartX(startX);
		line.setStartY(startY + 50);
		line.setEndX(startX);
		line.setEndY(startY+50);

		Timeline timeline = new Timeline();

		KeyValue keyValue1 = new KeyValue(line.endXProperty(), endX); // change the endX property each moment
		KeyValue keyValue2 = new KeyValue(line.endYProperty(), endY); // change the endY property each moment
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue1, keyValue2); // do line in 5 seconds

		timeline.getKeyFrames().add(keyFrame);

		timeline.setCycleCount(1);

		timeline.play(); // start the line
	}

}
