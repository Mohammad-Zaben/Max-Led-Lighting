import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Randomly extends BorderPane {
	private Label l;
	private TextField pownum, Maxnum, led;
	private TextArea CostTable;
	private Button Enterbt, Backbt, Calcbt, ShowDi;
	private ArrayList<TextField> LedsTextField = new ArrayList<>();
	private LinkedList Result;
	private int[] pow, led2;
	Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 60);

	public Randomly(Stage stage, Scene backScene) {
		super.setStyle("-fx-background-image: url('file:///C:/java-2/Algorithm_project1/test-lamp.jpg');" // set
																											// background
				+ "-fx-background-size:cover;-fx-background-position: center center;");
		l = new Label("Randomly Test");

		l.setFont(font);
		l.setTextFill(Color.WHEAT);
		super.setAlignment(l, Pos.CENTER);
		super.setTop(l);

		VBox pane = new VBox();
		pane.setSpacing(15);
		pane.setAlignment(Pos.CENTER);

		pownum = new TextField();
		pownum.setStyle("-fx-background-color: rgba(200, 200, 200, 0.7);" + // Gray background color
				"-fx-background-radius: 5;" + "-fx-padding: 5px;" + "-fx-font-size: 18px;" + "-fx-text-fill: white;"
				+ "-fx-prompt-text-fill: #A9A9A9;" + "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 10;"
				+ "-fx-border-width: 1px;-fx-prompt-text-fill: #FF0000;");
		pownum.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				pownum.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
		pownum.setMaxWidth(210);
		pownum.setPrefHeight(50);
		pownum.setPromptText("Number of Power(1-10)");

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);

		Enterbt = new Button("Enter");

		BtStle(Enterbt);
		Enterbt.setOnAction(e -> { // Enter Button Action , is sent the number of power to createTextFields method
			String str = pownum.getText();
			if (!str.equals("")) {
				int numberOfTextFields = Integer.parseInt(str);
				if (numberOfTextFields <= 10) {
					createTextFields(gridPane, numberOfTextFields);
					Enterbt();
					BtDeleteStyle(ShowDi);
					BtStle(Calcbt); // set style to the button

				} else {
					errorMas("Randomly Test is only 1-10");
				}
			}
		});

		HBox Toppane = new HBox(); // this pane has the power number text field and enter button
		Toppane.setSpacing(25);
		Toppane.setAlignment(Pos.CENTER);

		Toppane.getChildren().addAll(pownum, Enterbt);
		pane.getChildren().add(Toppane);

		pane.getChildren().add(gridPane);

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

		Calcbt = new Button("Calculate"); // Calculate Button
		Calcbt.setStyle(
				"-fx-background-color: #808080; -fx-text-fill: white; -fx-font-size: 20pt; -fx-padding: 18px 40px;");

		Calcbt.setOnAction(e -> { // set Action to the Button

			Calculate();
			BtStle(ShowDi);

		});
		HBox calcpane = new HBox();
		calcpane.setPadding(new Insets(10, 10, 10, 10));
		calcpane.setSpacing(20);

		calcpane.getChildren().addAll(Calcbt, Maxnum, led);
		calcpane.setAlignment(Pos.CENTER);
		pane.getChildren().add(calcpane);

		CostTable = new TextArea();
		CostTable.setPrefSize(100, 300);
		CostTable.setMaxWidth(400);
		CostTable.setPromptText("DP table ......");
		CostTable.setEditable(false);
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

		BtStle(Backbt);

		Backbt.setOnAction(e -> {
			Back(stage, backScene);
		});
		super.setBottom(Backbt);

		super.setCenter(pane);

	}

	// this method to save data the user enter it , and store it in array, then sent
	// it to FinallyData
	public void Calculate() {
		CostTable.clear();
		int x = Integer.parseInt(pownum.getText());
		pow = new int[x];
		for (int i = 0; i < x; i++) {
			pow[i] = i + 1;
		}

		FinallyData fd = new FinallyData(led2, pow); // sent data by parameter to FinallyData class
		Result = fd.getResult();
		int max = fd.getResult().size();
		Maxnum.setText(max + "");

		led.setText(fd.getResult().toString());

		int[][] cost = fd.getCost();
		Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 24);
		CostTable.setFont(font);
		CostTable.appendText("*  ");
		CostTable.appendText("0   ");
		for (int i = 0; i < led2.length; i++) {
			CostTable.appendText(led2[i] + "  ");
		}
		CostTable.appendText("\n");
		for (int i = 0; i < cost.length; i++) {
			CostTable.appendText(i + "  ");
			for (int j = 0; j < cost[i].length; j++) {
				CostTable.appendText(cost[i][j] + "  ");
			}
			CostTable.appendText("\n");
		}
	}

	public void Enterbt() { // this method to set the value in the text fields
		led2 = new int[LedsTextField.size()];

		for (int i = 0; i < led2.length; i++) {
			led2[i] = i + 1;
		}

		RandomSort(led2);

		for (int i = 0; i < LedsTextField.size(); i++) {
			LedsTextField.get(i).setText(led2[i] + "");
		}
	}

	private void showDiagram(Stage stage, Scene scene) { // this method to sent data to show diagram class,and display
															// it
		// Create a new scene
		if (led2 != null || pow != null) {
			stage.setMaximized(true);
			Diagram D = new Diagram(stage, scene, Result, led2, pow);
			Scene scene1 = new Scene(D, 600, 600);

			// stage.close();
			Stage stage1 = new Stage();
			stage1.setMaximized(true);
			stage1.setScene(scene1);
			stage1.show();
		} else {
			errorMas("Enter the data first");
		}
	}

	// this method to create TextField according to the power number

	private void createTextFields(GridPane gridPane, int numberOfTextFields) {
		if (numberOfTextFields <= 10) {
			gridPane.getChildren().removeIf(node -> node instanceof TextField); // Clearing the pan from TextFields

			LedsTextField.clear(); // Clear the list

			for (int i = 0; i < numberOfTextFields; i++) {
				TextField textField = new TextField();
				textField.setStyle("-fx-background-color: rgba(200, 200, 200, 0.5);" + "-fx-background-radius: 5;"
						+ "-fx-padding: 5px;" + "-fx-font-size: 20px;" + "-fx-text-fill: black;"
						+ "-fx-prompt-text-fill: #A9A9A9;" + "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 10;"
						+ "-fx-border-width: 1px;");

				textField.setEditable(false);
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.matches("\\d*")) {
						textField.setText(newValue.replaceAll("[^\\d]", ""));
					}
				});

				LedsTextField.add(textField); // Add the reference to the list
				gridPane.add(textField, i, 1);
			}
		} else {

		}

	}

	public void RandomSort(int[] led) { // this method to do random shuffling to led array
		Random random = new Random();
		for (int i = led.length - 1; i > 0; i--) {
			int x = random.nextInt(i + 1);
			int temp = led[i];
			led[i] = led[x];
			led[x] = temp;
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

	public void BtDeleteStyle(Button bt) {
		bt.setStyle(
				"-fx-background-color: #808080; -fx-text-fill: white; -fx-font-size: 20pt; -fx-padding: 18px 40px;");
		bt.setOnMouseEntered(null);

		bt.setOnMouseExited(null);
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
