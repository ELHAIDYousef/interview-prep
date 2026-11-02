package prep.b2b;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yousef ELHAID
 */
final class Ligne {
    private final String libelle;
    private final long montantHT;

    Ligne(String libelle, long montantHT) {
        this.libelle = libelle;
        this.montantHT = montantHT;
    }

    String getLibelle() {
        return libelle;
    }

    long getMontantHT() {
        return montantHT;
    }

    @Override
    public String toString() {
        return libelle + "=" + montantHT;
    }
}

final class Facture {

    private static final String PAYS_MAROC = "MA";
    private static final int TAUX_TVA_MAROC = 20;
    private static final int TAUX_TVA_ETRANGER = 0;

    private final String numero;
    private final String paysClient;
    private final int tauxTva;
    private final List<Ligne> lignes;

    Facture(String numero, String paysClient, List<Ligne> lignes) {
        this.numero = numero;
        this.paysClient = paysClient;
        this.tauxTva = PAYS_MAROC.equals(paysClient) ? TAUX_TVA_MAROC : TAUX_TVA_ETRANGER;   // ← null-safe
        this.lignes = List.copyOf(lignes);
    }

    List<Ligne> getLignes() {
        return lignes;
    }

    long totalTTC() {
        long totalHT = 0;
        for (Ligne ligne : lignes) {
            totalHT += ligne.getMontantHT();
        }
        return totalHT + totalHT * tauxTva / 100;
    }

    Facture avecLigne(Ligne ligne) {
        List<Ligne> nouvellesLignes = new ArrayList<>(lignes);
        nouvellesLignes.add(ligne);
        return new Facture(numero, paysClient, nouvellesLignes);
    }

    @Override
    public String toString() {
        return numero + " " + lignes + " TTC=" + totalTTC();
    }
}

public class Main {

    public static void main(String[] args) {
        List<Ligne> saisie = new ArrayList<>();
        saisie.add(new Ligne("Licence", 1000));
        Facture f1 = new Facture("F-001", "MA", saisie);
        saisie.add(new Ligne("Support", 500));
        Facture f2 = f1.avecLigne(new Ligne("Formation", 200));
        try {
            f1.getLignes().add(new Ligne("Remise", -100));
        } catch (UnsupportedOperationException e) {
            System.out.println("Ajout refusé");
        }
        Facture f3 = new Facture("F-002", "FR", saisie);
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
    }
}