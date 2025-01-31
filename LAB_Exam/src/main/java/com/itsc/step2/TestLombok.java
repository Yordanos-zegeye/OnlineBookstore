//YORDANOS ZEGEYE MULUYE

package com.itsc.step2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestLombok {

    private String firstName;
    private String lastName;
    private int age;

    public static void main(String[] args) {
        // Create a new Person using the NoArgsConstructor and set values using setters
        TestLombok person = new TestLombok();
        person.setFirstName("Yordanos");
        person.setLastName("Zegeye");
        person.setAge(22);
        System.out.println(person);
    }
}
