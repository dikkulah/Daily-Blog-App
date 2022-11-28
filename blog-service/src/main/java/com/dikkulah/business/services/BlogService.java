package com.dikkulah.business.services;

import com.dikkulah.business.dto.BlogDto;
import com.dikkulah.data.entity.Blog;
import com.dikkulah.data.repository.BlogRepository;
import com.dikkulah.exception.AlreadyInUseException;
import com.dikkulah.exception.ResourceNotFoundException;
import com.dikkulah.exception.TokenNotValidException;
import com.dikkulah.feign.GatewayClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class BlogService {

    private static final String THERE_IS_NO_BLOG_WITH_THIS_ID = " there is no blog with this id.";
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;
    private final GatewayClient gatewayClient;

    public BlogDto entityToDto(Blog blog) {
        return modelMapper.map(blog, BlogDto.class);
    }

    public Blog dtoToEntity(BlogDto blogDto) {
        return modelMapper.map(blogDto, Blog.class);
    }

    private String validToken(String gatewayToken) {
        return gatewayClient.validate(gatewayToken, gatewayToken).getBody();
    }

    public BlogDto createBlog(BlogDto blogDto, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Blog blog = dtoToEntity(blogDto);
            if (blogRepository.findById(blogDto.getId()).isPresent())
                throw new AlreadyInUseException("There is daily with this id: ".concat(blogDto.getId().toString()));
            else {
                blogRepository.save(blog);
                return blogDto;
            }
        } else throw new TokenNotValidException();

    }

    public List<BlogDto> listUserBlog(String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            List<Blog> blogList = blogRepository.findByEmail(isTokenValid);
            List<BlogDto> dtoList = new ArrayList<>();
            blogList.forEach(blog -> dtoList.add(entityToDto(blog)));
            return dtoList;
        } else throw new TokenNotValidException();
    }

    public BlogDto findBlog(String id, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Blog blog = blogRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException(id.concat(THERE_IS_NO_BLOG_WITH_THIS_ID)));
            return entityToDto(blog);
        } else throw new TokenNotValidException();
    }

    public String deleteBlog(String id, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Blog blog = blogRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException(id.concat(THERE_IS_NO_BLOG_WITH_THIS_ID)));
            blogRepository.delete(blog);
            return "Deleted";
        } else throw new TokenNotValidException();
    }

    public BlogDto updateBlog(String id, BlogDto blogDto, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Blog blog = blogRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException(id.concat(THERE_IS_NO_BLOG_WITH_THIS_ID)));
            if (blog != null) {
                blogRepository.save(dtoToEntity(blogDto));
            }
            return blogDto;
        } else throw new TokenNotValidException();
    }
}
