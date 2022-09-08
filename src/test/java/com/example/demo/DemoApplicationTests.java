package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    PropertiesWrapper propertiesWrapper;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void propertyBindingUserConfigurationPropertiesAnnotation() {
        final CustomProperties foo = propertiesWrapper.getProperties().get("foo");
        assertThat(foo).isNotNull();
        assertThat(foo.getName()).isEqualTo("Foo");
        assertThat(foo.getValue()).isEqualTo(15);

        final CustomProperties bar = propertiesWrapper.getProperties().get("bar");
        assertThat(bar).isNotNull();
        assertThat(bar.getName()).isEqualTo("Baz");
        assertThat(bar.getValue()).isEqualTo(50);
    }


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    // I don't know how, and even if it's possible to generate the metadata required,
    // because it's unknown which properties will be present.
    @Autowired
    @Qualifier("foo")
    CustomProperties fooProperties;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired // Resolve by name
    CustomProperties bar;

    @Test
    void propertyBindingUsingBinderAndBeanFactoryPostProcessor() {
        assertThat(fooProperties).isNotNull();
        assertThat(fooProperties.getName()).isEqualTo("Foo");
        assertThat(fooProperties.getValue()).isEqualTo(15);
        assertThat(bar).isNotNull();
        assertThat(bar.getName()).isEqualTo("Baz");
        assertThat(bar.getValue()).isEqualTo(50);
    }

}
