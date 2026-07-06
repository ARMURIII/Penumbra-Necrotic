package arr.armuriii.reg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RegModule {
    String modid();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface RegistryEntry {
        String id() default "";
    }
}
