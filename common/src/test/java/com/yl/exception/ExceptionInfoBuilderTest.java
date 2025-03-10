package com.yl.exception;

import com.yl.learn.common.expection.CalculateException;
import com.yl.learn.common.expection.info.ExceptionInfo;
import com.yl.learn.common.expection.info.ExceptionInfoBuilder;
import com.yl.learn.common.expection.info.imp.DefaultExceptionInfoBuilder;
import junit.framework.TestCase;

public class ExceptionInfoBuilderTest extends TestCase {

    public void testExceptionInfoBuilder() {
        ExceptionInfoBuilder builder = new DefaultExceptionInfoBuilder();
        Throwable exception = new CalculateException("caculate exception");

        builder.buildBasicInfo(exception);
        builder.buildStackInfo(exception);
        ExceptionInfo info = builder.getExpInfo();

        System.out.println(info.getStackTrace()[0].toString());
    }
}
