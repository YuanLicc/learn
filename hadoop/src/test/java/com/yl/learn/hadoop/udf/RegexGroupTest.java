package com.yl.learn.hadoop.udf;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class RegexGroupTest extends TestCase {
    
    public void test() {
        UDFRegexGroup udfRegexGroup = new UDFRegexGroup();
        
        assert udfRegexGroup.evaluate("1212", "", 1) == null;
        assert udfRegexGroup.evaluate("1212", "1212", 0) == null;
        assert udfRegexGroup.evaluate("1212", "1212", 1) == null;

        assert "1212".equals(udfRegexGroup.evaluate("1212", "(1212)", 1));
    
        PrintUtil.println(udfRegexGroup.evaluate("12_2112_111", "([0-9]+)_([0-9]+)_([0-9]+)", 3));
        
    }
    
}
