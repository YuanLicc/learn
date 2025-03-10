package com.yl.learn.dp.observer;

import com.yl.learn.dp.observer.imp.Subject;
import junit.framework.TestCase;

public class ObserverTest extends TestCase {

    public void testObserver() {
        Subject subject = new SubjectA();

        subject.attach(new ObserverA());
        subject.notifyObservers();
    }
}
