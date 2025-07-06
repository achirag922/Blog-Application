package com.dev.blog.payloads;

public class CommentDto {
	
	private int id;
	
	private String content;

	public CommentDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
