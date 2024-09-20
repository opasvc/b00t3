package com.dztzb00t3.j2t;

import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import java.net.UnknownHostException;
import org.springframework.core.env.Environment;
import org.springframework.boot.SpringApplication;
import com.dztzb003.j2t.common.utils.DefaultProfileUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * 启动类
 *
 * @author dztz
 * @date 2024/09/12
 */
@Slf4j
@SpringBootApplication
public class DztzB00t3Application {

    private static final String SERVER_SSL_KEY_STORE = "server.ssl.key-store";

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(DztzB00t3Application.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty(SERVER_SSL_KEY_STORE) != null) {
            protocol = "https";
        }
        log.info("""

                        ----------------------------------------------------------
                        \t\
                        Application '{}' is running! Access URLs:
                        \t\
                        Local: \t\t{}://localhost:{}
                        \t\
                        External: \t{}://{}:{}
                        \t\
                        Profile(s): \t{}
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}
