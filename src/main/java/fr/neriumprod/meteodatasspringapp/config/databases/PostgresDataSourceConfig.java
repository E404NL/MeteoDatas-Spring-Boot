package fr.neriumprod.meteodatasspringapp.config.databases;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"fr.neriumprod.meteodatasspringapp.dao.postgres"},
                        entityManagerFactoryRef = "postgresEntityManagerFactory",
                        transactionManagerRef = "postgresTransationManager")
public class PostgresDataSourceConfig {

    @Bean(name = "postgresDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("postgresDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("fr.neriumprod.meteodatasspringapp.entities.postgres");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean(name = "postgresTransactionManager")
    @Primary
    public JpaTransactionManager transactionManager(
            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory postgresEntityManagerFactory
    ) {
        return new JpaTransactionManager(postgresEntityManagerFactory);
    }
}
