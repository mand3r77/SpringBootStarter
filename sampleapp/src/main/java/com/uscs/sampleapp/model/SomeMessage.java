package com.uscs.sampleapp.model;

import java.util.Date;

public class SomeMessage<T> {

	private String id;

	private String payload;
	
	private Date createDt = new Date();

	public SomeMessage() {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Builder getBuilder() {
		return new Builder();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public static class Builder<T> {

		private SomeMessage<T> built;

		public Builder() {
			built = new SomeMessage<T>();
			built.setCreateDt(new Date());
		}

		public Builder<T> id(String id) {
			built.setId(id);
			return this;
		}
		
		public Builder<T> payload(String payload) {
			built.setPayload(payload);
			return this;
		}
		
		public Builder<T> createDt(Date createDt) {
			built.setCreateDt(createDt);
			return this;
		}

		public SomeMessage<T> build() {
			return built;
		}

	}

}
