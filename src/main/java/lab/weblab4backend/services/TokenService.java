package lab.weblab4backend.services;


import lab.weblab4backend.dao.HitDao;
import lab.weblab4backend.dao.TokenDao;
import lab.weblab4backend.model.Hit;
import lab.weblab4backend.model.Token;

import java.time.ZonedDateTime;
import java.util.List;

public class TokenService {

    private final TokenDao dao = new TokenDao();

    public TokenService() {
    }

    public Token findHit(String token) {
        return dao.findByToken(token);
    }

    public void add(String token) {
        Token token1 = new Token();
        token1.setToken(token);
        token1.setCreatedAt(ZonedDateTime.now().plusDays(30));
        this.dao.save(token1);
    }

}