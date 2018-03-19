
package w18comp1011test2section2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class EditUserViewController implements Initializable {
    @FXML private TextField contactIDTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField streetAddressTextField;
    @FXML private TextField cityTextField;
    @FXML private Label errorMsgLabel;
    @FXML private ComboBox<String> firstNameComboBox;
        
    /**
     * Your goal for test 2 is to create this method.  It should read from
     * the contactIDTextField and update the view with the infromation stored
     * in the database.
     */
    public void getUserData() throws SQLException
    {
        try{
            //clear the fields
            this.errorMsgLabel.setText("");
            this.firstNameTextField.setText("");
            this.lastNameTextField.setText("");
            this.streetAddressTextField.setText("");
            this.cityTextField.setText("");
            
            int contactId = Integer.parseInt(contactIDTextField.getText());
            
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            try
            {
                //1. Connect to the database
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "student", "student");

                //2. Create a String that holds the query with ? as user inputs
                String sql = "SELECT * FROM contacts WHERE contactID = ?";

                //3. prepare the query
                preparedStatement = conn.prepareStatement(sql);

                //4. Bind the values to the parameters
                preparedStatement.setInt(1, contactId);
                
                rs = preparedStatement.executeQuery();
                
                while(rs.next())  //this will piont to the first record
                {
                    //update the text fields
                    this.firstNameTextField.setText(rs.getString("firstName"));
                    this.lastNameTextField.setText(rs.getString("lastName"));
                    this.streetAddressTextField.setText(rs.getString("streetAddress"));
                    this.cityTextField.setText(rs.getString("city"));
                }
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }
            finally
            {
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
            
            if (rs != null)
                rs.close();
            }
        }
        catch (NumberFormatException e)
        {
            errorMsgLabel.setText("Sorry, only whole numbers for contact ID");
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNameTextField.setDisable(true);
        lastNameTextField.setDisable(true);
        streetAddressTextField.setDisable(true);
        cityTextField.setDisable(true);
        errorMsgLabel.setText("");
        
        updateComboBoxFromDB(); //this is a bonus to populate the combobox methods
       
        
    }    
    
    /**
     * Bonus:  If you updated the ComboBox, when a first name is selected,
     * this should populate all of the text fields
     */
    public void comboBoxWasUpdated()
    {
    }
        
    
    /**
     * Bonus:  The goal here is to pull all the first names from the database
     * and populate the comboBox with the first names. 
     * @throws SQLException 
     */
    public void updateComboBoxFromDB() 
    {
    }
    
}
