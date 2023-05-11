package com.priceline.sentimentanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.export.newrelic.NewRelicMetricsExportAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;

@SpringBootApplication(exclude = {
        ValidationAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        CassandraAutoConfiguration.class,
        CassandraDataAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class,
        NewRelicMetricsExportAutoConfiguration.class
})public class MainClass {
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);

    }
}
