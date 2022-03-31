package qa.demo.configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")
public interface CredentialsConfig extends Config {
    String user();
    String password();

    @DefaultValue("selenoid.autotests.cloud/wd/hub/")
    String selenoidUrl();
}
