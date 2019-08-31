/*
 * The MIT License
 *
 * Copyright (c) 2018 Niek Haarman
 * Copyright (c) 2007 Mockito contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.nhaarman.mockitokotlin2.internal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockito.invocation.InvocationOnMock;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/**
 * This wrapper is needed, because kotlin does not allow explicit call suspend function providing Continuation.
 * But interoperability via Java allows it.
 */
public class JavaSuspendWrapper {
    /**
     * @param function accepting Object, otherwise kotlin does not allow pass suspending lambda
     */
    @Nullable
    public static <T> Object wrapSuspend(@NotNull Continuation<?> continuation, @NotNull InvocationOnMock invocationOnMock, @NotNull Object function) {
        Function2<InvocationOnMock, Continuation<? super T>, Object> realFunction = (Function2<InvocationOnMock, Continuation<? super T>, Object>) function;
        return KotlinSuspendWrapperKt.wrapSuspend(realFunction, invocationOnMock, (Continuation<Object>) continuation);
    }
}
