public class Usuario {
    private int id;
    private String name;

    public Usuario(String name) {
        this.name = name;
        this.id = Banco.getNewId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
