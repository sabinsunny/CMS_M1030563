/**
 * 
 */
package com.mindtree.cms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.cms.model.BlogComments;
import com.mindtree.cms.model.BlogContent;

/**
 * @author M1030563
 *
 */
@Repository
public class BlogDAOImpl implements BlogDAO {
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void addBlog(BlogContent blog) {
		this.getSession().save(blog);
	}

	public void updateBlog(BlogContent blog) {
		this.getSession().update(blog);

	}

	public List<BlogContent> listBlogs() throws Exception {
		try {
			Query queryResult = this.getSession().createQuery(
					"from BlogContent");
			@SuppressWarnings("unchecked")
			List<BlogContent> blogList = queryResult.list();
			return blogList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	public BlogContent getBlogById(int id) throws Exception {
		try {
			BlogContent blog = (BlogContent) this.getSession().load(
					BlogContent.class, new Integer(id));
			return blog;
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void removeBlog(int blogId) throws Exception {
		try {
			String queryComment = "delete from BlogComments c where c.blogContent.blogId=" + blogId;
			Query queryResultComment = this.getSession().createQuery(queryComment);
			queryResultComment.executeUpdate();
			String query = "delete from BlogContent b where b.blogId=" + blogId;
			Query queryResult = this.getSession().createQuery(query);
			queryResult.executeUpdate();
		} catch (Exception ex) {
			throw ex;
		}

	}

	@Override
	public void saveComment(BlogComments comment) throws Exception {
		try {
			if (null != comment) {
				this.getSession().save(comment);
			}
		} catch (Exception ex) {
			throw ex;
		}

	}

}
