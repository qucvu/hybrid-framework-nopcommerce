package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class UserDataMapper {
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("dob")
	private Dob dob;
	
	@JsonProperty("subjects")
	private List<Subject> subjects;
	
	public List<Subject> getSubject(){
		return subjects;
	}
	
	public Dob getDob(){
		return dob;
	}


	public static UserDataMapper getUserDataMapper() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			File userJsonFile = new File(GlobalConstants.PROJECT_PATH + "resources/UserData.json");
			return objectMapper.readValue(userJsonFile, UserDataMapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static class Dob {
		@JsonProperty("day")
		private String day;
		@JsonProperty("month")
		private String month;
		@JsonProperty("year")
		private String year;		
		public String getDay() {
			return day;
		}
		public String getMonth() {
			return month;
		}
		public String getYear() {
			return year;
		}

	}
	
	
	static class Subject {
		@JsonProperty("math")
		private String math;
		@JsonProperty("physic")
		private String physic;
		
		public String getMath() {
			return math;
		}
		public String getPhysic() {
			return physic;
		}

	}

	public String getFirstName() {
		return firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	
	// case for get array json
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//        File usersJsonfile = new File(GlobalConstants.PROJECT_PATH + "resources/UserData.json");
//		ObjectMapper objectMapper = new ObjectMapper();
//        List<UserDataMapper> userData = objectMapper.readValue(usersJsonfile, new TypeReference<List<UserDataMapper>>() {});
//        for (UserDataMapper userDataMapper : userData) {
//			System.out.println(userDataMapper.getlastName());
//		}
        
        System.out.println(UserDataMapper.getUserDataMapper().subjects.get(1).getMath());
	}

}
