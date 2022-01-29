import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;


public class WordleController {

   private   String[] green = {"", "", "", "", ""};   //  is correct and in the correct position
    private  String[] yellow1 = {"", "", "", "", ""};  // answer but not in the right position
    private  String[] yellow2 = {"", "", "", "", ""};
    private  String[] yellow3 = {"", "", "", "", ""};
    private  String gray = "";    //  it is not in the answer at all


    public void searchOnClick(ActionEvent actionEvent) {


        int index = 0;

        for (int i = 0; i < green.length; i++) {
            green[i] = allTextFields[index++].getText().trim().toLowerCase();
        }
        for (int i = 0; i < yellow1.length; i++) {
            yellow1[i] = allTextFields[index++].getText().trim().toLowerCase();
        }
        for (int i = 0; i < yellow2.length; i++) {
            yellow2[i] = allTextFields[index++].getText().trim().toLowerCase();
        }
        for (int i = 0; i < yellow3.length; i++) {
            yellow3[i] = allTextFields[index++].getText().trim().toLowerCase();
        }

        gray = allTextFields[index].getText().trim().toLowerCase();




        String[] results = new Solver(green, yellow1, yellow2, yellow3, gray).solve();

        if(results == null)
            return;

        int numOfResult = results.length;

        int numOfColumn =  Integer.valueOf( columnChoiceBox.getValue().toString());

        String text =   Solver.toArrayColumn(results,numOfColumn);

        if(uppercaseCheckBox.isSelected())
            text = text.toUpperCase();


        resultTextArea.setText( "Total match: " + numOfResult + "\n" + text);
    }

    void testInput(){

        String msg = "";

        for (int i = 0; i < green.length; i++) {
            msg += green[i] + ",";
        }
        msg += "\n";
        for (int i = 0; i < yellow1.length; i++) {
            msg += yellow1[i] + ",";
        }
        msg += "\n";
        for (int i = 0; i < yellow2.length; i++) {
            msg += yellow2[i] + ",";
        }
        msg += "\n";
        for (int i = 0; i < yellow3.length; i++) {
            msg += yellow3[i] + ",";
        }
        msg += "\n" + gray;

        resultTextArea.setText(msg);

        // new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    public void clearAllOnClick(ActionEvent actionEvent) {

        for (TextField Tf : allTextFields) {
            Tf.setText("");
        }
    }





    @FXML
    void initialize() {

        allTextFields = new TextField[]{greenTf1,
                greenTf2,
                greenTf3,
                greenTf4,
                greenTf5,
                yellowOneTf1,
                yellowOneTf2,
                yellowOneTf3,
                yellowOneTf4,
                yellowOneTf5,
                yellowTwoTf1,
                yellowTwoTf2,
                yellowTwoTf3,
                yellowTwoTf4,
                yellowTwoTf5,
                yellowThreeTf1,
                yellowThreeTf2,
                yellowThreeTf3,
                yellowThreeTf4,
                yellowThreeTf5,
                grayTf
        };

        yellowTextFields = new TextField[]{
                yellowOneTf1,
                yellowOneTf2,
                yellowOneTf3,
                yellowOneTf4,
                yellowOneTf5,
                yellowTwoTf1,
                yellowTwoTf2,
                yellowTwoTf3,
                yellowTwoTf4,
                yellowTwoTf5,
                yellowThreeTf1,
                yellowThreeTf2,
                yellowThreeTf3,
                yellowThreeTf4,
                yellowThreeTf5,
        };

        String style = ""
                + "-fx-font-size: 70px;"
                + "-fx-font-weight: bold;"
                + "-fx-font-family: Verdana;"
                + "-fx-alignment: center;"
                + "-fx-padding: 1 1 1 1 ;  ";

        String greenBgStyle = "-fx-text-fill:#2C5C25;" + " -fx-control-inner-background: #25FF16;";
        String yellowBgStyle = "-fx-text-fill:#7B333A;" + " -fx-control-inner-background: #F8F90E;";
        String grayBgStyle = "-fx-text-fill:#1F201F;" + " -fx-control-inner-background: #B2B4B2;";


        for (TextField Tf : allTextFields) {
            Tf.setStyle(style + greenBgStyle);


            // set max length 1, letter only
            Tf.textProperty().addListener((observable, oldValue, newValue) -> {

                oldValue = oldValue.toUpperCase();
                newValue = newValue.toUpperCase();

                if (!isLetter(newValue))
                    newValue = oldValue;

                if (Tf.getId() != grayTf.getId())
                    if (newValue.length() > 1) {
                        newValue = oldValue.substring(0, 1);
                    }

                Tf.setText(newValue);
            });
        }

        for (TextField Tf : yellowTextFields) {
            Tf.setStyle(style + yellowBgStyle);
        }

        grayTf.setStyle(style + grayBgStyle);

        columnChoiceBox.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        columnChoiceBox.getSelectionModel().select(3);


    }


    TextField[] allTextFields, yellowTextFields;

    @FXML
    TextArea resultTextArea;

    @FXML
    ChoiceBox columnChoiceBox;

    @FXML
    CheckBox uppercaseCheckBox;

    @FXML
    public TextField greenTf1,
            greenTf2,
            greenTf3,
            greenTf4,
            greenTf5,
            yellowOneTf1,
            yellowOneTf2,
            yellowOneTf3,
            yellowOneTf4,
            yellowOneTf5,
            yellowTwoTf1,
            yellowTwoTf2,
            yellowTwoTf3,
            yellowTwoTf4,
            yellowTwoTf5,
            yellowThreeTf1,
            yellowThreeTf2,
            yellowThreeTf3,
            yellowThreeTf4,
            yellowThreeTf5,
            grayTf;


    boolean isLetter(String s) {

        int len = s.length();
        for (int i = 0; i < len; i++) {
            if ((Character.isLetter(s.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public void helpOnClick(ActionEvent actionEvent) {

        String helpSt = "A five-letter word is chosen which players aim to guess within six tries." +
                "\nAfter every guess, each letter is marked as either green, yellow or gray.\n\n"
                +"Green indicates that letter is correct and in the correct position. \n"
                +"Yellow means it is in the answer but not in the right position.\n"
                +"Gray indicates it is not in the answer at all\n";

        resultTextArea.setText(helpSt);
    }
}
