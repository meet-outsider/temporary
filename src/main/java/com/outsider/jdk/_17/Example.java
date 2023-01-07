package com.outsider.jdk._17;

import com.outsider.jdk.shared.User;

public class Example {

    public static void main(String[] args) {
        // chain call
        User alex = User.build()
            .setName("Alex")
            .setAge(16)
            .setStatus(false);
        String data = "data";
        System.out.println("提供给" + alex.getName() + "一个数据: " + data);
        alex.supplier(() -> "data");
        alex.consumer((x) -> {
            System.out.println("消费提供的数据: " + x);
        });
        System.out.println("链式调用，消费多个数据");
        alex.mutConsumer(
            (s) -> {
                System.out.println("转小写" + s.toLowerCase());
            },
            (s) -> {
                System.out.println("转大写" + s.toUpperCase());
            },
            (s) -> {
                System.out.println("拼接" + s.concat("_append"));
            }
        );
        System.out.println(alex.toString());
        int num = 3;
        // match pattern
        switch (num) {
            case 1 -> System.out.println("match 1");
            case 2 -> System.out.println("match 2");
            case 3 -> System.out.println("match 3");
            default -> System.out.println("match nothing");
        }
        // textBlock
        String textBlock = """
            {
              "title" : "untitled",
              "context": "this is context,but noting"
            }
            """;
        // instanceOf
        // if (textBlock instanceof String str) {
        //     // System.out.println(str);
        // }
        // record
        record UserRecord(String name, Integer age) {

        }
        UserRecord userRecord = new UserRecord("name", 11);
        System.out.println("record example: " + userRecord);
        // System.out.printf("variable: %d str: %s", variable, str);
    }
}
