/**
 * 
 */
package com.mindtree.cms.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.cms.model.BlogComments;
import com.mindtree.cms.model.BlogContent;
import com.mindtree.cms.pojo.Blog;
import com.mindtree.cms.service.BlogService;
import com.mindtree.cms.util.FileUtil;

/**
 * @author M1030563
 *
 */
@Controller
@RequestMapping("/secure")
public class LoginController {
	@Autowired
	BlogService blogService;
	@Autowired
	FileUtil fileUtil;
	@Autowired
	private Environment environment;

	@RequestMapping(value = "/home")
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = "/addBlog")
	public String addBlogContents(Model model) {
		model.addAttribute("blog", new Blog());
		return "addBlog";
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String success(@Valid @ModelAttribute("blog") Blog blog,
			BindingResult result, ModelMap model) throws Exception {
		MultipartFile file = blog.getFile();
		boolean filevalid=true;
		if (file == null
				|| file.getSize() == 0
				|| file.getSize() > Long.parseLong(environment
						.getRequiredProperty("maxUploadSize"))) {
			filevalid=false;
			model.addAttribute("fileError",environment.getRequiredProperty("SelectImage"));
		} else {
			String type = file.getContentType();
			String[] types = type.split("/");
			if (!types[0].toLowerCase().contains("image".toLowerCase())) {
				filevalid=false;
				model.addAttribute("fileError",environment.getRequiredProperty("SelectOnlyImage"));
			}
		}
		if (result.hasErrors() || !filevalid) {
			return "addBlog";
		} else {
			String blogName = blog.getTittle();
			BlogContent blogContent = new BlogContent();
			blogContent.setTittle(blog.getTittle());
			blogContent.setContent(blog.getContent());
			if (!blog.getFile().isEmpty()) {
				blogContent.setFile(fileUtil.convertToBytes(blog.getFile()));
			}
			Set<BlogComments> blogComments = new HashSet<BlogComments>();
			BlogComments comment = new BlogComments();
			blogComments.add(comment);
			blogContent.setBlogComments(blogComments);
			blogService.addBlog(blogContent);
			model.addAttribute("blogName", blogName);
			return "blogAdded";
		}
	}

	@RequestMapping(value = "/deleteBlogHere")
	public String doDelete(Model model) throws Exception {
		List<Blog> blogs = blogService.listBlogs();
		model.addAttribute("blogs", blogs);
		return "deleteBlogs";
	}

	@RequestMapping(value = "/deleteBlog", method = RequestMethod.GET)
	public String getBlogDetails(@RequestParam("value") String blogId,
			String userName, Model model) throws NumberFormatException, Exception {
		blogService.removeBlog(Integer.parseInt(blogId.trim()));
		List<Blog> blogs = blogService.listBlogs();
		model.addAttribute("blogs", blogs);
		return "deleteBlogs";
	}
}
