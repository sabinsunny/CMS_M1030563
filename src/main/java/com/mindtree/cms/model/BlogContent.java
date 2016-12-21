/**
 * 
 */
package com.mindtree.cms.model;

import java.util.Set;

import javax.persistence.*;

/**
 * @author M1030563
 *
 */
@Entity
@Table(name = "BLOG")
public class BlogContent {
	@Id
	@GeneratedValue
	@Column(name = "blog_id")
	private int blogId;

	@Column(name = "tittle")
	private String tittle;

	@Lob
	@Column(name = "CONTENT", length = 512)
	private String content;

	@Lob
	@Column(name = "FILEIMAGE", length = 100000)
	private byte[] file;

	
	
	@OneToMany(mappedBy="blogContent")
	private Set<BlogComments> blogComments;
	
	public BlogContent() {
	}

	public Set<BlogComments> getBlogComments() {
		return blogComments;
	}

	public void setBlogComments(Set<BlogComments> blogComments) {
		this.blogComments = blogComments;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}


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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}