package main;

import controlleur.Connexion;
import controlleur.SetupEM;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello buddy");
        new SetupEM();
        System.out.println(Connexion.verifierMotDePasse("chef", "chef"));
    }
}
