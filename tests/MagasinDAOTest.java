import controlleur.MagasinDAO;
import controlleur.RayonDAO;
import modele.Article;
import modele.Magasin;
import modele.Rayon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class MagasinDAOTest {
    @Mock
    EntityManager entityManagerMock;

    @Mock
    EntityTransaction transaction;

    @Mock
    Query queryMock;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Magasin magasin = new Magasin();
    }

    @Test
    public void rayon_ajouter_au_magasin()
    {
        when(entityManagerMock.getTransaction()).thenReturn(transaction);

        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation){
                Rayon rayon = (Rayon) invocation.getArguments()[0];
                rayon.setIdRayon(1);
                return rayon;
            }
        }).when(entityManagerMock).persist(any(Rayon.class));
        MagasinDAO dao = new MagasinDAO();
        dao.setEntityManager(entityManagerMock);

        Rayon rayon =dao.creerRayon("");
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).persist(rayon);
    }
    @Test
    public void rayon_supprimer_du_magasin()
    {
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        MagasinDAO dao = new MagasinDAO();
        dao.setEntityManager(entityManagerMock);
        Rayon rayon =dao.creerRayon("");
        int idRayonASupprimer =1;
        rayon.setIdRayon(idRayonASupprimer);
        when(entityManagerMock.find(Rayon.class, idRayonASupprimer)).thenReturn(rayon);
        doNothing().when(entityManagerMock).remove(rayon);
        dao.supprimerRayon(idRayonASupprimer);
        verify(entityManagerMock).remove(rayon);

    }

    @Test
    public void modifier_rayon_du_magasin(){

        Rayon rayon = new Rayon();
        Rayon rayonModifie = rayon;
        rayonModifie.setNom("test");
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        when(entityManagerMock.merge(rayon)).thenReturn(rayonModifie);
        MagasinDAO dao = new MagasinDAO();
        dao.setEntityManager(entityManagerMock);
        dao.modifierRayon(rayon);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).merge(rayonModifie);

    }


    @Test
    public void ajouter_rayon_dans_la_liste_rayon(){
        //Before
        Rayon rayon1 = new Rayon();
        Rayon rayon2 = new Rayon();
        MagasinDAO dao = new MagasinDAO();
        List<Rayon> listeRayonInitial = new ArrayList<>();
        listeRayonInitial.add(rayon1);
        Magasin.setListeRayons((ArrayList<Rayon>) listeRayonInitial);
        //test
        dao.ajouterRayonDansLaListeRayon(rayon2);
        assertEquals(rayon1,Magasin.getListeRayons().get(0));
        assertEquals(rayon2,Magasin.getListeRayons().get(1));
        //after
        Magasin.setListeRayons(null);
    }
    @Test
    public void supprimer_rayon_dans_la_liste_rayon(){
        //Before
        Rayon rayon1 = new Rayon();
        Rayon rayon2 = new Rayon();
        MagasinDAO dao = new MagasinDAO();
        List<Rayon> listeRayonInitial = new ArrayList<>();
        listeRayonInitial.add(rayon1);
        listeRayonInitial.add(rayon2);
        Magasin.setListeRayons((ArrayList<Rayon>) listeRayonInitial);
        //test
        dao.supprimerRayonDansLaListeRayon(rayon2);
        assertEquals(rayon1,Magasin.getListeRayons().get(0));
        try{
            Magasin.getListeRayons().get(1);
        }catch (IndexOutOfBoundsException e){
            assertEquals("Index 1 out of bounds for length 1",e.getMessage());
        }
        //after
        Magasin.setListeRayons(null);
    }

    @Test
    public void recuperer_rayon_du_magasin(){
        MagasinDAO dao = new MagasinDAO();

        Rayon rayon1 = new Rayon();
        Rayon rayon2 = new Rayon();
        List<Rayon> listeTest = new ArrayList<>();
        listeTest.add(rayon1);
        listeTest.add(rayon2);

        when(entityManagerMock.getTransaction()).thenReturn(transaction);

        when(entityManagerMock.createQuery("from Rayon")).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(listeTest);
        dao.setEntityManager(entityManagerMock);
        assertArrayEquals(listeTest.toArray(), dao.recupererRayon().toArray());
        verify(transaction).begin();
        verify(transaction).commit();


    }
}
