package main.model;

import lombok.*;

@Data
@AllArgsConstructor
public class Manufacturer {
    private int id;
    private String name;
    private String country;
}
