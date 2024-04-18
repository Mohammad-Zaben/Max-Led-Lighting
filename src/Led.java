import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Led extends HBox {
	private ImageView imageview;
	private Label l;
	private Image image1;
	private Image image2;
    private boolean onoff;
	public Led(int index, boolean OnOff) {
		this.onoff=OnOff;
		setPadding(new Insets(20, 20, 20, 20));
		setSpacing(7);
		Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 20);
		l = new Label(index + "");
		l.setTextFill(Color.BLACK);
		l.setFont(font);
		l.setPadding(new Insets(40, 0, 0, 0));

		
		image2 = new Image("file:///C:/java-2/Algorithm_project1/imageedit_2_5820485756.png");

		imageview = new ImageView(image2);

		imageview.setFitHeight(100);
		imageview.setFitWidth(100);
		getChildren().add(imageview);
		getChildren().add(l);

	}

	public void setOnLed() {
		getChildren().removeAll(imageview,l);
		image1 = new Image("file:///C:/java-2/Algorithm_project1/Screenshot_2023-12-02_182657-removebg-preview.png");
		imageview = new ImageView(image1);
		imageview.setFitHeight(100);
		imageview.setFitWidth(100);
		getChildren().add(imageview);
		getChildren().add(l);
	}

	public boolean isOnoff() {
		return onoff;
	}
	
	

}
