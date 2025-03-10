package com.yl.learn.dp.memento;

import com.yl.learn.dp.memento.imp.Caretaker;
import com.yl.learn.dp.memento.imp.Originator;
import junit.framework.TestCase;

public class MementoTest extends TestCase {

    public void testMemento() {
        Originator o = new Originator();
        o.setState("AA");
        System.out.println(o);

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(o.createMemento());

        o.setState("BB");
        System.out.println(o);

        o.restoreMemento(caretaker.getMemento());
        System.out.println(o);
    }
}
