import controlleur.ChefMagasinDAO;
import modele.ChefMagasin;
import modele.Magasin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ChefMagasinDAOTest {
    @Mock
    EntityManager entityManagerMock;
    @Mock
    private Query queryMock;
    @Mock
    EntityTransaction transaction;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void modifier_un_chef_de_magasin_test(){
        ChefMagasin chefmagasin = new ChefMagasin();
        ChefMagasin chefModifie = chefmagasin;
        chefModifie.setMotDePasse("mdp");
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        when(entityManagerMock.merge(chefmagasin)).thenReturn(chefModifie);

        ChefMagasinDAO dao = new ChefMagasinDAO();
        dao.setEntityManager(entityManagerMock);
        dao.modifierChefMagasin(chefModifie);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).merge(chefModifie);

    }
    @Test
    public void recuperer_un_chef_de_magasin_test(){
        ChefMagasinDAO dao = new ChefMagasinDAO();
        ChefMagasin chefmagasin = new ChefMagasin();
        Magasin magasin = new Magasin();

        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        when(entityManagerMock.createQuery("from ChefMagasin")).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(chefmagasin);
        dao.setEntityManager(entityManagerMock);


        assertEquals(dao.recupererChefMagasin(), chefmagasin);
        assertEquals(magasin.getChefMagasin(), chefmagasin);
        verify(transaction).begin();
        verify(transaction).commit();

    }
}
