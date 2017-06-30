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

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ReferenceUnitTest {
    @Test
    public void testReferenceCreationNormalFlow() throws Exception {
        Reference noteRef = new Reference("Note", "123");

        assertEquals("Note", noteRef.type);
        assertEquals("123", noteRef.id);
    }

    @Test
    public void testReferenceCreationUsingRecordFlow() throws Exception {
        Record aNote = new Record("Note", null);
        Reference reference = new Reference(aNote);

        assertEquals(aNote.getId(), reference.id);
        assertEquals(aNote.getType(), reference.type);
    }
}
