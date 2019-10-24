package farmtrack.hibernate.service;

import java.util.List;

import farmtrack.hibernate.domain.Credenziali;
import farmtrack.hibernate.domain.Socio;
import farmtrack.hibernate.repository.LoginRepository;
import farmtrack.hibernate.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;
    private SocioRepository socioRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository, SocioRepository socioRepository) {
        this.loginRepository = loginRepository;
        this.socioRepository = socioRepository;
    }

    @Override
    public Integer verificaUtenze(String utente, String password) {

        List<Credenziali> credenziali = loginRepository.findAll();
        for (Credenziali credenziale : credenziali) {

            if (credenziale.getNomeUtente().equals(utente)) {

                if (credenziale.getPassword().equals(password)) {
                    return credenziale.getNumTessera();
                }

            }
        }
        return 0;

    }

    @Override
    public Socio getSocio(Integer id) {
        return socioRepository.getOne(id);
    }

    @Override
    public String getLivelloAmministrazione(Socio socio) {
        String[] ruoli = socio.getRuolo().split(",");
        String ruolo = ruoli[0];

        if (ruolo.contains("amministratore")) {
            return "A";
        } else if ((ruolo.contains("presidente") || ruolo.contains("tesoriere")) && !ruolo.contains("amministratore")) {
            return "B";
        } else {
            return "C";
        }

    }

}