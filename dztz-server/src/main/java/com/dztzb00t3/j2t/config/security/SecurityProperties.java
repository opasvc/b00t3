package com.dztzb00t3.j2t.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * security config properties
 *
 * @author dztz
 * @date 2024/09/13
 */
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
}