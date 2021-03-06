package me.mdbell.bus.robot;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import org.greenrobot.eventbus.SubscriberMethod;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Slf4j
class SubscriberAdapter implements SubscriberInfoIndex {

    protected static final SubscriberAdapter INSTANCE = new SubscriberAdapter();

    private static final ThreadMode THREAD_MODE = ThreadMode.POSTING;
    private static final boolean STICKY = false;

    private SubscriberAdapter() {

    }

    @Override
    public SubscriberInfo getSubscriberInfo(Class<?> aClass) {
        List<SubscriberMethod> methods = new ArrayList<>();
        SubscriberInfoImpl result = new SubscriberInfoImpl();
        result.clazz = aClass;

        iterateClass(methods, aClass);

        result.methods = methods.toArray(new SubscriberMethod[0]);
        return result;
    }

    private void iterateClass(List<SubscriberMethod> methods, Class<?> aClass) {
        for (Method m : aClass.getDeclaredMethods()) {
            Subscribe annotation = m.getAnnotation(Subscribe.class);
            if (annotation == null) {
                continue;
            }
            if (m.getParameterCount() > 1) {
                log.warn("Method {} has more then one parameter in bus, skipping!", m);
                continue;
            }
            if (Modifier.isStatic(m.getModifiers())) {
                log.warn("Method {} is static, event receiver need to be non-static!", m);
                continue;
            }
            if (!Modifier.isPublic(m.getModifiers())) {
                log.warn("Method {} is not public. It needs to be", m);
                continue;
            }
            Class<?> eventType = m.getParameterTypes()[0];
            log.debug("Registering event subscriber '{}' in '{}' type:{}",
                    m.getName(), aClass.getName(), eventType.getName());
            methods.add(new SubscriberMethod(m, eventType, THREAD_MODE, annotation.priority(), STICKY));
        }
    }

    private class SubscriberInfoImpl implements SubscriberInfo {

        private Class<?> clazz;
        private SubscriberMethod[] methods;

        @Override
        public Class<?> getSubscriberClass() {
            return clazz;
        }

        @Override
        public SubscriberMethod[] getSubscriberMethods() {
            return methods;
        }

        @Override
        public SubscriberInfo getSuperSubscriberInfo() {
            return SubscriberAdapter.this.getSubscriberInfo(clazz.getSuperclass());
        }

        @Override
        public boolean shouldCheckSuperclass() {
            return false;
        }
    }
}
