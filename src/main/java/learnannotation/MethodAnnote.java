package learnannotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnote {
    String author() default "Huke";
    String date();
    int revision() default 1;
    String comments();
}
