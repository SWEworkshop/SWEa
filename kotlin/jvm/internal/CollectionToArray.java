package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CollectionToArray {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    public static Object[] toArray(Collection<?> collection) {
        int size = collection.size();
        if (size == 0) {
            return EMPTY_OBJECT_ARRAY;
        }
        Object[] objArr = new Object[size];
        Iterator<?> it = collection.iterator();
        for (int i = 0; i < size; i++) {
            if (!it.hasNext()) {
                return Arrays.copyOf(objArr, i);
            }
            objArr[i] = it.next();
        }
        return it.hasNext() ? finishToArray(objArr, it) : objArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v13, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E> T[] toArray(Collection<E> collection, T[] tArr) {
        int size = collection.size();
        T[] tArr2 = tArr.length >= size ? tArr : (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        Iterator<E> it = collection.iterator();
        for (int i = 0; i < tArr2.length; i++) {
            if (it.hasNext()) {
                tArr2[i] = it.next();
            } else if (tArr != tArr2) {
                return (T[]) Arrays.copyOf(tArr2, i);
            } else {
                tArr2[i] = null;
                return tArr2;
            }
        }
        return it.hasNext() ? (T[]) finishToArray(tArr2, it) : tArr2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v12, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T[] finishToArray(T[] tArr, Iterator<?> it) {
        int length = tArr.length;
        while (it.hasNext()) {
            int length2 = tArr.length;
            if (length == length2) {
                int i = ((length2 / 2) + 1) * 3;
                if (i <= length2) {
                    if (length2 != Integer.MAX_VALUE) {
                        i = Integer.MAX_VALUE;
                    } else {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                }
                tArr = (T[]) Arrays.copyOf(tArr, i);
            }
            tArr[length] = it.next();
            length++;
        }
        return length == tArr.length ? tArr : (T[]) Arrays.copyOf(tArr, length);
    }

    private CollectionToArray() {
    }
}
