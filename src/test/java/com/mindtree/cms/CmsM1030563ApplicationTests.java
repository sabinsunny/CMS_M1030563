package com.mindtree.cms;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.cms.pojo.Blog;
import com.mindtree.cms.pojo.Comment;
import com.mindtree.cms.service.BlogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsM1030563ApplicationTests {
	@Autowired
	BlogService blogService;
	@Test
	public void blogLoads() throws Exception {
		List<Blog> blogs = blogService.listBlogs();
		assertEquals(blogs.size(),0);
	}
	@Test(expected=Exception.class)
	public void insertComment() throws Exception {
		Comment blogComment = new Comment();
		blogComment.setUserName("sabin");
		blogComment.setComment("test comment");
		blogService.saveBlogComment(blogComment);
	}
	@Test(expected=Exception.class)
	public void getBlog() throws Exception {
		Blog blog = blogService.getBlogById(100);
		assertTrue(blog == null);
	}
	

}
