package allClasses;

public class ThemePrincipal extends Theme{

    public ThemePrincipal() {
    }

    public ThemePrincipal(String nomTheme) {
        super(nomTheme);
    }

    public ThemePrincipal(String nomTheme, String code) {
        super(nomTheme, code);
    }

    @Override
    public String toString() {
        return nomTheme;
    }

    public void quicksave(ThemePrincipal tp) {
        Librairie lib = new Librairie();
        lib.getAbd().ouvrirConnexionBase();
        
        String query = "INSERT INTO THEMES(CODETHEME, LIBELLETHEME) VALUES('" + tp.getCode() + "', '" + tp.getNomTheme() + "')";
        
        lib.getAbd().ouvrirLienRequete();
        lib.getAbd().executerRequeteUpdate(query);
        lib.getAbd().fermerLienRequete();
        lib.getAbd().fermerConnexionBase();
    }
    
}
