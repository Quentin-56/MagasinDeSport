import controlleur.ConnexionDAO;
import modele.ChefMagasin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnexionDAOTest {

    @Mock
    EntityManager entityManagerMock;
    @Mock
    private Query queryMock;

    @BeforeEach
    public void setup(){
       MockitoAnnotations.initMocks(this);
    }

    @Test
    public void devrait_etre_un_chef_qui_se_connecte(){
        when(entityManagerMock.createQuery("from ChefMagasin chef where chef.identifiant =  ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"Id")).thenReturn(queryMock);

        ChefMagasin chef = new ChefMagasin();
        when(queryMock.getSingleResult()).thenReturn(chef);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);
        assertEquals(con.leChefSeConnecte("Id"),true);
    }

    @Test
    public void devrait_etre_un_vendeur_qui_se_connecte(){
        when(entityManagerMock.createQuery("from ChefMagasin chef where chef.identifiant =  ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"Id")).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(null);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);
        assertEquals(con.leChefSeConnecte("Id"),false);
    }
}