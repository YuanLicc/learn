package com.yl.learn.dp.prototype.impl;

import com.yl.learn.dp.prototype.Prototype;

import java.io.*;

/**
 * 默认具体原型类，无属性类
 * @author YuanLi
 * @param <T>
 */
public abstract class DefaultPrototype implements Prototype {

    public Prototype deepClone() {
        Prototype object = null;
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream (bao);
            oos.writeObject(this);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
            object = (Prototype)ois.readObject();
        }
        catch (IOException ioE) {
            ioE.printStackTrace();
        }
        catch (ClassNotFoundException cnfE) {
            cnfE.printStackTrace();
        }
        finally {
            return object;
        }
    }
}
