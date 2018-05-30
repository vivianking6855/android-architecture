/**
 * Exception Factory, offer all exception strings
 */
package com.clean.exception;

import android.content.Context;

import com.clean.R;
import com.learn.data.exception.DataException;

import static com.learn.data.common.ResultConst.DATA_NET_ERROR;
import static com.learn.data.common.ResultConst.DATA_NO_USER;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context   Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Throwable exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof DataException) {
            switch (((DataException) exception).code) {
                case DATA_NO_USER:
                    message = context.getString(R.string.exception_message_user_not_found);
                    break;
                case DATA_NET_ERROR:
                    message = context.getString(R.string.exception_message_no_connection);
                    break;
                default:
                    break;
            }
        }

        return message;
    }

    /**
     * Create with error code string.
     *
     * @param context the context
     * @param code    the code
     * @return the string
     */
    public static String createWithErrorCode(Context context, int code) {
        String message = context.getString(R.string.exception_message_generic);

            switch (code) {
                case DATA_NO_USER:
                    message = context.getString(R.string.exception_message_user_not_found);
                    break;
                case DATA_NET_ERROR:
                    message = context.getString(R.string.exception_message_no_connection);
                    break;
                default:
                    break;
            }

        return message;
    }

}
