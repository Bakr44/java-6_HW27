package com.example.assignment27.Service;

import com.example.assignment27.Api.ApiException;
import com.example.assignment27.Model.Blog;
import com.example.assignment27.Model.User;
import com.example.assignment27.Repository.AuthRepository;
import com.example.assignment27.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllBlog(){
        return blogRepository.findAll();
    }



    public List<Blog> getAllBlogsForUser(User user) {
        return blogRepository.findByUser(user);
    }


    public void addBlogs(Integer user_id,Blog blog) {
        User user = authRepository.findUserById(user_id);
        if (blog==null){
            throw new ApiException("Id not Found");
        }
        blog.setUser(user);

        blogRepository.save(blog);
    }

    public Blog getBlogById(Integer blogId) {
        Blog blog1 = blogRepository.findBlogById(blogId);
        if (blog1==null){
            throw new ApiException("Id not Found");
        }
        return blogRepository.findBlogById(blogId);
    }



    public List<Blog> getBlogByTitleAndUser(User user,String title) {
        return blogRepository.findByTitleAndUser(title, user);
    }


    public void updateBlog(Integer user_id, Integer blogId ,Blog blog) {
        User user=authRepository.findUserById(user_id);
        Blog blog1 = blogRepository.findBlogById(blogId);
        if (blog1==null){
            throw new ApiException("Id not Found");
        }
        if (blog1.getUser()!=user){
            throw new ApiException("Id not Found");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
    }

    public void deleteBlog(Integer user_id,Integer blogId) {
        User user=authRepository.findUserById(user_id);
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog==null){
            throw new ApiException("Id not Found");
        }
        if (blog.getUser()!=user){
            throw new ApiException("Id not Found");
        }
        blogRepository.delete(blog);
    }
}
