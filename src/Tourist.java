public class Tourist {
    private String name;
    private int children;
    private int adults;

    public Tourist(String name, int children, int adults) {
        this.name = name;
        this.children = children;
        this.adults = adults;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public int getAdults() {
        return adults;
    }
}
