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

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class LogoutResponseHandlerWrapperUnitTest {
    @Test
    public void testLogoutResponseHandlerWrapperSuccessFlow() throws Exception {
        final boolean[] checkpoints = { false };
        LogoutResponseHandler logoutResponseHandler = new LogoutResponseHandler() {
            @Override
            public void onLogoutSuccess() {
                checkpoints[0] = true;
            }

            @Override
            public void onLogoutFail(Error error) {
                fail("Should not get fail callback");
            }
        };

        LogoutResponseHandlerWrapper wrapper = new LogoutResponseHandlerWrapper(
                null,
                logoutResponseHandler
        );

        wrapper.onSuccess(new JSONObject());

        assertTrue(checkpoints[0]);
    }

    @Test
    public void testLogoutResponseHandlerWrapperFailFlow() throws Exception {
        final boolean[] checkpoints = { false };
        LogoutResponseHandler logoutResponseHandler = new LogoutResponseHandler() {
            @Override
            public void onLogoutSuccess() {
                fail("Should not get success callback");
            }

            @Override
            public void onLogoutFail(Error error) {
                checkpoints[0] = true;
                assertEquals("Test Error", error.getDetailMessage());
            }
        };

        LogoutResponseHandlerWrapper wrapper = new LogoutResponseHandlerWrapper(
                null,
                logoutResponseHandler
        );

        wrapper.onFail(new Error("Test Error"));

        assertTrue(checkpoints[0]);
    }

    @Test
    public void testLogoutResponseHandlerWrapperAuthResolveFlow() throws Exception {
        final boolean[] checkpoints = { false };
        AuthResolver resolver = new AuthResolver() {
            @Override
            public void resolveAuthUser(User user) {
                checkpoints[0] = true;
                assertNull(user);
            }
        };

        LogoutResponseHandlerWrapper wrapper = new LogoutResponseHandlerWrapper(resolver, null);
        wrapper.onSuccess(new JSONObject());

        assertTrue(checkpoints[0]);
    }
}
