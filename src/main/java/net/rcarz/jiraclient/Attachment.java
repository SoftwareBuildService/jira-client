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

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Represents an issue attachment.
 */
public final class Attachment extends Resource {

    private User author = null;
    private String filename = null;
    private Date created = null;
    private int size = 0;
    private String mimeType = null;
    private String content = null;

    /**
     * Creates an attachment from a JSON payload.
     *
     * @param restclient REST client instance
     * @param json JSON payload
     */
    protected Attachment(RestClient restclient, JSONObject json) {
        super(restclient);

        if (json != null)
            deserialise(json);
    }

    private void deserialise(JSONObject json) {
        Map map = json;

        self = Field.getString(map.get("self"));
        id = Field.getString(map.get("id"));
        author = Field.getResource(User.class, map.get("author"), restclient);
        filename = Field.getString(map.get("filename"));
        created = Field.getDate(map.get("created"));
        size = Field.getInteger(map.get("size"));
        mimeType = Field.getString(map.get("mimeType"));
        content = Field.getString(map.get("content"));
    }

    /**
     * Retrieves the given attachment record.
     *
     * @param restclient REST client instance
     * @param id Internal JIRA ID of the attachment
     *
     * @return an attachment instance
     *
     * @throws JiraException when the retrieval fails
     */
    public static Attachment get(RestClient restclient, String id)
        throws JiraException {

        JSONObject result = null;

        try {
            result = restclient.get(RESOURCE_URI + "attachment/" + id);
        } catch (Exception ex) {
            throw new JiraException("Failed to retrieve attachment " + id, ex);
        }

        return new Attachment(restclient, result);
    }

    @Override
    public String toString() {
        return getContentUrl();
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreatedDate() {
        return created;
    }

    public String getContentUrl() {
        return content;
    }

    public String getFileName() {
        return filename;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getSize() {
        return size;
    }
}

