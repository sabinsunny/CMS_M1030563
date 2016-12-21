/**
 * 
 */
package com.mindtree.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mindtree.cms.pojo.Blog;
import com.mindtree.cms.pojo.Comment;
import com.mindtree.cms.pojo.GuestUser;
import com.mindtree.cms.service.BlogService;

/**
 * @author Sabin
 *
 */
@Controller
public class NavigationController {
	@Autowired
	BlogService blogService;

	@RequestMapping(value = "/")
	public String viewFirst(ModelMap model) {
		GuestUser guest = new GuestUser();
		model.put("userForm", guest);
		return "userLogin";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String doLogin(
			@Valid @ModelAttribute("userForm") GuestUser userForm,
			BindingResult result, ModelMap model) throws Exception {
		if (result.hasErrors()) {
			return "userLogin";
		}
		List<Blog> blogs = blogService.listBlogs();
		model.addAttribute("blogs", blogs);
		model.addAttribute("userForm", userForm);
		return "usersBlogs";
	}

	@RequestMapping(value = "/openBlog", method = RequestMethod.GET)
	public String getBlogDetails(@RequestParam("value") String blogId,
			@RequestParam("userName") String userName, Model model) throws NumberFormatException, Exception {
		Blog blog = blogService.getBlogById(Integer.parseInt(blogId.trim()));
		model.addAttribute("blog", blog);
		model.addAttribute("blogComment", new Comment());
		model.addAttribute("userName", userName);
		return "blogDetails";
	}

	@RequestMapping(value = "/backToMain", method = RequestMethod.GET)
	public String doGoBacktoView(@RequestParam("userName") String userName,
			Model model) throws Exception {
		List<Blog> blogs = blogService.listBlogs();
		model.addAttribute("blogs", blogs);
		GuestUser user = new GuestUser();
		user.setUserName(userName);
		model.addAttribute("userForm", user);
		return "usersBlogs";
	}

	@RequestMapping(value = "/addBlogComment", method = RequestMethod.POST)
	public @ResponseBody List<Comment> saveBlogComments(
			@RequestBody Comment blogComment, HttpServletRequest request,
			HttpServletResponse response) {
		List<Comment> blogComments = null;
		try {
			if (blogComment.getComment() != null
					&& !blogComment.getComment().isEmpty())
				blogService.saveBlogComment(blogComment);
			blogComments = blogService
					.findBlogComments(blogComment.getBlogId());
		} catch (Exception ex) {

		}
		return blogComments;
	}

}
