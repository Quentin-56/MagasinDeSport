import controlleur.ConnexionDAO;
import controlleur.SetupEM;
import modele.ChefMagasin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnexionDAOTest {

    @Mock
    EntityManager entityManagerMock;
    @Mock
    SetupEM setupEMMock;
    @Mock
    private Query queryMock;

    @BeforeEach
    public void setup(){
        System.out.println("Hello");
       MockitoAnnotations.initMocks(this);
    }

    @Test
    public void lechefseconnecteTest(){
        System.out.println("Debut du test");
        when(setupEMMock.getEm()).thenReturn(entityManagerMock);

        when(entityManagerMock.createQuery("from ChefMagasin chef where chef.identifiant =  ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"Id")).thenReturn(queryMock);

        ChefMagasin chef = new ChefMagasin();
        when(queryMock.getSingleResult()).thenReturn(null);

        ConnexionDAO con = new ConnexionDAO();
        con.setEm(entityManagerMock);
        assertEquals(con.leChefSeConnecte("Id"),true);
    }
}