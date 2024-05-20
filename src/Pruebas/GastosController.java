package pruebas;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Acount;
import model.AcountDAO;
import model.AcountDAOException;
import model.User;

/**
 * FXML Controller class
 *
 * @author nache
 */
public class AccountController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Button changesButton;
    @FXML
    private TextField loggedUserName;
    @FXML
    private TextField loggedUserEmail;
    @FXML
    private PasswordField loggedUserPassword;
    @FXML
    private PasswordField loggedUserPassword2;
    @FXML
    private TextField loggedUserSurname;
    @FXML
    private Button cancelButton;
    @FXML
    private FontAwesomeIcon nameInfoIcon;
    @FXML
    private FontAwesomeIcon surnameIconInfo;
    @FXML
    private FontAwesomeIcon nicknameInfoIcon;
    @FXML
    private FontAwesomeIcon emailInfoIcon;
    @FXML
    private FontAwesomeIcon passwordInfoIcon;
    @FXML
    private FontAwesomeIcon password2InfoIcon;
    @FXML
    private AnchorPane nameInfoPane;
    @FXML
    private AnchorPane surnameInfoPane;
    @FXML
    private AnchorPane nincknameInfoPane;
    @FXML
    private AnchorPane emailInfoPane;
    @FXML
    private AnchorPane passwordInfoPane;
    @FXML
    private Text saveChangesError;
    
    private User user;
    @FXML
    private TextField loggedUserNickname;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeImageViewCircular();
        try {
            // Inicializa la imagen del usuario cuando la escena se carga
            userImage.setImage(getLoggedUserImage());
            User loggedUser = Acount.getInstance().getLoggedUser();
            populateUserDetails(loggedUser);
        } catch (AcountDAOException | IOException e) {
            e.printStackTrace();
        }      
    }    
    public void makeImageViewCircular(){
        Circle clip = new Circle (109,100,100);
        userImage.setClip(clip);
    }
    private void populateUserDetails(User user) {
    loggedUserName.setText(user.getName());
    loggedUserSurname.setText(user.getSurname());
    loggedUserEmail.setText(user.getEmail());
    loggedUserNickname.setText(user.getNickName());
    }
    
    public Image getLoggedUserImage() throws AcountDAOException, IOException {
        return Acount.getInstance().getLoggedUser().getImage();
    }
    
    @FXML
    private void selectImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser(); 
        
        //hay que configurar para que el fileChooser solo permita formatos de imagen. Primero crear el filtro y luego añadirlo
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen (.png, *.jpg, *.jpge)", ".png", ".jpg", ".jpge"); 
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Mostrar la forma de seleccionar el archivo
        File file  = fileChooser.showOpenDialog(new Stage()); 
        
        if (file != null) {
            Image image = new Image(file.toURI().toString()); 
            userImage.setImage(image); 
        }
    } 

    @FXML
    private void returnToHome(MouseEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxmlapplication/MainView.fxml"));
                Parent homeRoot = loader.load();
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setScene(new Scene(homeRoot));
                MainViewController mainViewController = loader.getController();
                mainViewController.loadScene("Home.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    private Tooltip tooltip = new Tooltip();

    @FXML
    private void hideInfo(MouseEvent event) {
        tooltip.hide();
    }

    @FXML
    private void showInfo(MouseEvent event) {
        if (!tooltip.isShowing()) {
            Node icon = (Node) event.getSource();
            String infoMessage = getInfoMessage(icon.getId());
            tooltip.setText(infoMessage);
            tooltip.show(icon, event.getScreenX(), event.getScreenY());
        }
    }
    
    private String getInfoMessage(String iconName) {
        switch (iconName) {
            case "nameInfoIcon":
                return "Name must not contain any blank spaces, numbers or symbols";
                
            case "surnameIconInfo":
                return "Surname must not contain any blank spaces, numbers or symbols";
                
            case "nicknameInfoIcon":
                return "Nickname can not be changed "; 
                
            case "emailInfoIcon":
                return "Email must be: _@_.__"; 
                
            case "passwordInfoIcon":    
                return "Password must be (at least) 6 characters long"; 
                
            case "password2InfoIcon":
                return "Password must be (at least) 6 characters long";
                
            default:
                return ""; 
        }
    }
    
    /*
        METODOS PARA COMPROBAR EL FORMATO DE LOS DATOS INTRODUCIDOS
    */
     // Que no este vacio, que solo tenga letrae y no tenga espacios en blanco
    private boolean validateName (String name) {
        return !name.trim().isEmpty() && name.matches("[a-zA-Z]+") && !name.contains(" ");
    }
    
    // Que no este vacio, que solo tenga letrae y no tenga espacios en blanco
    private boolean validateSurname (String surname) {
        return !surname.trim().isEmpty() && surname.matches("[a-zA-Z]+") && !surname.contains(" ");
    }
    
    //Que tenga la estructura correcta de un email
    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    // Que sea de al menos 6 caracteres de longitud
    private boolean validatePassword(String password) {
        return password.length() >= 6;
    }
    
    // Que las contraseñas coincidan
    private boolean passwordsMatch (String password, String password2) {
        return password.equals(password2);
    }


    @FXML
    private void validateName(ActionEvent event) {
        if (!validateName(loggedUserName.getText())) {
            loggedUserName.getStyleClass().add("invalid-field");
            loggedUserName.getStyleClass().removeAll("valid-field");
            
        } else {
            loggedUserName.getStyleClass().removeAll("invalid-field");
            loggedUserName.getStyleClass().add("valid-field");
        }
    }

    @FXML
    private void validateEmail(ActionEvent event) {
         if (!validateEmail(loggedUserEmail.getText())) {
            loggedUserEmail.getStyleClass().add("invalid-field");
            loggedUserEmail.getStyleClass().removeAll("valid-field");
            
        } else {
            loggedUserEmail.getStyleClass().removeAll("invalid-field");
            loggedUserEmail.getStyleClass().add("valid-field");
        }
    }

    @FXML
    private void validatePassword(ActionEvent event) {
         if (!validatePassword(loggedUserPassword.getText())) {
            loggedUserPassword.getStyleClass().add("invalid-field");
            loggedUserPassword.getStyleClass().removeAll("valid-field");
            
        } else {
            loggedUserPassword.getStyleClass().removeAll("invalid-field");
            loggedUserPassword.getStyleClass().add("valid-field");
        }
    }

    @FXML
    private void validatePassword2(ActionEvent event) {
        if (!validatePassword(loggedUserPassword2.getText()) || !passwordsMatch(loggedUserPassword.getText(), loggedUserPassword2.getText())) {
            loggedUserPassword2.getStyleClass().add("invalid-field");
            loggedUserPassword2.getStyleClass().removeAll("valid-field");
            
        } else {
            loggedUserPassword2.getStyleClass().add("valid-field");
            loggedUserPassword2.getStyleClass().removeAll("invalid-field");
        }
        
    }

    @FXML
    private void validateSurname(ActionEvent event) {
        if (!validateSurname(loggedUserSurname.getText())) {
            loggedUserSurname.getStyleClass().add("invalid-field");
            loggedUserSurname.getStyleClass().removeAll("valid-field");
            
        } else {
            loggedUserSurname.getStyleClass().removeAll("invalid-field");
            loggedUserSurname.getStyleClass().add("valid-field");
        }
    }

    @FXML
    private void saveChangesAccount(MouseEvent event) {
        boolean isNameValid = validateName(loggedUserName.getText());
        boolean isSurnameValid = validateSurname(loggedUserSurname.getText());
        boolean isEmailValid = validateEmail(loggedUserEmail.getText());
        boolean isPasswordValid = validatePassword(loggedUserPassword.getText());
        boolean isPassword2Valid = validatePassword(loggedUserPassword2.getText()) && passwordsMatch(loggedUserPassword.getText(), loggedUserPassword2.getText());
        
        if (isNameValid && isSurnameValid && isEmailValid && isPasswordValid && isPassword2Valid) {
        //falta meter todo lo de isvalid y que se vea texto o no de error.
        //me da fallo al hacerlo el método returnToHome y el tooltip
        String name = loggedUserName.getText();
        String surname = loggedUserSurname.getText();
        String email = loggedUserEmail.getText();
        String password = loggedUserPassword.getText();
        String password2 = loggedUserPassword2.getText();
        Image image = userImage.getImage();
        
        try{
            /*Alert alert2 = new Alert (AlertType.CONFIRMATION);
                alert2.setTitle("Confirm Changes");
                alert2.setHeaderText("Save Changes?");
                alert2.setContentText("Do you want to save the changes?");
                Optional<ButtonType> result2 = alert2.showAndWait();
                if(result2.isPresent()&& result2.get() == ButtonType.OK){
                    System.out.println("OK");*/
            User loggedUser = Acount.getInstance().getLoggedUser();
            loggedUser.setName(name);
            loggedUser.setSurname(surname);
            loggedUser.setEmail(email);
            loggedUser.setImage(image);
            //if(password.equals(password2)){
                //loggedUser.setPassword(password);
            System.out.println("Cambiado");
            /*}else{ 
                Alert alert = new Alert (AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Validation error");
                alert.setContentText("Passwords do not match. \nPlease, try again");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()&& result.get() == ButtonType.OK){
                    System.out.println("OK");
                }
          }*/
        }catch(Exception e){
            e.printStackTrace();
   
        }}
        else{
        saveChangesError.setVisible(true);
            //creamos una animacion para que el texto desaparezca con el tiempo
            Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(3),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        saveChangesError.setVisible(false);
                    }
                }
            ));
            timeline.play();
            System.out.println("One or more fields are not correct\n");
    }
}
  
}