/**
 * 
 */
package com.mindtree.cms.pojo;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sabin
 *
 */
public class Blog {

	@NotEmpty(message = "Please enter Blog Tittle")
	@Size(min = 3, max = 20, message = "Blog must between 3 and 20 characters")
	private String tittle;
	@NotEmpty(message = "Please enter Blog Content")
	@Size(min = 3, max = 20000, message = "Blog must between 3 and 20000 characters")
	private String content;

	private MultipartFile file;
	
	private List<Comment> comments;

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	private String fileBase64;
	private int blogId;

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
