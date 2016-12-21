/**
 * 
 */
package com.mindtree.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.cms.dao.BlogDAO;
import com.mindtree.cms.model.BlogComments;
import com.mindtree.cms.model.BlogContent;
import com.mindtree.cms.pojo.Blog;
import com.mindtree.cms.pojo.Comment;
import com.mindtree.cms.util.FileUtil;

/**
 * @author M1030563
 *
 */
@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogDAO blogDao;
	@Autowired
	FileUtil fileUtil;
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void addBlog(BlogContent blog) throws Exception {
		blogDao.addBlog(blog);
	}

	@Transactional
	public void updateBlog(BlogContent blog) throws Exception {
		blogDao.updateBlog(blog);

	}

	@Transactional(readOnly = true)
	public List<Blog> listBlogs() throws Exception {
		List<BlogContent> blogs = blogDao.listBlogs();
		List<Blog> blogList = new ArrayList<Blog>();
		if (blogs != null) {
			for (BlogContent blogContent : blogs) {
				Blog blog = new Blog();
				blog.setBlogId(blogContent.getBlogId());
				blog.setTittle(blogContent.getTittle());
				if (blogContent.getFile() != null) {
					blog.setFileBase64(fileUtil.Base64ImgConvertor(blogContent
							.getFile()));
				}
				blogList.add(blog);
			}
		}
		return blogList;
	}

	@Transactional(readOnly = true)
	public Blog getBlogById(int id) throws Exception {
		BlogContent content = blogDao.getBlogById(id);
		Blog blog = null;
		List<Comment> comments = new ArrayList<Comment>();
		if (null != content) {
			blog = new Blog();
			blog.setBlogId(content.getBlogId());
			blog.setTittle(content.getTittle());
			blog.setContent(content.getContent());
			if (content.getFile() != null) {
				blog.setFileBase64(fileUtil.Base64ImgConvertor(content
						.getFile()));
			}
		}
		if (null != content.getBlogComments()
				&& !content.getBlogComments().isEmpty()) {
			for (BlogComments comm : content.getBlogComments()) {
				Comment comment = new Comment();
				comment.setComment(comm.getComment());
				comment.setCommentId(comm.getCommentId());
				comment.setUserName(comm.getUserName());
				comments.add(comment);
			}
			blog.setComments(comments);
		}
		return blog;
	}

	@Transactional
	public void removeBlog(int blogId) throws Exception {
		blogDao.removeBlog(blogId);
		
	}

	@Transactional
	public void saveBlogComment(Comment blogComment) throws Exception {
		BlogContent content = blogDao.getBlogById(blogComment.getBlogId());
		BlogComments comment = new BlogComments();
		comment.setComment(blogComment.getComment());
		comment.setUserName(blogComment.getUserName());
		comment.setBlogContent(content);
		blogDao.saveComment(comment);
		
	}

	@Transactional
	public List<Comment> findBlogComments(int blogId) throws Exception {
		BlogContent content = blogDao.getBlogById(blogId);
		List<Comment> comments = new ArrayList<Comment>();
		if (null != content.getBlogComments()
				&& !content.getBlogComments().isEmpty()) {
			for (BlogComments comm : content.getBlogComments()) {
				Comment comment = new Comment();
				comment.setComment(comm.getComment());
				comment.setCommentId(comm.getCommentId());
				comment.setUserName(comm.getUserName());
				comments.add(comment);
			}
		}
		return comments;
	}
	

}
