package allClasses;

public abstract class Theme{
    protected String nomTheme;
    protected String code;

    public Theme() {
    }
    
    public Theme(String nomTheme) {
        this.nomTheme = nomTheme;
    }

    public String getNomTheme() {
        return nomTheme;
    }

    public void setNomTheme(String nomTheme) {
        this.nomTheme = nomTheme;
    }

    public Theme(String nomTheme, String code) {
        this.nomTheme = nomTheme;
        this.code = code;
    }

    public String toString() {
        return nomTheme;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
