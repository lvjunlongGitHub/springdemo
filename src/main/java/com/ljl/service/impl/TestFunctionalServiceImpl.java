package com.ljl.service.impl;

import com.ljl.service.TestFunctionalService;

/**
 * @author lvjunlong
 * @date 2019/8/27 下午9:44
 */
public class TestFunctionalServiceImpl implements TestFunctionalService {
    @Override
    public void say() {
        System.out.println("say----");
    }

    public static void main(String[] args) {
        TestFunctionalServiceImpl persionService = new TestFunctionalServiceImpl();
        persionService.say();
        persionService.spell();

        TestFunctionalService.eat();
    }
}
