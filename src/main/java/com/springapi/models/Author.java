package com.springapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author
 */
@Document(collection = "authors")
public class Author {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    public Author() { }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Author firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Author lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    // public void fromBook(Book newbook){
    //     parseName( newbook.getAuthor() );
    // }
    
    public void parseName(String fullname) {
        Integer i = 1;
        for (String part : fullname.split(" ")) {
            switch (i) {
            case 1:
                this.firstName = part;
                break;
            case 2:
                this.lastName = part;
                break;
            default:
                this.lastName = this.lastName + " " + part;
                break;
            }
            i++;
        }
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (o == this)
    //         return true;
    //     if (!(o instanceof Author)) {
    //         return false;
    //     }
    //     Author author = (Author) o;
    //     return Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(firstName, lastName);
    // }

    // @Override
    // public String toString() {
    //     return "{" +
    //         " firstName='" + getFirstName() + "'" +
    //         ", lastName='" + getLastName() + "'" +
    //         "}";
    // }


    
    
}