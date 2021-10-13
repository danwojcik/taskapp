package pl.sda.taskapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//adnotacje loobmokowe
@Builder
@Data //zawiera w sobie gettery, settery, konstruktor dal pól final, equals i hashcode
@NoArgsConstructor //konstruktor bezparametrowy
@AllArgsConstructor //konstruktor z wszystkimi parametrami
public class TaskDto {

    private Long id;
    private String name;
    private String description;
}
