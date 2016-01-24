package com.ekocaman.springboot.service;

import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitClientException;
import com.google.identitytoolkit.GitkitUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class GitkitService {

    private final GitkitClient gitkitClient;

    public GitkitService() throws IOException, GitkitClientException {
        gitkitClient = GitkitClient.createFromJson(this.getClass().getResource("/gitkit-server-config.json").getPath());
    }

    public GitkitUser validateTokenInRequest(HttpServletRequest request) throws GitkitClientException {
        return gitkitClient.validateTokenInRequest(request);
    }

    public GitkitUser validateToken(String token) throws GitkitClientException {
        return gitkitClient.validateToken(token);
    }
}
