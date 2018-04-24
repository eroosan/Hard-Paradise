package urjc.hardParadise;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class HardParadiseApplication {
	
	private static final Log LOG = LogFactory.getLog(HardParadiseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HardParadiseApplication.class, args);
	}
	
	@Bean
	public CacheManager cacheManager()
	{
		LOG.info("Activating cache...");
		return new ConcurrentMapCacheManager("test");
	}
	@Bean
	 public Config config() {
		
		 Config config = new Config();
		 JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		 joinConfig.getMulticastConfig().setEnabled(false);
		 joinConfig.getTcpIpConfig().setEnabled(true).addMember("192.168.33.11").addMember("192.168.33.12");
		 return config;
	 }
}
