package com.ljl.service;

/**
 * <P>
 *     测试函数式接口
 *     什么是函数式接口？
 *     1、接口内除object的public方法外，如果一个接口中有且只有一个抽象方法 则其为一个函数式接口
 *     2、如果一个接口上声明了@FunctionalInterface注解 则编译器会按照函数式接口的定义来要求该接口
 *     3、如果接口上只有一个抽象方法，但我们没有对其加上@FunctionalInterface 编译器仍然将其看作函数式接口，加上注解后 一目了然  如果没有满足强制性要求 则会抛出错误信息
 *     4、只有一个抽象方法的接口 有必要加上 @FunctionalInterface 如 Runnable接口
 *     5、所有的函数式接口 都可以使用lambda表达式 实现（表达易懂 简单
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午9:44
 */
@FunctionalInterface
public interface TestFunctionalService {

    /**
     * 抽象方法
     */
    void say();

    /**
     * 默认方法
     */
    default void spell() {
        System.out.println("说话");
    }

    /**
     * 静态方法
     */
    static void eat() {
        System.out.println("eat====");
    }

    static void main(String[] args) {

        TestFunctionalService persionService = () -> System.out.println("hello");

        persionService.say();
    }
}
