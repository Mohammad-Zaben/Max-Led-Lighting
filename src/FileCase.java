import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FileCase extends BorderPane {
	private Label l;
	private TextField Maxnum, led;
	private TextArea CostTable;
	private Button Backbt, Calcbt, ShowDi, LoadFile;
	private ArrayList<Integer> Led = new ArrayList<>();
	private LinkedList Result;
	private int[] LED, POW;
	private boolean isFullData = false;
	Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 60);

	public FileCase(Stage stage, Scene backScene) {
		super.setStyle("-fx-background-image: url('file:///C:/java-2/Algorithm_project1/test-lamp.jpg');" // set
																											// background
				+ "-fx-background-size:cover;-fx-background-position: center center;");
		l = new Label("File Test");

		l.setFont(font);
		l.setTextFill(Color.WHEAT);
		super.setAlignment(l, Pos.CENTER);
		super.setTop(l);

		VBox pane = new VBox();
		pane.setSpacing(15);
		pane.setAlignment(Pos.CENTER);

		Maxnum = new TextField();
		Maxnum.setStyle("-fx-background-color: #808080;" + // Gray background color
				"-fx-background-radius: 5;" + "-fx-padding: 5px;" + "-fx-font-size: 18px;" + "-fx-text-fill: white;"
				+ "-fx-prompt-text-fill: #A9A9A9;" + "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 10;"
				+ "-fx-border-width: 1px;");
		Maxnum.setMaxWidth(210);
		Maxnum.setPrefHeight(50);
		Maxnum.setPromptText("Max number of Led");
		Maxnum.setEditable(false);

		led = new TextField();
		led.setStyle("-fx-background-color: #808080;" + // Gray background color
				"-fx-background-radius: 5;" + "-fx-padding: 5px;" + "-fx-font-size: 18px;" + "-fx-text-fill: white;"
				+ "-fx-prompt-text-fill: #A9A9A9;" + "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 10;"
				+ "-fx-border-width: 1px;");
		led.setMaxWidth(210);
		led.setPrefHeight(50);
		led.setPromptText("number of Led");
		led.setEditable(false);

		LoadFile = new Button("Load File Data"); // Load data from file
		LoadFile.setStyle(
				"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 20pt; -fx-padding: 18px 40px;");
		BtStle(LoadFile); // set style to the button
		LoadFile.setOnAction(e -> {
			CostTable.clear();
			Maxnum.clear();
			led.clear();
			LoadFileData();

		});

		Calcbt = new Button("Calculate"); // Calculate Button
		Calcbt.setStyle(
				"-fx-background-color: #808080; -fx-text-fill: white; -fx-font-size: 20pt; -fx-padding: 18px 40px;");
		// set style to the button
		Calcbt.setOnAction(e -> { // set Action to the Button
			Calculate();

		});

		HBox calcpane = new HBox();
		calcpane.setPadding(new Insets(10, 10, 10, 10));
		calcpane.setSpacing(20);

		calcpane.getChildren().addAll(LoadFile, Calcbt, Maxnum, led);
		calcpane.setAlignment(Pos.CENTER);

		CostTable = new TextArea();
		CostTable.setPrefSize(100, 300);
		CostTable.setMaxWidth(400);
		CostTable.setPromptText("DP table ......");
		CostTable.setEditable(false);

		pane.getChildren().add(calcpane);
		pane.getChildren().add(CostTable);

/////////////////////////////////////  

		ShowDi = new Button("Show Diagram");
		ShowDi.setStyle(
				"-fx-background-color: #808080; -fx-text-fill: white; -fx-font-size: 20pt; -fx-padding: 18px 40px;");

		ShowDi.setOnAction(e -> {
			showDiagram(stage, backScene);
		});

		VBox RightPane = new VBox();
		RightPane.setPadding(new Insets(15, 15, 15, 15));
		RightPane.setSpacing(40);
		RightPane.getChildren().addAll(ShowDi);

		super.setLeft(RightPane);

//////////////////////////////////////////////
		Backbt = new Button("Back");
		Backbt.setStyle(
				"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 15px 35px;");
		BtStle(Backbt);

		Backbt.setOnAction(e -> {
			Back(stage, backScene);
		});
		super.setBottom(Backbt);

		super.setCenter(pane);

	}

	public void LoadFileData() { // Load file method
		Led.clear();
		FileChooser fch = new FileChooser();
		fch.setTitle("select file");
		Stage stage = new Stage();
		File f = fch.showOpenDialog(stage);
		if (f != null && f.length() != 0 && istxt(f)) { // just do if user select file and the file is not empty
			try {
				Scanner inp = new Scanner(f);
				int pow = inp.nextInt();
				while (inp.hasNextInt()) {// store the data from file in array
					Led.add(inp.nextInt());
				}
				System.out.println(pow);
				System.out.println(Led.toString());
				POW = new int[pow];
				LED = new int[Led.size()];
				isFullData = true; // if the data is store in the arrays set it true

				for (int i = 0; i < pow; i++)
					POW[i] = i + 1;

				for (int i = 0; i < Led.size(); i++)
					LED[i] = Led.get(i);

				inp.close();
				if (LED.length == POW.length && !hasDuplicates(LED)) {
					BtStle(Calcbt); // do the calculate button is available
				} else {
					if (hasDuplicates(LED))
						errorMas("The Selected File has Duplicate");
					else
						errorMas("Please choose a valid file");
				}
			} catch (FileNotFoundException e1) {
				errorMas("Please choose a valid file");
			}
		} else {
			errorMas("Please choose a valid file");
		}

	}

	// this method to save data the user enter it , and store it in array, then sent
	// it to FinallyData
	public void Calculate() {
		if (isFullData && LED.length == POW.length && !hasDuplicates(LED)) { // if the array is full data from the file

			CostTable.clear();
			FinallyData fd = new FinallyData(LED, POW);
			int max = fd.getResult().size();
			Maxnum.setText(max + "");
			Result = fd.getResult();
			led.setText(fd.getResult().toString());

			int[][] cost = fd.getCost();
			Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 24);
			CostTable.setFont(font);

			for (int i = 0; i < cost.length; i++) {
				for (int j = 0; j < cost[i].length; j++) {
					CostTable.appendText(cost[i][j] + "  ");
				}
				CostTable.appendText("\n");
			}

			BtStle(ShowDi); // do show diagram button is available
		}
	}

	// to set style to button
	public void BtStle(Button bt) {
		bt.setStyle(
				"-fx-background-color: #333333; -fx-text-fill: white; -fx-font-size: 20pt; -fx-padding: 18px 40px;");
		bt.setOnMouseEntered(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), bt); // bt1 effects
			scaleTransition.setToX(1.2);
			scaleTransition.setToY(1.2);
			scaleTransition.play();
		});
		bt.setOnMouseExited(e -> {
			ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), bt);
			scaleTransition.setToX(1.0);
			scaleTransition.setToY(1.0);
			scaleTransition.play();
		});
	}

	private void showDiagram(Stage stage, Scene scene) {
		// Create a new scene

		if (LED != null || POW != null) {
			Diagram D = new Diagram(stage, scene, Result, LED, POW);
			Scene scene1 = new Scene(D, 600, 600);

			Stage stage1 = new Stage();
			stage1.setMaximized(true);
			stage1.setScene(scene1);
			stage1.show();
		}
	}

	static boolean hasDuplicates(int[] leds) { // this method to check if the array has duplicate or no
		HashSet<Integer> hashset = new HashSet<>();

		for (int led : leds) {
			if (!hashset.add(led)) {
				// If adding the element to the set fails, it's a duplicate
				return true;
			}
		}
		// No duplicates found
		return false;
	}

	public boolean istxt(File f) {
		if (f.isFile()) {
			String name = f.getName();
			if (name.endsWith(".txt"))
				return true;
		}
		return false;
	}

	// show error message
	private void errorMas(String content) {
		 
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("warning");
		        alert.setContentText(content);
		        alert.showAndWait();
		    
	}

	// to do the action to the back button
	public void Back(Stage stage, Scene scene) {
		stage.close();
		stage.setScene(scene);
		stage.show();
	}

}
