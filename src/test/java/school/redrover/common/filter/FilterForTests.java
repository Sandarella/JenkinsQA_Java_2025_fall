package school.redrover.common.filter;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.Arrays;
import java.util.List;

import static school.redrover.common.ProjectUtils.log;

public class FilterForTests implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        String files = System.getenv("LIST_OF_CHANGED_FILES");
        log("Changed files: " + files);

        String dependenciesClasses = System.getenv("LIST_OF_DEPENDENCIES_CLASSES");
        log("Dependencies classes: " + dependenciesClasses);

        if (files != null && dependenciesClasses != null) {
            return FilterUtils.filterMethods(
                    Arrays.stream(files.split(";")).toList(),
                    dependenciesClasses,
                    methods);
        }

        return methods;
    }
}
