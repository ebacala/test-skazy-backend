package com.ebacala.answer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Table(schema = "api", name = "answers")
public class Answer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "a")
    @NotNull(message = "a must be a number between 1 and 9")
    @Min(value = 1, message = "a must be a number between 1 and 9")
    @Max(value = 9, message = "a must be a number between 1 and 9")
    private Integer a;

    @Column(name = "b")
    @NotNull(message = "b must be a number between 1 and 9")
    @Min(value = 1, message = "b must be a number between 1 and 9")
    @Max(value = 9, message = "b must be a number between 1 and 9")
    private Integer b;

    @Column(name = "c")
    @NotNull(message = "c must be a number between 1 and 9")
    @Min(value = 1, message = "c must be a number between 1 and 9")
    @Max(value = 9, message = "c must be a number between 1 and 9")
    private Integer c;

    @Column(name = "d")
    @NotNull(message = "d must be a number between 1 and 9")
    @Min(value = 1, message = "d must be a number between 1 and 9")
    @Max(value = 9, message = "d must be a number between 1 and 9")
    private Integer d;

    @Column(name = "e")
    @NotNull(message = "e must be a number between 1 and 9")
    @Min(value = 1, message = "e must be a number between 1 and 9")
    @Max(value = 9, message = "e must be a number between 1 and 9")
    private Integer e;

    @Column(name = "f")
    @NotNull(message = "f must be a number between 1 and 9")
    @Min(value = 1, message = "f must be a number between 1 and 9")
    @Max(value = 9, message = "f must be a number between 1 and 9")
    private Integer f;

    @Column(name = "g")
    @NotNull(message = "g must be a number between 1 and 9")
    @Min(value = 1, message = "g must be a number between 1 and 9")
    @Max(value = 9, message = "g must be a number between 1 and 9")
    private Integer g;

    @Column(name = "h")
    @NotNull(message = "h must be a number between 1 and 9")
    @Min(value = 1, message = "h must be a number between 1 and 9")
    @Max(value = 9, message = "h must be a number between 1 and 9")
    private Integer h;

    @Column(name = "i")
    @NotNull(message = "i must be a number between 1 and 9")
    @Min(value = 1, message = "i must be a number between 1 and 9")
    @Max(value = 9, message = "i must be a number between 1 and 9")
    private Integer i;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "modification_date", nullable = false)
    @UpdateTimestamp
    private Date modificationDate;

    public Answer() {
    }

    public Answer(List<Integer> unknownList) {
        this.setA(unknownList.get(0));
        this.setB(unknownList.get(1));
        this.setC(unknownList.get(2));
        this.setD(unknownList.get(3));
        this.setE(unknownList.get(4));
        this.setF(unknownList.get(5));
        this.setG(unknownList.get(6));
        this.setH(unknownList.get(7));
        this.setI(unknownList.get(8));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getE() {
        return e;
    }

    public void setE(Integer e) {
        this.e = e;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean valid) {
        isValid = valid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Boolean containsDifferentUnknowns() {
        Set<Integer> unknownsSet = new HashSet<>();

        unknownsSet.add(this.getA());
        unknownsSet.add(this.getB());
        unknownsSet.add(this.getC());
        unknownsSet.add(this.getD());
        unknownsSet.add(this.getE());
        unknownsSet.add(this.getF());
        unknownsSet.add(this.getG());
        unknownsSet.add(this.getH());
        unknownsSet.add(this.getI());

        return unknownsSet.size() == 9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return a == answer.a && b == answer.b && c == answer.c && d == answer.d && e == answer.e && f == answer.f && g == answer.g && h == answer.h && i == answer.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d, e, f, g, h, i);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                ", g=" + g +
                ", h=" + h +
                ", i=" + i +
                '}';
    }
}
