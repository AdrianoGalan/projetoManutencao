/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.AreaDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Area;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaAreaController implements Initializable {

    @FXML
    private JFXTextField tfNomeArea;
    @FXML
    private JFXButton btEntrar;
    @FXML
    private JFXButton btFechar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionBtEntrar(ActionEvent event) {

        Area area = new Area();
        if (!tfNomeArea.getText().isEmpty()) {
            area.setNomeArea(tfNomeArea.getText());
            AreaDao ad = new AreaDao();
            if (ad.salvarArea(area)) {
                btFechar.getScene().getWindow().hide();
            }

        } else {
            Util.mensagem("Preencha nome da area", "Nome invalido");
        }

    }

    @FXML
    private void actionBtFechar(ActionEvent event) {

        btFechar.getScene().getWindow().hide();
    }

}
