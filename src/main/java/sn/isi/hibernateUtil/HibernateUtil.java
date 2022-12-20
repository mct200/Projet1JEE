package sn.isi.hibernateUtil;

import java.util.Properties;

import javax.imageio.spi.ServiceRegistry;
import javax.security.auth.login.Configuration;

import org.omg.CORBA.Environment;

import sn.isi.entities.Comptes;
import sn.isi.entities.Droits;


public class HibernateUtil {
	 private static SessionFactory sessionFactory;
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER,
	                        "com.mysql.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/scolarite-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateti meCode=false&serverTimezone=UTC");
	                        settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "");
	                settings.put(Environment.DIALECT,
	                        "org.hibernate.dialect.MySQL5Dialect");
	                //cette ligne est très importante
	                settings.put(Environment.HBM2DDL_AUTO,
	                        "update");
	                settings.put(Environment.SHOW_SQL, "true");
	                settings.put(Environment.FORMAT_SQL, "true");
	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,
	                        "thread");
	                configuration.setProperties(settings);
	              
	                configuration.addAnnotatedClass(Comptes.class);
	                configuration.addAnnotatedClass(Droits.class);

	                ServiceRegistry serviceRegistry = new
	                        StandardServiceRegistryBuilder()
	                        .applySettings(configuration.getProperties()).build();
	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	                return sessionFactory;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }

}
