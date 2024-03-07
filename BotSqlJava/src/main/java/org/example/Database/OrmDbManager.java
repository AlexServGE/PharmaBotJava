package org.example.Database;

import org.example.Bot.Logging.BotLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrmDbManager {

  private static final String LOGTAG = "DATABASEMANAGER";

  static final SessionFactory sessionFactory = new Configuration()
          .configure("hibernate.cfg.xml")
          .addAnnotatedClass(Tender.class)
          .buildSessionFactory();

  public OrmDbManager() {
  }

  public Session getSession() {
    return sessionFactory.openSession();
  }

  public void sessionFactoryClose() {
    sessionFactory.close();
  }

  public List<Tender> selectAllFromDBProcurements(Session curSession, String medicineCategory, String tenderFederalRegion, String dateStart, String dateEnd) {
    List<Tender> tenders = null;
    try {
      tenders = curSession.createQuery("FROM Tender WHERE medicineCategory = :medicineCategory " +
                      "AND tenderFederalRegion = :tenderFederalRegion " +
                      "AND tenderDate BETWEEN :dateStart AND :dateEnd", Tender.class)
              .setParameter("medicineCategory", medicineCategory)
              .setParameter("tenderFederalRegion", tenderFederalRegion)
              .setParameter("dateStart", dateStart)
              .setParameter("dateEnd", dateEnd)
              .getResultList();
    } catch (Exception e) {
      BotLogger.error(LOGTAG, e);
    }
    return tenders;
  }
}
