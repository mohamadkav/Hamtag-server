package net.hamtag.server.api.response.types.category;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {
	@JsonProperty
	private String name;
	
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private Object image;
	
	@JsonProperty
	private String translation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getImage() {
		return image;
	}

	public void setImage(Object image) {
		this.image = image;
	}
	
	
}
