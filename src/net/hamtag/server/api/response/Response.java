package net.hamtag.server.api.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
	
	@JsonProperty("success")
    private boolean success = true;

    @JsonProperty("original_response")
    private java.lang.Object originalResponse = null;

    @JsonProperty("error")
    private Enum<?> error = null;
    
    public Response() {}

    public Response(Object originalResponse) {
        setOriginalResponse(originalResponse);
    }
    
    public Response(Enum<?> error) {
        setSuccess(false);
        setError(error);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getOriginalResponse() {
        return originalResponse;
    }

    public void setOriginalResponse(Object originalResponse) {
        this.originalResponse = originalResponse;
    }

    public Enum<?> getError() {
        return error;
    }

    public void setError(Enum<?> error) {
        this.error = error;
    }
    
    @JsonIgnore
    @Override
    public String toString() {
    	ObjectMapper mapper = new ObjectMapper();
    	String json = null;
		try {
			json = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return json;
    	
    }

	public javax.ws.rs.core.Response crossOriginResponse() {
		return javax.ws.rs.core.Response.ok(this).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	
	@JsonProperty("response_type")
	private String type = "normal_response";
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}
