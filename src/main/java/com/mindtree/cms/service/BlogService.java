/**
 * 
 */
package com.mindtree.cms.service;

import java.util.List;

import com.mindtree.cms.model.BlogContent;
import com.mindtree.cms.pojo.Blog;
import com.mindtree.cms.pojo.Comment;

/**
 * @author M1030563
 *
 */
public interface BlogService {
	public void addBlog(BlogContent blog)throws Exception ;
	public void updateBlog(BlogContent blog)throws Exception ;
	public List<Blog> listBlogs()throws Exception ;
	public Blog getBlogById(int blogId)throws Exception ;
	public void removeBlog(int blogId)throws Exception ;
	public void saveBlogComment(Comment blogComment)throws Exception ;
	public List<Comment> findBlogComments(int blogId)throws Exception ;
}
