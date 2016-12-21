/**
 * 
 */
package com.mindtree.cms.dao;

import java.util.List;

import com.mindtree.cms.model.BlogComments;
import com.mindtree.cms.model.BlogContent;

/**
 * @author M1030563
 *
 */
public interface BlogDAO {
	public void addBlog(BlogContent blog)throws Exception;
	public void updateBlog(BlogContent blog)throws Exception;
	public List<BlogContent> listBlogs()throws Exception;
	public BlogContent getBlogById(int id)throws Exception;
	public void removeBlog(int blogId)throws Exception;
	public void saveComment(BlogComments comment)throws Exception;
}
