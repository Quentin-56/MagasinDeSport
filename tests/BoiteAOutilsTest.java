import controleur.BoiteAOutil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoiteAOutilsTest {

    @Test
    public void estUnNombreValableEntierTest() {
        String [] str = {"14", "-6", "5.60","0","-1.5"};
        Boolean [] strResult = {true, false, false,true,false};
        for(int i =0 ; i<str.length; i++){
            assertEquals(BoiteAOutil.estUnNombreValableEntier(str[i]), strResult[i]);
        }
    }

    @Test
    public void estUnNombreValableTest() {
        String [] str = {"14", "-6", "5.60","0","-1.5"};
        Boolean [] strResult = {true, false, true,true,false};
        for(int i =0 ; i<str.length; i++){
            assertEquals(BoiteAOutil.estUnNombreValable(str[i]), strResult[i]);
        }
    }

    @Test
    public void supprimerEspaceTest() {
        String [] str = {"aa( )", " aze ", " az","a&a ","aa((    ))&aa","a"};
        String [] strResult = {"aa( )", "aze", "az","a&a","aa((    ))&aa","a"};

        for(int i =0 ; i<str.length; i++){
            assertEquals(BoiteAOutil.supprimerEspace(str[i]), strResult[i]);
        }
    }

    @Test
    public void formaterCasseTest() {
        String [] str ={"BALLON","chaussure", "cHaUssUre", "BalLon","VeLO"};
        String [] strResult ={"Ballon","Chaussure","Chaussure","Ballon","Velo"};
        for(int i =0 ; i<str.length; i++){
            assertEquals(BoiteAOutil.formaterCasse(str[i]), strResult[i]);
        }

    }
    @Test
    public void le_formatage_dun_mdp_est_incorrect(){
        String [] str ={"PASSWORD","PASS3","password2", "333333","password", "Password"};
        for (int i= 0; i< str.length; i++){
            assertEquals(BoiteAOutil.checkString(str[i]), false);
        }
    }
    @Test
    public void le_formatage_dun_mdp_est_correct(){
        assertEquals(BoiteAOutil.checkString("PaSsWoRd75"), true);
    }
    @Test
    public void la_chaine_comporte_un_caractere_special(){
        String [] str ={"ovdtezGYF$","//debuzfg","@t"};
        for (int i= 0; i< str.length; i++){
            assertEquals(BoiteAOutil.checkCaractereSpeciaux(str[i]), true);
        }
    }
    @Test
    public void la_chaine_ne_comporte_pas_un_caractere_special(){
        assertEquals(BoiteAOutil.checkCaractereSpeciaux("dvoeyfdoetyFTOEFYHDvyfd"), false);
    }
}