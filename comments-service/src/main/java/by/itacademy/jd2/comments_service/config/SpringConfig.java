package by.itacademy.jd2.comments_service.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("by.itacademy.jd2")
@PropertySources({
        @PropertySource("classpath:db.properties"),
        @PropertySource("classpath:hibernate.properties"),
        @PropertySource("classpath:root.properties")})
@EnableJpaRepositories(basePackages = "by.itacademy.jd2.dao.api")
@EnableTransactionManagement
public class SpringConfig {
    private static final String DEFAULT_SCHEMA = "hibernate.default_schema";
    private static final String SHOW_SQL = "hibernate.show_sql";
    private static final String HBM2DDL = "hibernate.hbm2ddl.auto";
    private final Environment env;

    public SpringConfig(Environment env) {
        this.env = env;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(DEFAULT_SCHEMA, env.getProperty(DEFAULT_SCHEMA));
        properties.setProperty(SHOW_SQL, env.getProperty(SHOW_SQL));
        properties.setProperty(HBM2DDL, env.getProperty(HBM2DDL));

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            dataSource.setDriverClass(env.getProperty("db.driver"));
            dataSource.setJdbcUrl(env.getProperty("db.url"));
            dataSource.setUser(env.getProperty("root.user"));
            dataSource.setPassword(env.getProperty("root.password"));

            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException("Unable to create DataSource", e);
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();

        txManager.setEntityManagerFactory(entityManagerFactory);

        return txManager;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        vendorAdapter.setGenerateDdl(true);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("by.itacademy.jd2.dao.entity");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();

        return factory;
    }
}
