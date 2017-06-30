/*
 * Copyright 2017 Oursky Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.skygear.skygear;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidParameterException;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UserQueryByEmailsRequestUnitTest {
    @Test
    public void testUserQueryRequestCreationFlow() throws Exception {
        UserQueryByEmailsRequest request = new UserQueryByEmailsRequest(new String[]{
                "hello@skygear.dev",
                "world@skygear.dev",
                "foo@skygear.dev",
                "bar@skygear.dev"
        });

        assertEquals("user:query", request.action);

        String[] emails = (String[]) request.data.get("emails");
        assertEquals(4, emails.length);
        assertEquals("hello@skygear.dev", emails[0]);
        assertEquals("world@skygear.dev", emails[1]);
        assertEquals("foo@skygear.dev", emails[2]);
        assertEquals("bar@skygear.dev", emails[3]);
    }

    @Test(expected = InvalidParameterException.class)
    public void testUserQueryRequestCreationFlowWithNoEmails() throws Exception {
        UserQueryByEmailsRequest request = new UserQueryByEmailsRequest(new String[]{});
        request.validate();
    }
}
