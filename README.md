# Dynamic Properties Bean creation

This project demonstrates a method of loading configuration properties into objects and making them available on the Application Context. Similar to the user of a class annotated with `@ConfigurationProperties`, properties with a specific prefix are bound to an object. However, in this case, a key-value map is processed in such a way that each value is bound to an object and provided as a bean on the application context with its key as bean name.

The effect is [demonstrated](src/test/java/com/example/demo/DemoApplicationTests.java) by mapping the following [application.yaml](src/test/resources/application.yml):
```yml
my.properties:
  foo:
    name: Foo
    value: 15
  bar:
    name: Baz
    value: 50
```

This results in two [`CustomProperties`](src/main/java/com/example/demo/CustomProperties.java) beans being available on the application context, with the names "foo" and "bar", respectively.

