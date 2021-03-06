package top.shaozuo.geektime.java01.week07.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import top.shaozuo.geektime.java01.week07.aspect.DynamicDataSourceContext;

/**
 * 
 * @author shaozuo
 *
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dynamicDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        AbstractRoutingDataSource dataSource = new AbstractRoutingDataSource() {

            @Override
            protected Object determineCurrentLookupKey() {
                return DynamicDataSourceContext.getDataSource();
            }
        };

        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave", slaveDataSource);

        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }

}