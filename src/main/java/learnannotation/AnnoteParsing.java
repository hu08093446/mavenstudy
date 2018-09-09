package learnannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

public class AnnoteParsing {
    public static void main(String[] args) {
        try {
            for (Method method :
                    AnnoteParsing.class
                            .getClassLoader()
                            .loadClass("learnannotation.AnnoteTest")
                            .getMethods()) {
                if (method.isAnnotationPresent(MethodAnnote.class)) {
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        System.out.println("annotation for method " + method + " : " + annotation);
                    }

                    MethodAnnote methodAnnote = method.getAnnotation(MethodAnnote.class);

                    if( Objects.equals(methodAnnote.revision(), 1)) {
                        System.out.println("this is the default revision.");
                    }

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
