package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface ConfigLoader extends Config  {
    String login();
    String password();
    String browserUrl();
}
