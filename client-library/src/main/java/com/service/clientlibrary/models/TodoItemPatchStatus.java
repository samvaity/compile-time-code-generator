// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.service.clientlibrary.models;

/**
 * Defines values for TodoItemPatchStatus.
 */
public enum TodoItemPatchStatus {
    /**
     * Enum value NotStarted.
     */
    NOT_STARTED("NotStarted"),

    /**
     * Enum value InProgress.
     */
    IN_PROGRESS("InProgress"),

    /**
     * Enum value Completed.
     */
    COMPLETED("Completed");

    /**
     * The actual serialized value for a TodoItemPatchStatus instance.
     */
    private final String value;

    TodoItemPatchStatus(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a TodoItemPatchStatus instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed TodoItemPatchStatus object, or null if unable to parse.
     */
    public static TodoItemPatchStatus fromString(String value) {
        if (value == null) {
            return null;
        }
        TodoItemPatchStatus[] items = TodoItemPatchStatus.values();
        for (TodoItemPatchStatus item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
