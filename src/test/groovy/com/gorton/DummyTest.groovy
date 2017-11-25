package com.gorton

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner)
class DummyTest {

    @Test
    void dummyTest(){
        Dummy dummy = new Dummy()
        assert dummy.truth()
    }
}
