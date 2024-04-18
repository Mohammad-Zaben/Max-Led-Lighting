
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {
	private BorderPane pane;
	private Label l1;
	private Button ManualTestbt, BrwFile, Randombt;

	@Override
	public void start(Stage stage) throws Exception {
		pane = new BorderPane();
		pane.setStyle("-fx-background-image: url('file:///C:/java-2/Algorithm_project1/network-3357642_1280.jpg');" // set
																														// background
				+ "-fx-background-size:cover;-fx-background-position: center center;");

		Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 60);
		l1 = new Label("Max LED Lighting ");
		l1.setAlignment(Pos.CENTER);
		l1.setFont(font);
		l1.setPadding(new Insets(60, 60, 60, 60));
		l1.setTextFill(Color.WHEAT);
		BorderPane.setAlignment(l1, Pos.CENTER);

		ManualTestbt = new Button("Manual Test");

		ManualTestbt.setStyle(
				"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 34pt; -fx-padding: 25px 50px;");

		ManualTestbt.setOnMouseEntered(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), ManualTestbt); // bt1 effects
			scaleTransition.setToX(1.2);
			scaleTransition.setToY(1.2);
			scaleTransition.play();
		});
		ManualTestbt.setOnMouseExited(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), ManualTestbt);// bt2 effects
			scaleTransition.setToX(1.0);
			scaleTransition.setToY(1.0);
			scaleTransition.play();
		});

		BrwFile = new Button("Select File");

		BrwFile.setStyle(
				"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 34pt; -fx-padding: 25px 60px;");

		BrwFile.setOnMouseEntered(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), BrwFile); // bt1 effects
			scaleTransition.setToX(1.2);
			scaleTransition.setToY(1.2);
			scaleTransition.play();
		});
		BrwFile.setOnMouseExited(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), BrwFile);
			scaleTransition.setToX(1.0);
			scaleTransition.setToY(1.0);
			scaleTransition.play();
		});

		Randombt = new Button("Randomly Test");

		Randombt.setStyle(
				"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 34pt; -fx-padding: 25px 50px;");

		Randombt.setOnMouseEntered(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), Randombt); // bt1 effects
			scaleTransition.setToX(1.2);
			scaleTransition.setToY(1.2);
			scaleTransition.play();
		});
		Randombt.setOnMouseExited(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), Randombt);
			scaleTransition.setToX(1.0);
			scaleTransition.setToY(1.0);
			scaleTransition.play();
		});

		HBox buttons = new HBox();
		buttons.setPadding(new Insets(60, 60, 60, 60));
		buttons.getChildren().addAll(ManualTestbt, BrwFile, Randombt); // add button to HBox pane
		buttons.setSpacing(100);
		buttons.setAlignment(Pos.CENTER);
		stage.setMaximized(true);

		pane.setTop(l1);
		pane.setBottom(buttons);

		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);

		ManualTestbt.setOnAction(e -> showManual(stage, scene));
		BrwFile.setOnAction(e -> showFileTest(stage, scene));
		Randombt.setOnAction(e -> showRandomTest(stage, scene));
		stage.show();

	}

	// this method to show Manual Test Interface
	private void showManual(Stage stage, Scene scene) {
		// Create a new scene
		stage.setMaximized(true);
		ManualTest mt = new ManualTest(stage, scene); // new pane from ManualTest
		Scene scene1 = new Scene(mt, 600, 600);

		stage.close();
		stage.setScene(scene1);
		stage.show();
	}

	// this method to show File Test Interface
	private void showFileTest(Stage stage, Scene scene) {
		// Create a new scene
		stage.setMaximized(true);
		FileCase mt = new FileCase(stage, scene);// new pane from FileCase
		Scene scene1 = new Scene(mt, 600, 600);

		stage.close();
		stage.setScene(scene1);
		stage.show();
	}

	private void showRandomTest(Stage stage, Scene scene) {
		// Create a new scene
		stage.setMaximized(true);
		Randomly mt = new Randomly(stage, scene);// new pane from FileCase
		Scene scene1 = new Scene(mt, 600, 600);

		stage.close();
		stage.setScene(scene1);
		stage.show();
	}

	public static void main(String[] args) {

		// int[] pow= {1, 2, 3, 4 ,5 ,6};
		// int[] led= {2, 6 ,3, 5, 4 ,1};
		// FinallyData fd=new FinallyData(led,pow);
		launch();
	}

}
