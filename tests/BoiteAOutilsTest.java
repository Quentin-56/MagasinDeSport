import controlleur.BoiteAOutil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoiteAOutilsTest {


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
        String [] str ={"Antoine cARL","aa BB", "anToiNe BlAbLa", "antoine blabla","AA"};
        String [] strResult ={"Antoine Carl","Aa Bb","Antoine Blabla","Antoine Blabla","Aa"};
        for(int i =0 ; i<str.length; i++){
            assertEquals(BoiteAOutil.formaterCasse(str[i]), strResult[i]);
        }

    }
}