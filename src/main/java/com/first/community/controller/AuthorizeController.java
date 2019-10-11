package com.first.community.controller;

import com.first.community.DTO.AccessDTO;
import com.first.community.DTO.GithubUser;
import com.first.community.mapper.UserMapper;
import com.first.community.model.User;
import com.first.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Chiayhon
 * @create 2019 - 10 - 10
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.Client_id}")
    private String ClientId;

    @Value("${github.Client_secret}")
    private String ClientSecret;

    @Value("${github.Redirect_uri}")
    private String RedirectURL;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessDTO accessDTO = new AccessDTO();
        accessDTO.setClient_id(ClientId);
        accessDTO.setClient_secret(ClientSecret);
        accessDTO.setCode(code);
        accessDTO.setRedirect_uri(RedirectURL);
        accessDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if(githubUser != null){
            //登陆成功
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtCreate(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));
//            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }

    }
}
