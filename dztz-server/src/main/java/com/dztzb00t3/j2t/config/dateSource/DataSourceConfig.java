package com.dztzb00t3.j2t.config.dateSource;

import lombok.Data;
import javax.sql.DataSource;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * datasource config
 *
 * @author dztz
 * @date 2024/09/20
 */
@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    private String url;
    private String username;
    private String password;
    private String driverclassname;

    @Bean(name = "dataSource")         //数据源配置
    @Primary
    public DataSource dataSource() {
        return new PooledDataSource(driverclassname
                , url
                , username
                , password);
    }

}
