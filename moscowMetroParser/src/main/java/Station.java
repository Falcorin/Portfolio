public class Station {
    private Line line;
    private String name;

    public Station(String name, Line line) {
        this.line = line;
        this.name = name;
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}