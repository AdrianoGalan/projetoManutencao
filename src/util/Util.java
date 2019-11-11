/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import model.Equipamento;
import model.Funcionario;
import model.Pj;
import model.Usuarios;
import tela.FXMLTelaEquipamentoController;
import tela.FXMLTelaEquipeController;
import tela.FXMLTelaFornecedorController;

/**
 *
 * @author drico
 */
public class Util {

    private static Usuarios usuarioLogado;
    private static Funcionario funcionarioAtualizar;
    private static Pj pjAtualizar;
    private static Equipamento equipamentoAtualizar;
    private static FXMLTelaEquipeController telaEquipeCarregada;
    private static FXMLTelaFornecedorController telaFornecedorCarregada;
    private static FXMLTelaEquipamentoController telaEquipamentoCarregada;
    private static String matricula;
    
    private static ObservableList<String> tipoMaquinas = FXCollections.observableArrayList("PRENSA", "OPERATRIZ", "SOBRO AREIA", "COQUILHA BAIXA PRESÃO", "COQUILHADEIRA", "FORNO");

    public static Equipamento getEquipamentoAtualizar() {
        return equipamentoAtualizar;
    }

    public static void setEquipamentoAtualizar(Equipamento equipamentoAtualizar) {
        Util.equipamentoAtualizar = equipamentoAtualizar;
    }

    public static FXMLTelaEquipamentoController getTelaEquipamentoCarregada() {
        return telaEquipamentoCarregada;
    }

    public static void setTelaEquipamentoCarregada(FXMLTelaEquipamentoController telaEquipamentoCarregada) {
        Util.telaEquipamentoCarregada = telaEquipamentoCarregada;
    }

    public static FXMLTelaFornecedorController getTelaFornecedorCarregada() {
        return telaFornecedorCarregada;
    }

    public static void setTelaFornecedorCarregada(FXMLTelaFornecedorController telaFornecedorCarregada) {
        Util.telaFornecedorCarregada = telaFornecedorCarregada;
    }

    public static Pj getPjAtualizar() {
        return pjAtualizar;
    }

    public static void setPjAtualizar(Pj pjAtualizar) {
        Util.pjAtualizar = pjAtualizar;
    }

    public static String getMatricula() {
        return matricula;
    }

    public static void setMatricula(String matricula) {
        Util.matricula = matricula;
    }

    public static FXMLTelaEquipeController getTelaEquipeCarregada() {
        return telaEquipeCarregada;
    }

    public static void setTelaEquipeCarregada(FXMLTelaEquipeController telaEquipeCarregada) {
        Util.telaEquipeCarregada = telaEquipeCarregada;
    }

    public static Usuarios getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuarios usuarioLogado) {
        Util.usuarioLogado = usuarioLogado;
    }

    public static Funcionario getFuncionarioAtualizar() {
        return funcionarioAtualizar;
    }

    public static void setFuncionarioAtualizar(Funcionario funcionarioAtualizar) {
        Util.funcionarioAtualizar = funcionarioAtualizar;
    }

    public static void mensagem(String mgn, String titulo) {

        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setHeaderText(mgn);
        dialogo.setTitle(titulo);
        dialogo.showAndWait();

    }
    
    public static String caixaEntrada(String titulo, String mensagem, String campo){
        
        TextInputDialog dialogoNome = new TextInputDialog();
        
      

        dialogoNome.setTitle(titulo);
        dialogoNome.setHeaderText(mensagem);
        dialogoNome.setContentText(campo);
        // se o usuário fornecer um valor, assignamos ao nome
        return dialogoNome.showAndWait().get();
         
       

        
    }

    public static ObservableList<String> getTipoMaquinas() {
        return tipoMaquinas;
    }

    public static void setTipoMaquinas(ObservableList<String> aTipoMaquinas) {
        tipoMaquinas = aTipoMaquinas;
    }

}
