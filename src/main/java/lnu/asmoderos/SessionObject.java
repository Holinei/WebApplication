package lnu.asmoderos;

public class SessionObject {
    private boolean logged = false;
    private int role = 3;
    private int id;

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
