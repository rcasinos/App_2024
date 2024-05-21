package Loggeado.Perfil;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.User;

public class Mi_Perfil_Controller {

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private Label labelNickname;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelCorreo;

    @FXML
    private Label labelPassword;

    @FXML
    private TextField textFieldNickname;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldApellido;

    @FXML
    private TextField textFieldCorreo;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button botonEdit;

    @FXML
    private Button botonSave;

    private boolean editando = false;
    private User loggedUser;

    // Método para inicializar los componentes
    public void initialize() {
        makeImageViewCircular();
        try {
            // Inicializa la imagen del usuario cuando la escena se carga
            imagenPerfil.setImage(getLoggedUserImage());
            loggedUser = Acount.getInstance().getLoggedUser();
            populateUserDetails(loggedUser);
        } catch (AcountDAOException | IOException e) {
            e.printStackTrace();
        }

        // Deshabilitar la edición de los campos inicialmente
        textFieldNickname.setEditable(false);
        setEditableFields(false);
    }

    // Método para hacer circular la imagen de perfil
    private void makeImageViewCircular() {
        imagenPerfil.setPreserveRatio(true);
        imagenPerfil.setFitWidth(150);
        imagenPerfil.setFitHeight(150);

        Circle clip = new Circle();
        clip.radiusProperty().bind(imagenPerfil.fitWidthProperty().divide(2));
        clip.centerXProperty().bind(imagenPerfil.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(imagenPerfil.fitHeightProperty().divide(2));

        imagenPerfil.setClip(clip);
    }

    // Método para llenar los detalles del usuario en los campos de texto
    private void populateUserDetails(User user) {
        if (user != null) {
            textFieldNickname.setText(user.getNickName());
            textFieldNombre.setText(user.getName());
            textFieldApellido.setText(user.getSurname());
            textFieldCorreo.setText(user.getEmail());
            textFieldPassword.setText("Falta por implementar"); // No llenar el campo de password por seguridad
        } else {
            System.out.println("El usuario logueado es nulo.");
        }
    }

    // Método para obtener la imagen del usuario logueado
    public Image getLoggedUserImage() throws AcountDAOException, IOException {
        return Acount.getInstance().getLoggedUser().getImage();
    }

    // Evento para habilitar la edición de los campos
    @FXML
    private void editarPerfil_click(MouseEvent event) {
        if (!editando) {
            setEditableFields(true);
            editando = true;
        }
    }

    // Evento para guardar los cambios y deshabilitar la edición
    @FXML
    private void guardarCambios_click(MouseEvent event) {
        if (editando) {
            // Obtener los valores de los campos de texto
            String nuevoNombre = textFieldNombre.getText();
            String nuevoApellido = textFieldApellido.getText();
            String nuevoCorreo = textFieldCorreo.getText();
            String nuevaPassword = textFieldPassword.getText();

            // Validar los datos
            if (validateInputs(nuevoNombre, nuevoApellido, nuevoCorreo, nuevaPassword)) {
                try {
                    loggedUser.setName(nuevoNombre);
                    loggedUser.setSurname(nuevoApellido);
                    loggedUser.setEmail(nuevoCorreo);
                    if (!nuevaPassword.isEmpty()) {
                        loggedUser.setPassword(nuevaPassword);
                    }

                    // Deshabilitar la edición de los campos
                    setEditableFields(false);
                    editando = false;
                    System.out.println("Datos cambiado correctamente");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Mostrar mensaje de error en la validación
                System.out.println("Error en la validación de los datos.");
            }
        }
    }


    // Métodos para comprobar el formato de los datos introducidos
    private boolean validateName(String name) {
        return !name.trim().isEmpty() && name.matches("[a-zA-Z]+") && !name.contains(" ");
    }

    private boolean validateSurname(String surname) {
        return !surname.trim().isEmpty() && surname.matches("[a-zA-Z]+") && !surname.contains(" ");
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.isEmpty() || password.length() >= 6;
    }

    private boolean validateInputs(String name, String surname, String email, String password) {
        return validateName(name) && validateSurname(surname) && validateEmail(email) && validatePassword(password);
    }

    // Método para habilitar o deshabilitar la edición de los campos
    private void setEditableFields(boolean editable) {
        textFieldNombre.setEditable(editable);
        textFieldApellido.setEditable(editable);
        textFieldCorreo.setEditable(editable);
        textFieldPassword.setEditable(editable);
    }

    @FXML
    private void change_picture_exited(MouseEvent event) {
    }

    @FXML
    private void change_picture_entered(MouseEvent event) {
    }

    @FXML
    private void edit_exited(MouseEvent event) {
    }

    @FXML
    private void edit_entered(MouseEvent event) {
    }

    @FXML
    private void guardarCambios_salir(MouseEvent event) {
    }

    @FXML
    private void guardarCambios_entered(MouseEvent event) {
    }

    @FXML
    private void change_picture_click(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen (.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());

        Image image = null;
        if (file != null) {
            image = new Image(file.toURI().toString());
            imagenPerfil.setImage(image);
        }

        // Lógica para guardar la nueva imagen en la base de datos
        if (image != null) {
            try {
                // Obtener el usuario logueado
                User loggedUser = Acount.getInstance().getLoggedUser();

                // Actualizar la imagen del usuario en la base de datos
                loggedUser.setImage(image);

                // Mostrar mensaje de éxito
                System.out.println("Imagen guardada correctamente.");
            } catch (AcountDAOException | IOException e) {
                // Manejar excepción en caso de error al guardar la imagen
                e.printStackTrace();
                // Mostrar mensaje de error al usuario
                System.err.println("Error al guardar la imagen.");
            }
        }
    }

}
