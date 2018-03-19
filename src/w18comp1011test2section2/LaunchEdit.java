package w18comp1011test2section2;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jwright
 */
public class LaunchEdit extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("EditUserView.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Edit User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void loadUserButtonPushed(ActionEvent event)
    {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
