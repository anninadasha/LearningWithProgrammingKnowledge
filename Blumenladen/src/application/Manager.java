package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Manager {
	
   String[] ausgewaehlteKomboNachWert = new String[2];
   String[] ausgewaehlteKomboNachLieblingsfarben = new String[2];
	
	final private ObservableList<String> farben =FXCollections.observableArrayList(
			new String("rot"),
			new String("rosa"),
			new String("orange"),
			new String("gelb"),
			new String("grün"),
			new String("blau"),
			new String("türkis")
			);
	
	private ObservableList<String> lieblingsFarben = FXCollections.observableArrayList();
	private ArrayList<int[]> preferenzen = new ArrayList<int[]>();


	// Sortiert nach Wert, ohne zu konvertieren     Rückgabe ArrayList<int[3]>
	public ArrayList<int[]> sortiereNachWert(ArrayList<int[]> combos) {
    Collections.sort(combos , new Comparator< int[]>(){
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0]==arg1[0]) {
					return 0;
				}else {
					if (arg0[0]>arg1[0]) {
						return - (arg0[0]-arg1[0]);
					} else {
						return arg1[0]-arg0[0];
					}
				}
			}
		});
     // zu Löschen  Anfang
     for (int[] combo : combos) {
			System.out.println("Farben: " + farben.get(combo[1]) + " " + farben.get(combo[2]) + " Wert " + combo[0]);
		} 
     // zu Löschen Ende
        return combos;
	}
	
	//Gibt nur die Kombinationen mit dem großten Wert zurück
	public ArrayList<int[]> getCombosMitGroesstemWert(ArrayList<int[]> combos) {
		ArrayList<int[]> bestePreferenzen = new ArrayList<int[]>();
		for (int[] n: combos) {
			if ( n[0] == (combos.get(0))[0] ) {
				bestePreferenzen.add(n);
	}}
		// zu Löschen  Anfang
	     for (int[] combo : bestePreferenzen) {
				System.out.println("Beste Preferenzen nach Wert: " + farben.get(combo[1]) + " " + farben.get(combo[2]) + " Wert " + combo[0]);
			} 
	     // zu Löschen Ende
		return bestePreferenzen;}

	// Sortiert Kombinationen: erst die Kombos mit 2 Lieblingsfarben, dann mit einer und anschließend die übrigen.  Rückgabe ArrayList<int[3]>
	public ArrayList<int[]> sortiereNachLieblingsfarben(ArrayList<int[]> combos) {
		ArrayList<int[]> farbenSortiertNachLieblingsfarben = new ArrayList<int[]>();
		farbenSortiertNachLieblingsfarben.clear();
		for (int[] farbenkombo : combos) {
			if (lieblingsFarben.contains(farben.get(farbenkombo[1])) && lieblingsFarben.contains(farbenkombo[2])) {
				farbenSortiertNachLieblingsfarben.add(farbenkombo);
				System.out.println("LLLLLLLLLLLLLLLLl           "+ Arrays.toString(farbenkombo));
			}
		}
		if (!farbenSortiertNachLieblingsfarben.isEmpty()) {
			
			return farbenSortiertNachLieblingsfarben;
		} else {
		for (int[] farbenkombo : combos) {
			if (lieblingsFarben.contains(farbenkombo[1]) || lieblingsFarben.contains(farbenkombo[2]) ) {
				farbenSortiertNachLieblingsfarben.add(farbenkombo);
			}
			}
		if (!farbenSortiertNachLieblingsfarben.isEmpty()) {
			System.out.println("LLLLLLLLLLLLLLLLl           "+ Arrays.deepToString(farbenSortiertNachLieblingsfarben.toArray()));
			return farbenSortiertNachLieblingsfarben;
		} else {
		for (int[] farbenkombo : combos) {
			if (!lieblingsFarben.contains(farbenkombo[1]) && !lieblingsFarben.contains(farbenkombo[2])) {
				farbenSortiertNachLieblingsfarben.add(farbenkombo);
			}
		}
		System.out.println("LLLLLLLLLLLLLLLLl           "+ Arrays.deepToString(farbenSortiertNachLieblingsfarben.toArray()));
		return farbenSortiertNachLieblingsfarben;}}
	}
	
	//Sortiert preferenzen nach Wert; gibt ausgewaehlteKomboNachWert zurück
	public String[] gibBesteKomboMitGroestemWert() {
		sortiereNachWert(preferenzen);
		 ausgewaehlteKomboNachWert[0]=  farben.get(  (sortiereNachLieblingsfarben(   getCombosMitGroesstemWert(preferenzen)   )).get(0)[1]    ) ;
		 ausgewaehlteKomboNachWert[1]=  farben.get(  (sortiereNachLieblingsfarben(   getCombosMitGroesstemWert(preferenzen)   )).get(0)[2]    ) ;
		 System.out.println("ausgewaehlteKomboNachWert:     "+Arrays.toString(ausgewaehlteKomboNachWert));
		return  ausgewaehlteKomboNachWert ;
	}
	
	public String[] gibBesteKomboMitMeistenLieblingsfarben() {
		ausgewaehlteKomboNachLieblingsfarben[0]= (convertInString(    getCombosMitGroesstemWert(  sortiereNachLieblingsfarben(preferenzen)  )   )     ).get(0)[0];
		ausgewaehlteKomboNachLieblingsfarben[1]= (convertInString( getCombosMitGroesstemWert(  sortiereNachLieblingsfarben(preferenzen)))).get(0)[1];
		ausgewaehlteKomboNachLieblingsfarben = convertInString( getCombosMitGroesstemWert(  sortiereNachLieblingsfarben(preferenzen))).get(0);
		System.out.println("ausgewaehlteKomboNachLieblingsfarben:       " + Arrays.deepToString(ausgewaehlteKomboNachLieblingsfarben));
		return ausgewaehlteKomboNachLieblingsfarben;
	}
	
	public ArrayList<String[]> convertInString(ArrayList<int[]> listInInt) {
		ArrayList<String[]> listInString = new ArrayList<String[]>();
		for (int[] combo : listInInt) {
		String[] speicher = {farben.get(combo[1]), farben.get(combo[2])};
		listInString.add(speicher);
		}
		return listInString;
	}
	
	
	
	public void gestalte() {
		 Label label1;
		 Label label2;
		 Label label3;
		 Label label4;
		 Label label5;
		 Label label6;
		 Label label7;
		 Label label8;
		 Label label9;
		
	}
	
	// Get-Methode für Farben
	public ObservableList<String> getFarben() {
		return farben;
	}
	
	//Get-Methode für Lieblingsfarben
	public ObservableList<String> getLieblingsFarben() {
		return lieblingsFarben;
	}
	
	//Set-Methode für Lieblingsfarben
     public void setLieblingsFarben(ObservableList<String> lieblingsFarben) {
		this.lieblingsFarben = lieblingsFarben;
	}

     public ArrayList<int[]> getPreferenzen() {
 		return preferenzen;
 	}

 	public void setPreferenzen(ArrayList<int[]> preferenzen) {
 		this.preferenzen = preferenzen;
 	}

	
}
