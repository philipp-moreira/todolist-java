package br.com.rocketseat.learning.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rocketseat.learning.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        /*
         * Pegar autenticação (Usuario e Senha)
         */

        String[] credentials = getCredentials(request);
        String userName = credentials[0];
        String password = credentials[1];

        /*
         * Validar usuario e senha
         */
        var user = userRepository.findByUserName(userName);

        if(user != null){
            var passwordVerify = BCrypt
                                    .verifyer()
                                    .verify(password.toCharArray(), user.getPassword());
            if(passwordVerify.verified){
                // Segue viagem
                filterChain.doFilter(request, response);

            }else{
                response.sendError(HttpStatus.UNAUTHORIZED.value());
            }

        }else{
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private String[] getCredentials(HttpServletRequest request) {
        // Pega as informacoes do header em formato hash (base 64).
        var authorization = request.getHeader("Authorization");
        
        // Remove a particula 'Basic' da informação e depois remove todos os espaços.
        var authEncoded = authorization.substring("Basic".length()).trim();

        // Transforma a informação tratada de hash para array de bytes
        byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

        // Converte o array de bytes em uma string unica
        var authString = new String(authDecoded);

        // Quebra a string em duas partes, onde a primeira deve ser o nome de usuario e a segunda deve ser a senha do usuario enviada no header da request.
        var credential = authString.split(":");

        return credential;
    }
}
