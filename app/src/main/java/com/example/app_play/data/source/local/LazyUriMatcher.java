package com.example.app_play.data.source.local;

/**
 * Created by vel-4009 on 2019-09-18.
 * <p>
 * Love your Job
 */

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by vel-4009 on 10/08/16.
 */
public abstract class LazyUriMatcher<T> {


    private final AtomicReference<T> mCachedValue = new AtomicReference<>();

    public final T get()
    {
        T value = mCachedValue.get();
        if(value == null)
        {
            // putting "synchronized" outside will lock, every time we call to get UriMatcher.
            // now only if the value is null - i.e. only the initial time - it will lock.
            synchronized (mCachedValue)
            {
                value = mCachedValue.get();
                if(value == null)
                {
                    mCachedValue.set(initialValue());
                }
            }
        }

        return mCachedValue.get();
    }

    protected abstract T initialValue();
}
