package cn.sexycode.myjpa.spring.factory;

import cn.sexycode.myjpa.binding.StandardServiceRegistryImpl;
import cn.sexycode.myjpa.query.DefaultQueryFactory;
import cn.sexycode.myjpa.spring.Beans;
import cn.sexycode.sql.dialect.DialectFactory;
import cn.sexycode.sql.dialect.DialectFactoryImpl;
import cn.sexycode.sql.dialect.StandardDialectResolver;
import cn.sexycode.util.core.cls.classloading.ClassLoaderService;
import cn.sexycode.util.core.cls.classloading.ClassLoaderServiceImpl;
import cn.sexycode.util.core.factory.BeanFactory;
import cn.sexycode.util.core.factory.BeanFactoryUtil;
import cn.sexycode.util.core.factory.selector.StrategySelectorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * 适配Spring BeanFactory
 * @author qzz
 */
public class BeanFactoryAdapter implements BeanFactory, BeanDefinitionRegistryPostProcessor {
    private org.springframework.beans.factory.BeanFactory beanFactory;

    /**
     * 默认构造函数
     */
    public BeanFactoryAdapter() {
        this(null);
    }

    /**
     * 构造函数
     * @param beanFactory Spring BeanFactory
     */
    public BeanFactoryAdapter(org.springframework.beans.factory.BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        BeanFactoryUtil.setBeanFactory(this);
    }

    /**
     * 获取bean
     * @param clazz class
     * @param <T> 泛型
     * @return 对象
     */
    @Override
    public <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }

    /**
     * 获取bean
     * @param s bean 名称
     * @param <T> 泛型
     * @return 对象
     */
    @Override
    public <T> T getBean(String s) {
        return (T) beanFactory.getBean(s);
    }

    /**
     * 获取bean
     * @param s bean 名称
     * @param aClass 类型
     * @param <T> 泛型
     * @return 对象
     */
    @Override
    public <T> T getBean(String s, Class<T> aClass) {
        return beanFactory.getBean(s,aClass);
    }

    /**
     * 注册bean
     * @param registry BeanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (!registry.containsBeanDefinition(Beans.CLASS_LOADER_SERVICE)) {
            registry.registerBeanDefinition(Beans.CLASS_LOADER_SERVICE, BeanDefinitionBuilder.genericBeanDefinition(
                    ClassLoaderServiceImpl.class).getBeanDefinition());
        }
        if (!registry.containsBeanDefinition(Beans.DIALECT_FACTORY)) {
            registry.registerBeanDefinition(Beans.DIALECT_FACTORY, BeanDefinitionBuilder.genericBeanDefinition(
                    DialectFactory.class,()-> new DialectFactoryImpl(new StandardDialectResolver(),new StrategySelectorImpl(beanFactory.getBean(Beans.CLASS_LOADER_SERVICE, ClassLoaderService.class)))).getBeanDefinition());
        }
        if (!registry.containsBeanDefinition(Beans.STANDARD_SERVICE_REGISTRY)) {
            registry.registerBeanDefinition(Beans.STANDARD_SERVICE_REGISTRY, BeanDefinitionBuilder.genericBeanDefinition(
                    StandardServiceRegistryImpl.class).getBeanDefinition());
        }
        if (!registry.containsBeanDefinition(Beans.QUERY_FACTORY)) {
            registry.registerBeanDefinition(Beans.QUERY_FACTORY, BeanDefinitionBuilder.genericBeanDefinition(
                    DefaultQueryFactory.class).getBeanDefinition());
        }
    }

    /**
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        BeanFactoryUtil.setBeanFactory(this);
    }
}
