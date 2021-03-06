package com.scb.bookstore.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scb.bookstore.model.order.Order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(unique=true)
    @JsonProperty("username")
    private String username;

    @Column
    @JsonIgnore
    @JsonProperty("password")
    private String password;

    @Column
    @JsonProperty("first_name")
    private String firstName;

    @Column
    @JsonProperty("last_name")
    private String lastName;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonProperty("books")
    private List<Order> orders = new ArrayList<>();

    public List<Integer> getOrders() {
        if (!orders.isEmpty()) {
          return orders.stream()
                  .map(Order::getId)
                  .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @JsonIgnore
    public List<Order> getOriginalOrders() {
        return orders;
    }

    @JsonIgnore
    public String getPassword()
    {
        return password;
    }
}
