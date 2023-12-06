package com.lpnu.pizzaplace.DI;

public class ServiceDescriptor {
    private final Class<?> serviceType;

    private final Class<?> implementationType;

    private ServiceFactory<?> implementationFactory;

    private final ServiceLifetime lifetime;

    private final Object implementation;

    public ServiceDescriptor(Object implementation) {
        this.implementationType = implementation.getClass();
        this.implementation = implementation;
        this.implementationFactory = (container) -> this.implementation;
        this.lifetime = ServiceLifetime.Singleton;
        this.serviceType = implementation.getClass();
    }

    public ServiceDescriptor(ServiceFactory<?> implementationFactory, ServiceLifetime lifetime)
    {
        this.implementation = null;
        this.implementationFactory = implementationFactory;
        try {
            this.serviceType = this.implementationFactory.getClass().getMethod("getService").getReturnType();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        this.lifetime = lifetime;
        this.implementationType = null;
    }

    public ServiceDescriptor(Class<?> implementationType, ServiceLifetime lifetime)
    {
        this.implementation = null;
        this.implementationFactory = null;
        this.serviceType = implementationType;
        this.lifetime = lifetime;
        this.implementationType = implementationType;
    }

    public ServiceDescriptor(Class<?> serviceType, Object implementation)
    {
        checkForTypes(serviceType, implementation.getClass());

        this.implementation = implementation;
        this.serviceType = serviceType;
        this.implementationFactory = (container) -> this.implementation;
        this.lifetime = ServiceLifetime.Singleton;
        this.implementationType = implementation.getClass();
    }

    public ServiceDescriptor(Class<?> serviceType, ServiceFactory<?> implementationFactory, ServiceLifetime lifetime)
    {
        this.serviceType = serviceType;
        this.implementationFactory = implementationFactory;
        this.lifetime = lifetime;
        this.implementation = null;
        this.implementationType = null;
    }

    public ServiceDescriptor(Class<?> serviceType, Class<?> implementationType, ServiceLifetime lifetime) {
        this.serviceType = serviceType;
        this.lifetime = lifetime;
        this.implementationFactory = null;
        this.implementation = null;
        this.implementationType = implementationType;
    }

    public Class<?> getServiceType() {
        return serviceType;
    }

    public ServiceFactory<?> getImplementationFactory()
    {
        return implementationFactory;
    }

    public void setImplementationFactory(ServiceFactory<?> implementationFactory) {
        this.implementationFactory = implementationFactory;
    }

    public ServiceLifetime getServiceLifetime() {
        return lifetime;
    }

    public Class<?> getImplementationType() {
        return implementationType;
    }

    private static void checkForTypes(Class<?> serviceType, Class<?> implementationType) {
        if (!serviceType.isAssignableFrom(implementationType)) {
            throw new RuntimeException(String.format(
                    "Invalid service type %s for object of type %s",
                    serviceType.getName(),
                    implementationType.getName()));
        }
    }
}
