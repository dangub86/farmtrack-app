package farmtrack.hibernate.service;



import farmtrack.hibernate.domain.Socio;

public interface LoginService {

 Integer verificaUtenze(String utente, String password);
 Socio getSocio(Integer id);
 String getLivelloAmministrazione(Socio socio);

}