package com.lpnu.pizzaplace.DI;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class DependencyInjectionContainer {
    private final List<ServiceDescriptor> _serviceDescriptors;

    public DependencyInjectionContainer(List<ServiceDescriptor> serviceDescriptors)
    {

        this._serviceDescriptors = serviceDescriptors;
    }

    public <TInterface> TInterface getService(Class<TInterface> interfaceClass)
    {
        var serviceDescriptor = _serviceDescriptors
                .stream()
                .filter(serviceDesc -> serviceDesc.getServiceType() == interfaceClass)
                .findFirst().orElse(null);

        if (serviceDescriptor == null)
        {
            return null;
        }

        if (serviceDescriptor.getServiceLifetime() == ServiceLifetime.Scoped)
        {
            throw new RuntimeException(
                    String.format(
                            "Scoped service of type %s could only be instantiated inside of scope. Create one by using createScope method",
                            serviceDescriptor.getServiceType().getName()));
        }

        if (serviceDescriptor.getImplementationFactory() == null)
        {
            var implementationFactory = createFactoryForService(serviceDescriptor);
            serviceDescriptor.setImplementationFactory(implementationFactory);
        }

        //noinspection unchecked
        return (TInterface) serviceDescriptor.getImplementationFactory().getService();
    }

    public DependencyInjectionContainer createScope()
    {
        var newCollection = _serviceDescriptors.stream().map(serviceDescriptor -> {
            if (serviceDescriptor.getServiceLifetime() == ServiceLifetime.Scoped) {
                if (serviceDescriptor.getImplementationFactory() != null) {
                    return new ServiceDescriptor(serviceDescriptor.getServiceType(), serviceDescriptor.getImplementationFactory(), ServiceLifetime.Singleton);
                }
                return new ServiceDescriptor(serviceDescriptor.getServiceType(), serviceDescriptor.getImplementationType(), ServiceLifetime.Singleton);
            }
            return serviceDescriptor;
        }).toList();

        return new DependencyInjectionContainer(newCollection);
    }

    public boolean containsService(Class<?> serviceType)
    {
        return _serviceDescriptors
                .stream()
                .anyMatch(serviceDescriptor -> serviceDescriptor.getServiceType() == serviceType);
    }

    private ServiceFactory<?> createFactoryForService(ServiceDescriptor serviceDescriptor)
    {
        ServiceFactory<?> baseFactory = createFactoryToBuildServiceFromOtherServices(serviceDescriptor);

        if (serviceDescriptor.getServiceLifetime() == ServiceLifetime.Singleton)
        {
            var implementation = baseFactory.getService();
            return () -> implementation;
        }

        return baseFactory;
    }

    private ServiceFactory<?> createFactoryToBuildServiceFromOtherServices(ServiceDescriptor serviceDescriptor)
    {
        var constructor = getSuitableConstructor(serviceDescriptor);
        return getFactoryForConstructor(constructor);
    }

    private Constructor<?> getSuitableConstructor(ServiceDescriptor serviceDescriptor)
    {
        var implementationConstructors = serviceDescriptor.getImplementationType().getConstructors();

        var possibleConstructors = Arrays.stream(implementationConstructors).filter(this::canBeUsed).toList();

        var constructorsCount = possibleConstructors.size();

        if (constructorsCount == 0)
        {
            throw new RuntimeException(
                    String.format(
                            "Error initializing service %s. No suitable or parameterless constructor found",
                            serviceDescriptor.getServiceType().getName()));
        }

        if (constructorsCount == 1)
        {
            return possibleConstructors.getFirst();
        }

        //We should prioritize constructor with parameters if there are many ones. So we remove parameterless constructors and try again
        possibleConstructors = possibleConstructors
                .stream()
                .filter(constructor -> constructor.getParameterCount() == 0)
                .toList();

        constructorsCount = possibleConstructors.size();

        if (constructorsCount == 1)
        {
            return possibleConstructors.getFirst();
        }

        StringBuilder stringBuilder = new StringBuilder("The constructor choice is ambiguous between this ones: \n");
        possibleConstructors.forEach(constructor -> stringBuilder.append(constructor.toString()).append("\n"));

        throw new RuntimeException(stringBuilder.toString());
    }

    private ServiceFactory<?> getFactoryForConstructor(Constructor<?> constructor)
    {
        return () -> {
            var params = Arrays.stream(constructor.getParameterTypes()).map(this::getService).toArray();
            try {
                return constructor.newInstance(params);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private boolean canBeUsed(Constructor<?> constructor)
    {
        var parameterTypes = constructor.getParameterTypes();

        return Arrays.stream(parameterTypes).allMatch(this::containsService);
    }
}
