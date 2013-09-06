package jmdb.spikes;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.StringWriter;

import static org.fest.assertions.Assertions.assertThat;

public class PropertyNamingTest {

    @Test
    public void make_underscores_come_out_as_underscores() throws Exception {
        ObjectMapper om = new ObjectMapper();

        StringWriter out = new StringWriter();

        om.writeValue(out, new ObjectWithUnderscores("John", "foobar"));

        System.out.println("Object: " + out.toString());

        assertThat(out.toString()).isEqualTo("{\"first_name\":\"John\",\"last_name\":\"foobar\"}");
    }

    @Test
    public void make_camelcase_come_out_as_hyphens() throws Exception {
        ObjectMapper om = new ObjectMapper().setPropertyNamingStrategy(new CamelCaseToHypehnPropertyNamingStrategy());

        StringWriter out = new StringWriter();

        om.writeValue(out, new ObjectWithCamelCase("John", "foobar", "VAT7898"));

        System.out.println("Object: " + out.toString());

        assertThat(out.toString()).isEqualTo("{\"first-name\":\"John\",\"last-name\":\"foobar\",\"vat-code\":\"VAT7898\"}");
    }

    @Test
    public void make_camel_case() throws Exception {
        ObjectMapper om = new ObjectMapper();

        StringWriter out = new StringWriter();

        om.writeValue(out, new ObjectWithCamelCase("John", "foobar", "VAT45566"));

        System.out.println("Object: " + out.toString());

        assertThat(out.toString()).isEqualTo("{\"firstName\":\"John\",\"lastName\":\"foobar\",\"vatCode\":\"VAT45566\"}");
    }

    private static class ObjectWithUnderscores {
        public final String first_name;
        public final String last_name;

        private ObjectWithUnderscores(String first_name, String last_name) {
            this.first_name = first_name;
            this.last_name = last_name;
        }
    }

    private static class ObjectWithCamelCase {
        public final String firstName;
        public final String lastName;
        public final String vatCode;

        private ObjectWithCamelCase(String firstName, String lastName, String vatCode) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.vatCode = vatCode;
        }
    }

}