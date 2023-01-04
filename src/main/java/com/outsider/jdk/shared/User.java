package com.outsider.jdk.shared;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class User {

    private String name;
    private Integer age;
    private Boolean status;
    private String recordMsg;

    private User() {

    }

    public static User build() {
        return new User();
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public User setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getRecordMsg() {
        return recordMsg;
    }

    public void supplier(Supplier<String> supplier) {
        this.recordMsg = supplier.get();
    }

    public void consumer(Consumer<String> consumer) {
        consumer.accept(this.recordMsg);
    }

    public void mutConsumer(Consumer<String> con1, Consumer<String> con2, Consumer<String> con3) {
        con1.andThen(con2).andThen(con3).accept("consumer");
    }

    public void function(Function<String, String> function) {

    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", status=" + status +
            ", recordMsg='" + recordMsg + '\'' +
            '}';
    }
}
