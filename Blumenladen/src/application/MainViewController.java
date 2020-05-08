package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class MainViewController implements Initializable {
	
	Manager manager = new Manager();
	
	@FXML ChoiceBox<String> farbe1;
	@FXML ChoiceBox<String> farbe2;
	
	@FXML Slider preferenzWert;
	
	@FXML Label label1;
	@FXML Label label21;
	@FXML Label label22;
	@FXML Label label23;
	@FXML Label label24;
	@FXML Label label31;
	@FXML Label label32;
	@FXML Label label41;
	@FXML Label label42;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		farbe1.setItems(manager.getFarben());
		farbe2.setItems(manager.getFarben());
	}
	// gives Lieblingsfarben 
	public void getLieblingsfarben(ActionEvent event) {
	String text = new String(((CheckBox)event.getSource()).getText());
		if ( ((CheckBox)event.getSource()).isSelected()) {
		if(!manager.getLieblingsFarben().contains(text)) {
			manager.getLieblingsFarben().add(text);
		}
	}	if ( !((CheckBox)event.getSource()).isSelected()) {
		manager.getLieblingsFarben().remove(text);
		}
		System.out.println(manager.getLieblingsFarben().toString());
	}
	
	public void bestaetigen(ActionEvent event) {
		manager.getPreferenzen().add(new int[] {
				(int) preferenzWert.getValue(),
				farbe1.getSelectionModel().getSelectedIndex(),
				farbe2.getSelectionModel().getSelectedIndex()
						});
		manager.sortiereNachWert(manager.getPreferenzen());
		
		/**for (int[] combo : manager.getPreferenzen()) {
			System.out.println("Farben: " + manager.getFarben().get(combo[1]) + " " + manager.getFarben().get(combo[2]) + " Wert " + combo[0]);
		} **/
		faerbeLabels();
	}
	
	public void faerbeLabels() {
		//ArrayList<int[]> besteCombos = manager.getCombosMitGroesstemWert(manager.getPreferenzen());
	//	manager.sortiereNachLieblingsfarben(manager.getPreferenzen());
		//manager.gibBesteKomboMitGroestemWert();
		//manager.gibBesteKomboMitMeistenLieblingsfarben();
		manager.sortiereNachLieblingsfarben(manager.getPreferenzen());
	}
	


}

