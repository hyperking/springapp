// package com.springapi.entities;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import javax.persistence.*;
// import javax.validation.constraints.Size;
// import java.io.Serializable;
// import java.util.HashSet;
// import java.util.Set;

// import static javax.persistence.CascadeType.*;

// @Entity
// @Table(name = "Sample")
// public class Sample implements Serializable {

// @Id
// // @GeneratedValue(strategy = GenerationType.IDENTITY)
// @Column(name = "id", unique = true, nullable = false)
// private Long id;

// @Column(nullable = false)
// @Size(max = 25)
// private String name;

// public Sample() {
// }

// public Long getId() {
// return id;
// }

// public String getName() {
// return name;
// }

// public void setName(String name) {
// this.name = name;
// }

// }