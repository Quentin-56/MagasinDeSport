import controlleur.VendeurDAO;
import modele.Article;
import modele.Rayon;
import modele.Vendeur;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class VendeurDAOTest {
    @Mock
    EntityManager entityManagerMock;

    @Mock
    EntityTransaction transaction;
    @Mock
    Query queryMock;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void creer_un_vendeur_test(){
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation){
                Vendeur vendeur = (Vendeur) invocation.getArguments()[0];
                vendeur.setIdPersonne(1);
                return vendeur;
            }
        }).when(entityManagerMock).persist(any(Vendeur.class));
        VendeurDAO dao = new VendeurDAO();
        dao.setEntityManager(entityManagerMock);

        Vendeur vendeur = dao.creerVendeur("","","","",new Rayon());
        vendeur.setIdPersonne(1);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).persist(vendeur);
    }
    @Test
    public void supprimer_un_vendeur(){
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        Rayon rayon = new Rayon();
        VendeurDAO dao = new VendeurDAO();
        dao.setEntityManager(entityManagerMock);
        int idVendeurASupprimer= 1;
        Vendeur vendeur = dao.creerVendeur("","","","",rayon);
        vendeur.setIdPersonne(1);
        when(entityManagerMock.find(Vendeur.class, idVendeurASupprimer)).thenReturn(vendeur);
        doNothing().when(entityManagerMock).remove(vendeur);
        dao.supprimerVendeur(idVendeurASupprimer, rayon);
        verify(entityManagerMock).remove(vendeur);
    }
    @Test
    public void modifier_vendeur(){

        Vendeur vendeur = new Vendeur();
        Vendeur vendeurModifie = vendeur;
        vendeurModifie.setNom("test");
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        when(entityManagerMock.merge(vendeur)).thenReturn(vendeurModifie);
        VendeurDAO dao = new VendeurDAO();
        dao.setEntityManager(entityManagerMock);
        dao.modifierVendeur(vendeur);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).merge(vendeurModifie);

    }
    @Test
    public void ajouter_vendeur_dans_la_liste_vendeur_test(){
        //Before
        Rayon rayon = new Rayon();
        Vendeur vendeur1 = new Vendeur();
        Vendeur vendeur2 = new Vendeur();
        VendeurDAO dao = new VendeurDAO();
        List<Vendeur> listeVendeurInitial = new ArrayList<>();
        listeVendeurInitial.add(vendeur1);
        rayon.setListeVendeurs(listeVendeurInitial);
        //test
        dao.ajouterVendeurDansListeVendeur(vendeur2,rayon);
        assertEquals(vendeur1,rayon.getListeVendeurs().get(0));
        assertEquals(vendeur2,rayon.getListeVendeurs().get(1));
        //after
        rayon.setListeArticles(null);
    }
    @Test
    public void supprimer_vendeur_dans_la_liste_vendeur(){
        //Before
        Rayon rayon = new Rayon();
        Vendeur vendeur1 = new Vendeur();
        Vendeur vendeur2 = new Vendeur();
        VendeurDAO dao = new VendeurDAO();
        List<Vendeur> listeVendeurInitial = new ArrayList<>();
        listeVendeurInitial.add(vendeur1);
        listeVendeurInitial.add(vendeur2);
        rayon.setListeVendeurs(listeVendeurInitial);
        //test
        dao.supprimerVendeurDansListeVendeur(vendeur2, rayon);
        assertEquals(vendeur1,rayon.getListeVendeurs().get(0));
        try{
            rayon.getListeVendeurs().get(1);
        }catch (IndexOutOfBoundsException e){
            assertEquals("Index 1 out of bounds for length 1",e.getMessage());
        }
        //after
        rayon.setListeArticles(null);
    }
    @Test
    public void recuperer_vendeur(){
        VendeurDAO dao = new VendeurDAO();
        Rayon rayon = new Rayon();
        Vendeur vendeur1 = new Vendeur();
        Vendeur vendeur2 = new Vendeur();
        List<Vendeur> listeTest = List.of(vendeur1, vendeur2);

        when(entityManagerMock.getTransaction()).thenReturn(transaction);

        when(entityManagerMock.createQuery("from Vendeur vendeur")).thenReturn(queryMock);
        when(queryMock.setParameter(1,rayon)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(listeTest);
        dao.setEntityManager(entityManagerMock);
        assertArrayEquals(listeTest.toArray(), dao.recupererVendeurs().toArray());
        verify(transaction).begin();
        verify(transaction).commit();


    }
}
