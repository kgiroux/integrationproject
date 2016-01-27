package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PropositionDAOImpl
 * Implï¿½mentation des mï¿½thodes de l'interface PropositionDAO
 * pour les liens avec la base de donnï¿½es
 * */


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esigelec.quiz.controleur.TestLogger;
import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class PropositionDAOImpl implements IPropositionDAO {
	
	private static final Logger logger = Logger.getLogger(TestLogger.class);

	@Override
	public boolean createProposition(Proposition p) {
		
		logger.info("createProposition proposition");
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		logger.info("createProposition : " + p.toString());
		Cache.viderCacheListeQuiz();
		return (p.getId() != 0);
	}

	@Override
	public Proposition getProposition(int id) {
		
		logger.info("getProposition id");
		
		
		//si cache à jour on cherche dans le cache
		if(Cache.getCacheListeQuiz()!=null){
			
					for(Quiz q:Cache.getCacheListeQuiz())
						for(Question question:q.getListeQuestions())
							for(Proposition proposition:question.getListePropositions())
								if(proposition.getId()==id)
									return proposition;
							
					//proposition pas trouvé
					return null;
		
		}

		
		
		
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Proposition retour = (Proposition) session.get(Proposition.class, id);
		session.getTransaction().commit();
		session.close();
		logger.info("get Proposition: " + retour.toString() + " From id : " + id);
		return retour;
	}

	public List<Proposition> listProposition() {
		
		
		logger.info("listProposition");
		
		
		List<Proposition> retour=null;
		//si cache à jour on cherche dans le cache
				if(Cache.getCacheListeQuiz()!=null){
							retour=new ArrayList<Proposition>();
							for(Quiz q:Cache.getCacheListeQuiz())
								for(Question question:q.getListeQuestions())
									for(Proposition proposition:question.getListePropositions())
										retour.add(proposition);
											
									
							
							return retour;
				
				}

		
		
		
		
		
		
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Proposition";
	
		 retour = (List<Proposition>) session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		logger.info("get listProposition: " + retour.toString());
		return retour;
	}

	@Override
	public boolean updateProposition(Proposition p) {
		
		
		logger.info("updateProposition proposition");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(p);
		Proposition newPro = getProposition(p.getId());
		session.getTransaction().commit();
		session.close();
		logger.info("updateProposition: " + newPro.toString());
		Cache.viderCacheListeQuiz();
		return (p.equals(newPro));
	}

	@Override
	public boolean deleteProposition(Proposition p) {
		
		logger.info("deleteProposition proposition");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
		logger.info("deleteProposition: " + p);
		Cache.viderCacheListeQuiz();
		return (p == null);
	}

	@Override
	public Proposition getBonneReponse(Question q) {
		
		logger.info("getBonneReponse question");
		
		
		//si cache à jour on cherche dans le cache
		if(Cache.getCacheListeQuiz()!=null){
			
				Question questionConcernee=null;
					//recherche de la question
					for(Quiz quiz:Cache.getCacheListeQuiz())
						for(Question question:quiz.getListeQuestions())
							if(question.getId()==q.getId()){
								questionConcernee=question;
									break;
							}
				
									
									
				for(Proposition proposition:questionConcernee.getListePropositions())
					if(proposition.isEstBonneReponse())
							return proposition;
									
							
				return null;
		
		}
		
			
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "From Proposition where estBonneReponse = 1 AND id_question = " + q.getId();
		Proposition retour = (Proposition) session.createQuery(hql).uniqueResult();
		/*Proposition retour = (Proposition) session.createCriteria(Proposition.class)
				.add(Restrictions.eq("id_question", q.getId()))
				.add(Restrictions.eq("estBonneReponse", 1))
				.uniqueResult();*/
		session.getTransaction().commit();
		session.close();
		logger.info("getBonneReponse : " + retour.toString() + " From question" + q.toString());
		return retour;
	}
}
