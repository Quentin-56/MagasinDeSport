package main;

import controlleur.Connexion;
import controlleur.MagasinControlleur;
import controlleur.SetupEM;
import modele.Magasin;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello buddy");
        new SetupEM();
        MagasinControlleur.creerRayon("Tennis");
    }
}
