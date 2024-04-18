import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Power extends HBox {
	private ImageView imageview;
	private Label l;

	public Power(int index, boolean OnOff) {
		setPadding(new Insets(20, 20, 20, 20));
		setSpacing(7);
		Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 20);
		l = new Label(index + "");
		l.setTextFill(Color.BLACK);
		l.setFont(font);
		l.setPadding(new Insets(40, 0, 0, 0));
		Image image1 = new Image("file:///C:/java-2/Algorithm_project1/images-removebg-preview%20(3).png");
		Image image2 = new Image("file:///C:/java-2/Algorithm_project1/5258.png_300-removebg-preview.png");

		if (OnOff) {
			imageview = new ImageView(image1);

		} else {
			imageview = new ImageView(image2);
		}
		imageview.setFitHeight(100);
		imageview.setFitWidth(100);
		getChildren().add(l);
		getChildren().add(imageview);

	}

}
