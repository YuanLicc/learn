package com.yl.learn.hadoop.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Description(name = "regex_group",
        value = "_FUNC_(column, regex, group)")
public class UDFRegexGroup extends UDF {
    
    public String evaluate(String column, String regex, int group) {
        if (column == null || regex == null) return null;
     
        group = group < 0 ? 0 : group;
        
        if (group == 0) return null;
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(column);
        
        int groupCnt = matcher.groupCount();
        
        if(!matcher.matches()) return null;
        
        if(group <= groupCnt) {
            return matcher.group(group);
        }
        
        return null;
    }
    
    
}
