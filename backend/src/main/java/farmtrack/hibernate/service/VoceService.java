package farmtrack.hibernate.service;

import farmtrack.hibernate.domain.TipoVoce;
import farmtrack.hibernate.domain.Voce;


public interface VoceService {

    Voce modificaVoceInBilancio(Voce voce);
    TipoVoce aggiungiVoceInBilancio(TipoVoce voce);
    void rimuoviVoceInBilancio(Integer id);
    //List<Voce> salvaBilancio();
    //BigDecimal visualizzaBilancioAnnuale(int anno);
    //BigDecimal visualizzaBilancioTotale();

    //List<Voce> visualizzaVociInBilancio();
    //List<Voce> visualizzaVociTemporale(LocalDate data);

}