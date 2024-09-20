package com.dztzb003.j2t.common.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目启动时输出访问ip+端口
 *
 * @author dztz
 * @date 2024/09/12
 */
public class DefaultProfileUtil {



    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";



    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";



    private DefaultProfileUtil() {
    }




    /**
     * 设置在未配置配置文件时使用的默认值。
     *
     * @param app the Spring application
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        /*
         * 当没有定义其他配置文件时使用的默认配置文件
         * 无法在 <code>application.yml</code> 文件中设置。
         * 请参阅 https://github.com/spring-projects/spring-boot/issues/1219
         */
        defProperties.put(SPRING_PROFILE_DEFAULT, SPRING_PROFILE_DEVELOPMENT);
        app.setDefaultProperties(defProperties);
    }




    /**
     * 获取已应用的配置文件，否则获取默认配置文件。
     *
     * @param env spring环境
     * @return profiles
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }




}
