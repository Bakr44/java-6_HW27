package com.example.assignment27.Repository;

import com.example.assignment27.Model.Blog;
import com.example.assignment27.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {


    Blog findBlogById(Integer id);

    List<Blog> findByUser(User user);
    List<Blog> findByTitleAndUser(String title, User user);
}
