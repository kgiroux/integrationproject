package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.esigelec.quiz.dto.Personne;

//On regle la classe pour que les classes s'executent par odre alphabetique
@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class PersonneDAOImplTest {
	
	
	static Personne personne;
	PersonneDAOImpl daoPers;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personne = new Personne("Esigelec", "IR", "IR@esigelec.fr", "Esigelec", Personne.ADMIN);
	}

	@Before
	public void setUp() throws Exception {
		daoPers = new PersonneDAOImpl();
	}

	@Test
	public void AtestCreatePersonne() {
		boolean statut = daoPers.createPersonne(personne);
		System.out.println("createPersonne, Requete réussi: " + statut);
		System.out.println("Personne info = " + personne.toString());
		assertEquals(true, statut);
	}
	
	@Test
	public void BtestGetPersonneInt() {
		System.out.println("id de base : " + personne.getId());
		Personne p = daoPers.getPersonne(personne.getId());
		System.out.println("id recup dans la base : " + p.getId());
		assertTrue(p.equals(personne));
	}
	

	@Test
	public void CtestGetPersonneString() {
		System.out.println("email de base : " + personne.getMail());
		Personne p = daoPers.getPersonne(personne.getMail());
		System.out.println("email recup dans la base : " + p.getMail());
		assertTrue(p.equals(personne));
	}
	
	@Test
	public void DtestUpdatePersonne() {
		personne.setDroits(Personne.JOUEUR);
		System.out.println("Droit Personne: " + personne.getDroits());
		boolean statut = daoPers.updatePersonne(personne);
		System.out.println("updatePersonne, Requete réussi: " + statut);
		Personne p = daoPers.getPersonne(personne.getId());
		assertEquals(p, personne);
	}
	
	@Test(expected=NullPointerException.class)
	public void EtestDeletePersonne() {
		int id = personne.getId();
		boolean statut = daoPers.deletePersonne(personne);
		System.out.println("deletePersonne, Requete réussi: " + statut);
		System.out.println("Personne id = " + personne.getId());
		Personne q = daoPers.getPersonne(id);
		System.out.println(q.toString());
	}
	
	@Test
	public void FtestListPersonnes() {
		Personne a = new Personne("a", "a", "a", "a", Personne.ADMIN);
		daoPers.createPersonne(a);
		Personne b = new Personne("b", "b", "b", "b", Personne.ADMIN);
		daoPers.createPersonne(b);
		List<Personne> listeOne = daoPers.listPersonnes();
		Personne c = new Personne("c", "c", "c", "c", Personne.ADMIN);
		listeOne.add(c);
		daoPers.createPersonne(c);
		List<Personne> listeTwo = daoPers.listPersonnes();
		assertEquals(listeOne, listeTwo);
		daoPers.deletePersonne(a);
		daoPers.deletePersonne(b);
		daoPers.deletePersonne(c);
	}
	
	
	@Test
	public void GtestConnexion() {
		Personne a = new Personne("a", "a", "mail", "mdp", Personne.ADMIN);
		daoPers.createPersonne(a);
		Personne connecte = daoPers.connexion("mail", "mdp");
		assertEquals(connecte, a);
		daoPers.deletePersonne(a);
	}

}
