package com.lpnu.pizzaplace.DI;

import java.util.ArrayList;
import java.util.List;

public class ServiceCollection {

    private final List<ServiceDescriptor> _serviceDescriptors = new ArrayList<>();

    public void registerSingleton(Class<?> serviceType, Class<?> implementationClass)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceType, implementationClass, ServiceLifetime.Singleton));
    }

    public void registerSingleton(Class<?> serviceType, Object implementation)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceType, implementation));
    }

    public void registerSingleton(Class<?> serviceType, ServiceFactory<?> serviceFactory)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceType, serviceFactory, ServiceLifetime.Singleton));
    }

    public void registerSingleton(Class<?> implementationClass)
    {
        _serviceDescriptors.add(new ServiceDescriptor(implementationClass, ServiceLifetime.Singleton));
    }

    public void registerSingleton(Object implementation)
    {
        _serviceDescriptors.add(new ServiceDescriptor(implementation));
    }

    public void registerSingleton(ServiceFactory<?> serviceFactory)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceFactory, ServiceLifetime.Singleton));
    }

    public void registerTransient(Class<?> serviceType, Class<?> implementationClass)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceType, implementationClass, ServiceLifetime.Transient));
    }

    public void registerTransient(Class<?> serviceType, ServiceFactory<?> serviceFactory)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceType, serviceFactory, ServiceLifetime.Transient));
    }

    public void registerTransient(Class<?> implementationClass)
    {
        _serviceDescriptors.add(new ServiceDescriptor(implementationClass, implementationClass, ServiceLifetime.Transient));
    }

    public void registerTransient(ServiceFactory<?> serviceFactory)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceFactory, ServiceLifetime.Transient));
    }

    public void registerScoped(Class<?> serviceType, Class<?> implementationClass)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceType, implementationClass, ServiceLifetime.Scoped));
    }

    public void registerScoped(Class<?> serviceType, ServiceFactory<?> serviceFactory)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceType, serviceFactory, ServiceLifetime.Scoped));
    }

    public void registerScoped(Class<?> implementationClass)
    {
        _serviceDescriptors.add(new ServiceDescriptor(implementationClass, implementationClass, ServiceLifetime.Scoped));
    }

    public void registerScoped(ServiceFactory<?> serviceFactory)
    {
        _serviceDescriptors.add(new ServiceDescriptor(serviceFactory, ServiceLifetime.Scoped));
    }

    public DependencyInjectionContainer buildContainer()
    {
        return new DependencyInjectionContainer(_serviceDescriptors);
    }
}