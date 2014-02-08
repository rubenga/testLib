package allClasses;

public class SousTheme extends Theme {

    private ThemePrincipal themeDuSousTheme;

    public SousTheme() {
    }

    public SousTheme(String nomTheme) {
        super(nomTheme);
    }

    public SousTheme(ThemePrincipal themeDuSousTheme) {
        this.themeDuSousTheme = themeDuSousTheme;
    }

    public SousTheme(ThemePrincipal themeDuSousTheme, String nomTheme) {
        super(nomTheme);
        this.themeDuSousTheme = themeDuSousTheme;
    }

    public SousTheme(String nomTheme, String code) {
        super(nomTheme, code);
    }

    public SousTheme(ThemePrincipal themeDuSousTheme, String nomTheme, String code) {
        super(nomTheme, code);
        this.themeDuSousTheme = themeDuSousTheme;
    }

    @Override
    public String toString() {
        return nomTheme + " (" + themeDuSousTheme + ")";
    }

    public ThemePrincipal getThemeDuSousTheme() {
        return themeDuSousTheme;
    }

    public void setThemeDuSousTheme(ThemePrincipal themeDuSousTheme) {
        this.themeDuSousTheme = themeDuSousTheme;
    }

    public void quicksave(SousTheme st) {
        Librairie lib = new Librairie();
        lib.getAbd().ouvrirConnexionBase();
        
        String query = "INSERT INTO SOUS_THEMES(CODETHEME, LIBELLESOUSTHEME, CODESOUSTHEME) VALUES('" + st.getThemeDuSousTheme().getCode() + "', '" + st.getNomTheme() + "', '" + st.getCode() + "')";
        
        lib.getAbd().ouvrirLienRequete();
        lib.getAbd().executerRequeteUpdate(query);
        lib.getAbd().fermerLienRequete();
        lib.getAbd().fermerConnexionBase();
    }
}
