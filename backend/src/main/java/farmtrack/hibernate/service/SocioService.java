package farmtrack.hibernate.service;

import java.util.List;

import farmtrack.hibernate.domain.Credenziali;
import farmtrack.hibernate.domain.Socio;

public interface SocioService {

    Socio createSocio(Socio socio);
    Credenziali aggiornaPassword(String oldPassword, String newPassword);
    void modificaUtente(String nomeUtente);
    Socio editSocio(Socio person);
    void inviaMailNuovoSocio(String credenziali, String mail);
    void deleteSocio(int numTessera);
    List<Socio> getAllSoci();
    void salvaListaSoci();
    void visualizzaFileLog();
    Socio getSocio(Integer id);
    void inviaMailAmministratore(String indirizzoMail);
    void ModificaOpzioniApplicazione();
}
