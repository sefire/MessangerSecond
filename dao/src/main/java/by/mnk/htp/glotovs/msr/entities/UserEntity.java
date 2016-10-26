package by.mnk.htp.glotovs.msr.entities;

import java.io.Serializable;

/**
 * Created by Sefire on 24.10.2016.
 */
public class UserEntity implements IEntity<Integer> {

    private Integer idUser;
    private String phone;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private Integer age;
    private String password;

    public UserEntity() {
    }

    public UserEntity(Integer idUser, String phone, String firstName, String lastName, String country, String city, Integer age, String password) {
        this.idUser = idUser;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.age = age;
        this.password = password;
    }


    @Override
    public Integer getId() {
        return this.idUser;
    }

    public void setId(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", phone=" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
