package br.com.gm5.loja.repositories;


import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gm5.loja.models.Loja;

@Repository
public class LojaDAO {

	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
	
	public List<Loja> findByName(String nome) {		
		getSession().beginTransaction();
		List<Loja> lojas = getSession().createQuery("from loja where nome_loja like '%"+nome+"%'").getResultList();
		getSession().getTransaction().commit();
		return lojas;
	}
}
