/**
 * jira-client - a simple JIRA REST client
 * Copyright (c) 2013 Bob Carroll (bob.carroll@alum.rit.edu)
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.rcarz.jiraclient;

import java.lang.Throwable;

/**
 * An exception for JIRA REST errors.
 */
public class RestException extends Exception {

    private int status;
    private String result;

    public RestException(String msg, int status, String result) {
        super(msg);

        this.status = status;
        this.result = result;
    }

    public int getHttpStatusCode() {
        return status;
    }

    public String getHttpResult() {
        return result;
    }

    public String getMessage() {
        String msg = "%s: %s: %s";
        return msg.format(Integer.toString(status), super.getMessage(), result);
    }
}

