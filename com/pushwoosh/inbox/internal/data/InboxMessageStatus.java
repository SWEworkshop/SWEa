package com.pushwoosh.inbox.internal.data;

import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum InboxMessageStatus {
    CREATED(0),
    DELIVERED(1),
    READ(2),
    OPEN(3),
    DELETED_BY_USER(4),
    DELETED_FROM_SERVICE(5);
    
    private int code;

    private InboxMessageStatus(int i) {
        this.code = i;
    }

    public static List<InboxMessageStatus> getActualCodes() {
        return Arrays.asList(CREATED, DELIVERED, READ, OPEN);
    }

    @Nullable
    public static InboxMessageStatus getByCode(int i) {
        InboxMessageStatus[] values = values();
        for (InboxMessageStatus inboxMessageStatus : values) {
            if (inboxMessageStatus.code == i) {
                return inboxMessageStatus;
            }
        }
        PWLog.error("Incorrect InboxMessageStatusCode: " + i);
        return null;
    }

    public int getCode() {
        return this.code;
    }

    public Collection<InboxMessageStatus> getLowerStatus() {
        ArrayList arrayList = new ArrayList();
        InboxMessageStatus[] values = values();
        for (InboxMessageStatus inboxMessageStatus : values) {
            if (inboxMessageStatus.isLowerStatus(this)) {
                arrayList.add(inboxMessageStatus);
            }
        }
        return arrayList;
    }

    public boolean isLowerStatus(InboxMessageStatus inboxMessageStatus) {
        switch (inboxMessageStatus) {
            case DELIVERED:
                if (equals(CREATED)) {
                    return true;
                }
                break;
            case READ:
                if (equals(CREATED) || equals(DELIVERED)) {
                    return true;
                }
                break;
            case OPEN:
                if (equals(CREATED) || equals(DELIVERED) || equals(READ)) {
                    return true;
                }
                break;
            case DELETED_BY_USER:
                if (equals(CREATED) || equals(DELIVERED) || equals(READ) || equals(OPEN)) {
                    return true;
                }
                break;
            case DELETED_FROM_SERVICE:
                return true;
        }
        return false;
    }
}
