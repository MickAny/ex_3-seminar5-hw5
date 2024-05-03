package seminar5.ex_3.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class TrackUserActionAspect {

    private final Map<String, String> actions = Map.of(
            "getAll", "Получение всех записей",
            "create", "Создание записи",
            "delTask", "Удаление задачи",
            "updateTask", "Изменение данных задачи",
            "taskInsert", "Добавление новой задачи"
    );
    @Around("@annotation(TrackUserAction)")
    public Object trackUserActions(ProceedingJoinPoint joinPoint) throws Throwable{

        String methodName = actions.get(joinPoint.getSignature().getName());
        System.out.println("(COMPLETED) " + methodName);
        System.out.println("=".repeat(30));

        Object result = joinPoint.proceed();
        return result;
    }
}
