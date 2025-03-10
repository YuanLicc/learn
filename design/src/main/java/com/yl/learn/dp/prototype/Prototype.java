package com.yl.learn.dp.prototype;

import java.io.IOException;
import java.io.OptionalDataException;
import java.io.Serializable;

/**
 * 原型模式抽象原型接口（深度克隆）
 * @author YuanLi
 * @param <T>
 */
public interface Prototype extends Serializable {

    /**
     * 深度克隆，原型复制
     * @return T
     */
    Prototype deepClone();
}
