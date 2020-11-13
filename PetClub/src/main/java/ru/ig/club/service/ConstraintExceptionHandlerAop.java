package ru.ig.club.service;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import ru.ig.club.exception.ApplDbConstraintException;

/**
 * Обработка Exception, выбрасываемых при завершении транзакции.
 */
@Aspect
@Component
@Order(0)
public class ConstraintExceptionHandlerAop {

    @AfterThrowing(
            pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)",
            throwing = "error"
    )
    public void handle(Throwable error) throws Throwable {
        if (error instanceof DataIntegrityViolationException) {
            throw new ApplDbConstraintException();
        } else {
            throw error;
        }
    }
}

