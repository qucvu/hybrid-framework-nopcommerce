package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:enviromentConfig/${env}.properties"})
public interface Enviroment extends Config {
	String url();
	
	@Key("DB.username")
	String userNameDB();
	
	@Key("DB.password")
	String passwordDB();
	
	
	@Key("DB.host")
	String hostDB();
}
