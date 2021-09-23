package taxi.model;

import lombok.Data;

@Data
public class Aggregator {
    private int id;
    private String name;
    private Car car;
}
