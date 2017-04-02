package com.yeild.ssh.appconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.yeild.ssh.service","com.yeild.ssh.doamin","com.yeild.ssh.domain.dao"})
@Import({BaseHibernateConfig.class})
public class BaseWebAppConfig {

}
