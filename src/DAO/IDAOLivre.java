package DAO;

import java.util.List;
import Entites.Livre;
public interface IDAOLivre {
    public void Ajouter(Livre liv);
    public void Supprimer(int id);
    public void Modifier(Livre newl);
    public List<Livre> AfficherTout();
    public Livre AfficherParId(int id);
}
