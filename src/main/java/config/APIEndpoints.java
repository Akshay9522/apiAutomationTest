package config;

public enum APIEndpoints {
	
	    USERS("/users"),
	    USER_BY_ID("/users/{id}"),
	    CREATE_USER("/users"),
	    UPDATE_USER("/users/{id}"),
	    DELETE_USER("/users/{id}"),
	    AUTH_TOKEN("/auth/token"),
	    HEALTH_CHECK("/health");

	    private final String path;

	    APIEndpoints(String path) {
	        this.path = path;
	    }

	    public String getPath() {
	        return path;
	    }
	    public String getPathWithParam(String param, String value) {
	        return path.replace("{" + param + "}", value);
	    }
}
