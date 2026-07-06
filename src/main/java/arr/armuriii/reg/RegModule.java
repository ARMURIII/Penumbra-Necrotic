package arr.armuriii.reg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RegModule {
    String modid();
    int priority() default 1000;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface RegistryEntry {
        String id() default "";
        String[] params() default {};
    }
}
